package asac06.galaxy.jwt;

import asac06.galaxy.exception.JWTErrorType;
import asac06.galaxy.model.Auth;
import asac06.galaxy.repository.AuthRepository;
import asac06.galaxy.util.CookieUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final AuthRepository authRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//        GrantedAuthority auth = iterator.next();
//
//        String role = auth.getAuthority();

        String access = jwtProvider.generateToken("access", username, 6000L);
        String refresh = jwtProvider.generateToken("refresh", username, 86400000L);

        // refresh token db 저장
        Auth authEntity = new Auth(username, refresh, LocalDateTime.now().plusDays(1));
        authRepository.save(authEntity);

        response.addHeader("Authorization", "Bearer " + access);
        response.addCookie(CookieUtil.createCookie("refresh", refresh, 24*60*60));
        response.setStatus(HttpStatus.OK.value());
        new ObjectMapper().writeValue(response.getOutputStream(), username);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        //에러 로그 추가 필요
        JwtExceptionResponseUtil.unAuthentication(response, JWTErrorType.USER_NOT_FOUND);
    }
}
