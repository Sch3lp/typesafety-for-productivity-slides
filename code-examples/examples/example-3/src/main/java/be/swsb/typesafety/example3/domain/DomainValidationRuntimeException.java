package be.swsb.typesafety.example3.domain;

public class DomainValidationRuntimeException extends RuntimeException {
    public DomainValidationRuntimeException(final String message) {
        super(message);
    }
}
