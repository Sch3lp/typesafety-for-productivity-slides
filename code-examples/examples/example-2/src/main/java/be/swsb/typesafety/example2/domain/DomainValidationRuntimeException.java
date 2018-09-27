package be.swsb.typesafety.example2.domain;

public class DomainValidationRuntimeException extends RuntimeException {
    public DomainValidationRuntimeException(final String message) {
        super(message);
    }
}
