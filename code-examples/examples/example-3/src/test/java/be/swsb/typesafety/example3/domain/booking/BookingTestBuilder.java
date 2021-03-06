package be.swsb.typesafety.example3.domain.booking;

public class BookingTestBuilder {
    private BookingCode bookingCode;

    private BookingTestBuilder() {
    }

    public static BookingTestBuilder booking() {
        return new BookingTestBuilder();
    }

    public static BookingTestBuilder defaultBooking() {
        return booking().withBookingCode("kjadfooijasdf");
    }

    public Booking build() {
        final Booking booking = Booking.booking(bookingCode);
        return booking;
    }

    public BookingTestBuilder withBookingCode(final String bookingCode) {
        this.bookingCode = BookingCode.bookingCode(bookingCode);
        return this;
    }
}