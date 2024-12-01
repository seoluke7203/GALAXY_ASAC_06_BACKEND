package asac06.galaxy.service.impl;

import asac06.galaxy.exception.JWTErrorType;
import asac06.galaxy.exception.JWTException;
import asac06.galaxy.jwt.JWTProvider;
import asac06.galaxy.model.Auth;
import asac06.galaxy.repository.AuthRepository;
import asac06.galaxy.service.AuthService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JWTProvider jwtProvider;
    private final AuthRepository authRepository;

    @Override
    public Map<String, String> reissue(Cookie refresh) {

        if(refresh == null) {
            throw new JWTException(JWTErrorType.INVALID_REFRESH_TOKEN);
        }
        // 토큰 만료 확인
        try {
            jwtProvider.isTokenExpired(refresh.getValue());
        } catch (Exception e) {
            throw new JWTException(JWTErrorType.EXPIRED_REFRESH_TOKEN);
        }

        Boolean isExist = authRepository.existsByRefresh(refresh.getValue());
        if(!isExist) {
            throw new JWTException(JWTErrorType.INVALID_REFRESH_TOKEN);
        }

        String username = jwtProvider.getUsernameFromToken(refresh.getValue());

        String newAccessToken = jwtProvider.generateToken("access", username, 600000L);
        String newRefreshToken = jwtProvider.generateToken("refresh", username, 86400000L);


//        완벽히 지워지지 않음. -> TTL 혹은 배치 등의 추가 작업 필요
        authRepository.deleteByRefresh(refresh.getValue());


        Auth authEntity = new Auth(username, newRefreshToken, LocalDateTime.now().plusDays(1));
        authRepository.save(authEntity);


        Map<String, String> reissueTokenMap = new HashMap<>();

        reissueTokenMap.put("access", newAccessToken);
        reissueTokenMap.put("refresh", newRefreshToken);

        return reissueTokenMap;
    }
}
