package be.swsb.typesafety.example4.domain.booking;

import be.swsb.typesafety.example4.domain.DomainValidationRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Optional;
import java.util.regex.Pattern;

public class BookingCode {
    private final String bookingCode;

    private BookingCode(final String bookingCode) {
        this.bookingCode = bookingCode;
        validate();
    }

    public static Optional<BookingCode> bookingCode(final String bookingCode) {
        if (StringUtils.isEmpty(bookingCode)) {
            return Optional.empty();
        }
        return Optional.of(new BookingCode(bookingCode));
    }

    private void validate() {
        if (bookingCode != null &&
                !Pattern.matches("[0-9]{1,8}", bookingCode)) {
            throw new DomainValidationRuntimeException("A BookingCode should be at most 8 numerical characters");
        }
    }

    public String asString() {
        return bookingCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final BookingCode that = (BookingCode) o;

        return new EqualsBuilder()
                .append(bookingCode, that.bookingCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(bookingCode)
                .toHashCode();
    }
}
