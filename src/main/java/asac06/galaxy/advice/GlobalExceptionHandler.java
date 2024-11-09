package asac06.galaxy.advice;


import asac06.galaxy.exception.CustomException;
import asac06.galaxy.exception.ExceptionType;
import asac06.galaxy.repository.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<Obj> handle(Exception e) {
//        log.error("[ERROR] " + e.getMessage(), e);
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .build();
//    }


    @ExceptionHandler
    public BaseResponse<Void> handle(CustomException e) {
        log.error("[ERROR] " + e.getMessage(), e);
        ExceptionType type = e.getType();

        return BaseResponse.failure(type);
    }
}
