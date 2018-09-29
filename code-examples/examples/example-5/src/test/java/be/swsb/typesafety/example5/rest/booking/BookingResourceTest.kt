package be.swsb.typesafety.example5.rest.booking

import be.swsb.typesafety.example5.domain.booking.Booking
import be.swsb.typesafety.example5.domain.booking.BookingCode
import be.swsb.typesafety.example5.service.booking.BookingService
import be.swsb.typesafety.test.UnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class BookingResourceTest : UnitTest() {

    private lateinit var resource: BookingResource

    private val service: BookingService = mock()

    @BeforeEach
    internal fun setUp() {
        resource = BookingResource(service)
    }

    @Test
    fun saveBooking_ShouldCreateANewBookingAndReturnItsUUID() {
        val bookingCode = "12345678"

        val newUUID = UUID.randomUUID()
        whenever(service.createNewBooking(BookingCode.bookingCode(bookingCode))).thenReturn(newUUID)

        val actual = resource.saveBooking(bookingCode)

        assertThat(actual).isEqualTo(newUUID)
    }

    @Test
    fun chainingOptionalValues() {
        val recurringReservation = RecurringReservation()

        recurringReservation.booking?.bookingCode?.asString()
    }

    class RecurringReservation(val booking: Booking? = null)
}