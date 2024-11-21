package asac06.galaxy.jwt;

import asac06.galaxy.common.ApiResponse;
import asac06.galaxy.exception.JWTErrorType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtExceptionResponseUtil {

    public static void unAuthentication(HttpServletResponse response, JWTErrorType jwtErrorType) throws IOException {
        ObjectMapper om = new ObjectMapper();
        String responseBody = om.writeValueAsString(new ApiResponse<>(jwtErrorType.getStatus().value(), jwtErrorType.getDesc(), null));
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(jwtErrorType.getStatus().value());
        response.getWriter().println(responseBody);
    }
}