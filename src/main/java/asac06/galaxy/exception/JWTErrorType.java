package asac06.galaxy.exception;

import asac06.galaxy.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JWTErrorType implements ErrorCode {
    USER_NOT_FOUND("U401", HttpStatus.UNAUTHORIZED,"존재하지 않는 사용자입니다."),
    AUTHENTICATION_FAIL("A401", HttpStatus.UNAUTHORIZED,"인증 오류가 발생하였습니다.");

    private final String code;
    private final HttpStatus status;
    private final String desc;
}
