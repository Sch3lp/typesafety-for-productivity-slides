package be.swsb.typesafety.example3.domain.booking;

import be.swsb.typesafety.example3.domain.DomainValidationRuntimeException;
import org.junit.jupiter.api.Test;

import static be.swsb.typesafety.example3.domain.booking.BookingCode.bookingCode;
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
    void bookingCodeWithNull_ReturnsNull() {
        assertThat(bookingCode(null)).isNull();
    }

    @Test
    void bookingCodeWithEmpty_ReturnsNull() {
        assertThat(bookingCode("")).isNull();
    }

    @Test
    void bookingCodesAreEqualWhenCodesAreEqual() {
        final BookingCode bookingCode1 = bookingCode("09876543");
        final BookingCode bookingCode2 = bookingCode("09876543");
        final BookingCode bookingCode3 = bookingCode("12345678");

        assertThat(bookingCode1.equals(bookingCode2)).isTrue();
        assertThat(bookingCode2.equals(bookingCode1)).isTrue();
        assertThat(bookingCode2.equals(bookingCode3)).isFalse();
        assertThat(bookingCode3.equals(bookingCode1)).isFalse();
        assertThat(bookingCode1.equals(null)).isFalse();
    }
}