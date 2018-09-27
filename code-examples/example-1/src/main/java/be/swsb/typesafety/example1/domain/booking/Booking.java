package be.swsb.typesafety.example1.domain.booking;

import java.util.UUID;

public class Booking {
    private UUID id;
    private String bookingCode;

    //imagine a bunch of other fields

    private Booking(final String bookingCode) {
        this.id = UUID.randomUUID();
        this.bookingCode = bookingCode;
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
