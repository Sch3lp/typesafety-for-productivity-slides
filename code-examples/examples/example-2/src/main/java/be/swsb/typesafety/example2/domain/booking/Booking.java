package be.swsb.typesafety.example2.domain.booking;

import be.swsb.typesafety.example2.domain.DomainValidationRuntimeException;

import java.util.UUID;
import java.util.regex.Pattern;

public class Booking {
    private UUID id;
    private String bookingCode;

    //imagine a bunch of other fields

    private Booking(final String bookingCode) {
        this.id = UUID.randomUUID();
        this.bookingCode = bookingCode;
        validate();
    }

    private void validate() {
        if (bookingCode != null &&
                !Pattern.matches("[0-9]{1,8}", bookingCode)) {
            throw new DomainValidationRuntimeException("A Booking can only be created with a code that's at most 8 numerical characters");
        }
    }

    public static Booking booking(final String bookingCode) {
        return new Booking(bookingCode);
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public UUID getId() {
        return id;
    }
}
