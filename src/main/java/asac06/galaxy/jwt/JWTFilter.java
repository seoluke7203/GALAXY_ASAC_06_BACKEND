package asac06.galaxy.jwt;

import asac06.galaxy.exception.JWTErrorType;
import asac06.galaxy.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken = authorization.split(" ")[1];

        try {
            jwtProvider.isTokenExpired(accessToken);
        } catch (ExpiredJwtException e) {
            // 에러처리 필요
//            PrintWriter writer = response.getWriter();
//            writer.print("access token expired");
//
//            //response status code
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            JwtExceptionResponseUtil.unAuthentication(response, JWTErrorType.EXPIRED_ACCESS_TOKEN);
            return;
        }

        String category = jwtProvider.getCategory(accessToken);

        if(!"access".equals(category)) {
            //response body
//            PrintWriter writer = response.getWriter();
//            writer.print("invalid access token");
//
//            //response status code
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            JwtExceptionResponseUtil.unAuthentication(response, JWTErrorType.INVALID_TOKEN_ERROR);
        }

        String username = jwtProvider.getUsernameFromToken(accessToken);

        User user = new User();

        user.setUsername(username);

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, null);

        // 세션에 사용자 등룍
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);

        // --------------------------to-be




        // --------------------------as - is
        /*String authorization = request.getHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer ")) {
//            response.setStatus(401);
//            response.getWriter().write("token error");

            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.split(" ")[1];

        if(!jwtProvider.validateToken(token)) {
            //에러 로그 필요
            filterChain.doFilter(request, response);
            return;
        }

//        if(!jwtProvider.isTokenExpired(token)) {
//            // 만료된 토큰이면, refresh 토큰을 통해 새로운 access 토큰 발급 처리
//        }

        String username = jwtProvider.getUsernameFromToken(token);

//        User user = new User();
//        user.setUsername(username);
//        user.setPassword("temppassword");
//
//        // UserDetail 에 회원정보 담기
//        CustomUserDetails customUserDetails = new CustomUserDetails(user);

//        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);

        // 세션에 사용자 등룍
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);*/
        // --------------------------as - is
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        // 권한없이 접근 가능한 url 에 접근 시, 헤더에 Authorization 이 있는 경우 에러 발생
//        // permitAll url 은 인증 절차 예외
//        String[] excludePath = {"/login", "/ticketing/{id}", "api/register", "/api/products"};
//
//        String path = request.getRequestURI();
//
//        return Arrays.stream(excludePath).anyMatch(path::startsWith);
//    }
}
