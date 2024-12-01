package asac06.galaxy.exception;

import asac06.galaxy.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JWTErrorType implements ErrorCode {
    USER_NOT_FOUND("U401", HttpStatus.UNAUTHORIZED,"존재하지 않는 사용자입니다."),
    AUTHENTICATION_FAIL("A401", HttpStatus.UNAUTHORIZED,"인증 오류가 발생하였습니다."),

    INVALID_REFRESH_TOKEN("R400", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    EXPIRED_REFRESH_TOKEN("R401", HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다. 다시 로그인 해주세요.");

    private final String code;
    private final HttpStatus status;
    private final String desc;
}
