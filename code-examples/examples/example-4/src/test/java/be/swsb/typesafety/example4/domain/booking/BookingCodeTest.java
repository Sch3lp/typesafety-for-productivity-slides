package be.swsb.typesafety.example4.domain.booking;

import be.swsb.typesafety.example4.domain.DomainValidationRuntimeException;
import be.swsb.typesafety.example4.domain.booking.BookingCode;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.swsb.typesafety.example4.domain.booking.BookingCode.bookingCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookingCodeTest {

    @Test
    void bookingCodeMustBe8Numerical() {
        bookingCode("12345678");
        assertThatThrownBy(() -> bookingCode("123456789"))
                .isInstanceOf(DomainValidationRuntimeException.class)
                .hasMessage("A BookingCode should be at most 8 numerical characters");
        assertThatThrownBy(() -> bookingCode("12ab5678"))
                .isInstanceOf(DomainValidationRuntimeException.class)
                .hasMessage("A BookingCode should be at most 8 numerical characters");
    }

    @Test
    void bookingCodeWithNull_ReturnsEmpty() {
        assertThat(bookingCode(null)).isEmpty();
    }

    @Test
    void bookingCodeWithEmpty_ReturnsEmpty() {
        assertThat(bookingCode("")).isEmpty();
    }

    @Test
    void bookingCodesAreEqualWhenCodesAreEqual() {
        final Optional<BookingCode> bookingCode1 = bookingCode("09876543");
        final Optional<BookingCode> bookingCode2 = bookingCode("09876543");
        final Optional<BookingCode> bookingCode3 = bookingCode("12345678");

        assertThat(bookingCode1.get().equals(bookingCode2.get())).isTrue();
        assertThat(bookingCode2.get().equals(bookingCode1.get())).isTrue();
        assertThat(bookingCode2.get().equals(bookingCode3.get())).isFalse();
        assertThat(bookingCode3.get().equals(bookingCode1.get())).isFalse();
        assertThat(bookingCode1.get().equals(null)).isFalse();
    }
}