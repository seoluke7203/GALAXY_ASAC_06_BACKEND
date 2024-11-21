package asac06.galaxy.exception;

import lombok.Getter;

public class JWTException  extends RuntimeException {
    private final JWTErrorType errorType;

    public JWTException(JWTErrorType errorType) {
        super(errorType.getDesc());
        this.errorType = errorType;
    }
}