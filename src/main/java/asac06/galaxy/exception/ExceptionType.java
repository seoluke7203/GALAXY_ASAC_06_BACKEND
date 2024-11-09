package asac06.galaxy.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
public enum ExceptionType {

    PRODUCT_NOT_FOUND("P04", "상품이 데이터베이스 내 존재하지 않습니다. 요청하신 상품 id : ", HttpStatus.NOT_FOUND, Level.WARN);

    String type;
    String desc;
    HttpStatus status;
    Level level;

}
