package asac06.galaxy.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDetail {

    private final boolean success = false;
//    private String name;
//    private String type;
    private final String desc;

    public static ErrorDetail of(ErrorType type) {
        return new ErrorDetail(type.getDesc());
    }
}