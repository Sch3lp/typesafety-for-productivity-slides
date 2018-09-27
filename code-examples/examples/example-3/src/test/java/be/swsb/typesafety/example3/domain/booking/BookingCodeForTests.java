package be.swsb.typesafety.example3.domain.booking;

import org.apache.commons.lang3.RandomStringUtils;

public class BookingCodeForTests {

    public static BookingCode randomBookingCode() {
        return BookingCode.bookingCode(RandomStringUtils.randomNumeric(8));
    }
}