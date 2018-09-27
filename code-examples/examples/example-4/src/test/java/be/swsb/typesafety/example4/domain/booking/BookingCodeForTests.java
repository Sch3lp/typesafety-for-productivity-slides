package be.swsb.typesafety.example4.domain.booking;

import be.swsb.typesafety.example4.domain.booking.BookingCode;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Optional;

public class BookingCodeForTests {

    public static Optional<BookingCode> randomBookingCode() {
        return BookingCode.bookingCode(RandomStringUtils.randomNumeric(8));
    }
}