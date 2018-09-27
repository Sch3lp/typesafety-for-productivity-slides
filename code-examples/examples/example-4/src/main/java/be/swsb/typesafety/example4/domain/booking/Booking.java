package be.swsb.typesafety.example4.domain.booking;

import java.util.Optional;
import java.util.UUID;

public class Booking {
    private UUID id;
    private Optional<BookingCode> bookingCode;

    //imagine a bunch of other fields

    private Booking(final Optional<BookingCode> bookingCode) {
        this.id = UUID.randomUUID();
        this.bookingCode = bookingCode;
    }

    public static Booking booking(final Optional<BookingCode> bookingCode) {
        return new Booking(bookingCode);
    }

    public Optional<BookingCode> getBookingCode() {
        return bookingCode;
    }

    public Optional<String> getBookingCodeAsString() {
        return bookingCode.map(BookingCode::asString);
    }

    public UUID getId() {
        return id;
    }
}
