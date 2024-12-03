package asac06.galaxy.jwt;

import asac06.galaxy.exception.JWTErrorType;
import asac06.galaxy.repository.AuthRepository;
import asac06.galaxy.util.CookieUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

    private final JWTProvider jwtProvider;
    private final AuthRepository authRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.doFilter((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse, filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        if (!requestURI.matches("^\\/logout$")) {

            chain.doFilter(request, response);
            return;
        }

        String requestMethod = request.getMethod();
        if(!"POST".equals(requestMethod)) {
            chain.doFilter(request, response);
            return;
        }

        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if("refresh".equals(cookie.getName())) {
                refresh = cookie.getValue();
            }
        }

        if(refresh == null){
            JwtExceptionResponseUtil.unAuthentication(response, JWTErrorType.INVALID_REFRESH_TOKEN);
            return;
        }
        try{
            jwtProvider.isTokenExpired(refresh);
        }catch(ExpiredJwtException e){
            JwtExceptionResponseUtil.unAuthentication(response, JWTErrorType.INVALID_REFRESH_TOKEN);
            return;
        }

        String category = jwtProvider.getCategory(refresh);
        if(!"refresh".equals(category)){
            JwtExceptionResponseUtil.unAuthentication(response, JWTErrorType.INVALID_REFRESH_TOKEN);
            return;
        }

        Boolean isExist = authRepository.existsByRefresh(refresh);
        if(!isExist){
            JwtExceptionResponseUtil.unAuthentication(response, JWTErrorType.INVALID_REFRESH_TOKEN);
            return;
        }
        authRepository.deleteByRefresh(refresh);

        Cookie cookie = CookieUtil.deleteCookie("refresh");

        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
        new ObjectMapper().writeValue(response.getOutputStream(), null);

    }
}
