package sp.senai.org.vetmark.exception;

public class BusinessException
        extends RuntimeException {

    public BusinessException(
            String message
    ) {
        super(message);
    }

}
