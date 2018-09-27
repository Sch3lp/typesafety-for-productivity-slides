package be.swsb.typesafety.example4.domain.booking;

import java.util.Optional;

import static be.swsb.typesafety.example4.domain.booking.BookingCodeForTests.randomBookingCode;

public class BookingTestBuilder {
    private Optional<BookingCode> bookingCode;

    private BookingTestBuilder() {
    }

    public static BookingTestBuilder booking() {
        return new BookingTestBuilder();
    }

    public static BookingTestBuilder defaultBooking() {
        return booking().withBookingCode(randomBookingCode());
    }

    public Booking build() {
        final Booking booking = Booking.booking(bookingCode);
        return booking;
    }

    public BookingTestBuilder withBookingCode(final Optional<BookingCode> bookingCode) {
        this.bookingCode = bookingCode;
        return this;
    }
}