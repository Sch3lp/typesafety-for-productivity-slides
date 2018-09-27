package be.swsb.typesafety.example4.domain;

public class DomainValidationRuntimeException extends RuntimeException {
    public DomainValidationRuntimeException(final String message) {
        super(message);
    }
}
