package be.swsb.typesafety.example2.domain.booking;

import be.swsb.typesafety.example2.domain.DomainValidationRuntimeException;
import org.junit.jupiter.api.Test;

import static be.swsb.typesafety.example2.domain.booking.Booking.booking;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookingTest {

    @Test
    void bookingCodeMustBe8Numerical() {
        booking("12345678");
        assertThatThrownBy(() -> booking("123456789"))
                .isInstanceOf(DomainValidationRuntimeException.class)
                .hasMessage("A Booking can only be created with a code that's at most 8 numerical characters");
        assertThatThrownBy(() -> booking("12ab5678"))
                .isInstanceOf(DomainValidationRuntimeException.class)
                .hasMessage("A Booking can only be created with a code that's at most 8 numerical characters");
    }

    @Test
    void bookingCodeIsOptional() {
        booking(null);
    }
}