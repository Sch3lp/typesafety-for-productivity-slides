package be.swsb.typesafety.example3.domain.booking;

import java.util.UUID;

public class Booking {
    private UUID id;
    private BookingCode bookingCode;

    //imagine a bunch of other fields

    private Booking(final BookingCode bookingCode) {
        this.id = UUID.randomUUID();
        this.bookingCode = bookingCode;
    }

    public static Booking booking(final BookingCode bookingCode) {
        return new Booking(bookingCode);
    }

    public BookingCode getBookingCode() {
        return bookingCode;
    }

    public String getBookingCodeAsString() {
        return bookingCode.asString();
    }

    public UUID getId() {
        return id;
    }
}
