package asac06.galaxy.controller;

import asac06.galaxy.common.ApiResponse;
import asac06.galaxy.service.AuthService;
import asac06.galaxy.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<Object>> reissue(@CookieValue(name = "refresh") Cookie refresh, HttpServletResponse response) {

        //@CookieValue 사용으로 대체
//        String refresh = null;
//        Cookie[] cookies = request.getCookies();
//        for(Cookie cookie : cookies) {
//            if("refresh".equals(cookie.getName())) {
//                refresh = cookie.getValue();
//            }
//        }

        Map<String, String> reissueTokensMap = authService.reissue(refresh);

        String newAccessToken = reissueTokensMap.get("access");
        String newRefreshToken = reissueTokensMap.get("refresh");

        response.setHeader("Authorization", "Bearer " + newAccessToken);
        response.addCookie(CookieUtil.createCookie("refresh", newRefreshToken, 24*60*60));

        return ResponseEntity.status(200)
                .body(new ApiResponse<>(HttpStatus.OK.value(), null, null));
    }
}
