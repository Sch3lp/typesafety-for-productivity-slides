package be.swsb.typesafety.example5.service.booking

import be.swsb.typesafety.example5.domain.booking.Booking
import be.swsb.typesafety.example5.domain.booking.BookingCode
import be.swsb.typesafety.example5.domain.booking.BookingRepository
import be.swsb.typesafety.example5.domain.booking.randomBookingCode
import be.swsb.typesafety.test.UnitTest
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class BookingServiceTest : UnitTest() {

    private lateinit var bookingService: BookingService

    private val repository: BookingRepository  = mock()
    private val bookingCaptor: KArgumentCaptor<Booking> = argumentCaptor()

    @BeforeEach
    internal fun setUp() {
        bookingService = BookingService(repository)
    }

    @Test
    fun createNewBooking_CreatesNewBooking_SavesIt_ReturnsGeneratedUUID() {
        val randomUUID = UUID.randomUUID()
        whenever(repository.save(bookingCaptor.capture())).thenReturn(randomUUID)

        val bookingCode = BookingCode.randomBookingCode()
        val newlyCreatedBookingId = bookingService.createNewBooking(bookingCode)

        assertThat(newlyCreatedBookingId).isEqualTo(randomUUID)

        val createdBooking = bookingCaptor.firstValue

        assertThat(createdBooking.bookingCode).isEqualTo(bookingCode)
    }
}