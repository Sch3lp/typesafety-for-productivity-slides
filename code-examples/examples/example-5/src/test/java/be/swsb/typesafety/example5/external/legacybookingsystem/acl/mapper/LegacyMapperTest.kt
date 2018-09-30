package be.swsb.typesafety.example5.external.legacybookingsystem.acl.mapper

import be.swsb.typesafety.example5.domain.booking.Booking
import be.swsb.typesafety.example5.domain.booking.BookingCode
import be.swsb.typesafety.example5.domain.booking.randomBookingCode
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.mapper.LegacyMapper
import be.swsb.typesafety.test.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LegacyMapperTest : UnitTest() {

    private val mapper = LegacyMapper()

    @Test
    fun toXml() {
        val bookingCode = BookingCode.randomBookingCode()
        val booking = Booking.booking(bookingCode)

        val legacyBookingType = mapper.toXml(booking)

        assertThat(legacyBookingType.bookingCode).isEqualTo(bookingCode?.asString())
    }

    @Test
    fun toXml_BookingCodeIsEmpty_NoCodeOnXml() {
        val booking = Booking.booking(null)

        val legacyBookingType = mapper.toXml(booking)

        assertThat(legacyBookingType.bookingCode).isNull()
    }
}