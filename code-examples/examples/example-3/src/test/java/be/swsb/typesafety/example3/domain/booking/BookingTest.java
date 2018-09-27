package be.swsb.typesafety.example3.domain.booking;

import org.junit.jupiter.api.Test;

import static be.swsb.typesafety.example3.domain.booking.Booking.booking;

class BookingTest {

    @Test
    void bookingCodeIsOptional() {
        booking(null);
    }
}