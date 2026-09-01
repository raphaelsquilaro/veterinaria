package sp.senai.org.vetmark.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(

        LocalDateTime timestamp,
        Integer status,
        String error,
        String path,
        List<FieldErrorResponse> fields
) {
}
