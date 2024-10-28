package asac06.galaxy.advice;

import org.springframework.http.HttpStatus;

public interface ErrorType {
    String name();
    HttpStatus getStatus();
    String getType();
    String getDesc();
}