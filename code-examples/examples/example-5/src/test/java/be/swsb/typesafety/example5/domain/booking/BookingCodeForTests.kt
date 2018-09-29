package be.swsb.typesafety.example5.domain.booking

import org.apache.commons.lang3.RandomStringUtils

fun BookingCode.Companion.randomBookingCode(): BookingCode? {
    return BookingCode.bookingCode(RandomStringUtils.randomNumeric(8))
}