package asac06.galaxy.config;

import asac06.galaxy.jwt.*;
import asac06.galaxy.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTProvider jwtProvider;
    private final AuthRepository authRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors((cors) -> cors.configurationSource(corsConfigurationSource()));

        http.csrf((auth)-> auth.disable());
        http.httpBasic((auth)-> auth.disable());
        http.formLogin((auth)-> auth.disable());
        http.logout((auth)-> auth.disable());

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/api/products/test").authenticated()
                .requestMatchers("/api/products/**"
                                ,"api/register"
                                ,"/ticketing/{id}"
                                ,"/login").permitAll()
//                .requestMatchers("/reissue").permitAll()
//                .requestMatchers("/error").permitAll()
                .anyRequest().authenticated()
        );
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtProvider, authRepository), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JWTFilter(jwtProvider), LoginFilter.class);
        http.addFilterBefore(new CustomLogoutFilter(jwtProvider, authRepository), LogoutFilter.class);

        // 인증되지 않은 사용자가 보호된 리소스에 액세스
        http.exceptionHandling((auth)-> auth.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        // jwt 방식은 STATELESS 방식
        http.sessionManagement((session)->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // 모든 경로에 대해서 CORS 설정을 적용

        return source;
    }
}
