package sp.senai.org.vetmark.exception;

public record FieldErrorResponse(

        String field,
        String message
) {
}
