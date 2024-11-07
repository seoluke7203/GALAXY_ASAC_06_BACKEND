package asac06.galaxy.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto<T> {
    private Integer statusCode;
    private String message;
    private T data;

    public ResponseDto(Integer statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
