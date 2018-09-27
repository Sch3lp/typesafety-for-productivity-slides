package be.swsb.typesafety.example1.domain.booking;

import org.junit.jupiter.api.Test;

import static be.swsb.typesafety.example1.domain.booking.Booking.booking;

class BookingTest {

    @Test
    void bookingCodeIsOptional() {
        booking(null);
    }
}