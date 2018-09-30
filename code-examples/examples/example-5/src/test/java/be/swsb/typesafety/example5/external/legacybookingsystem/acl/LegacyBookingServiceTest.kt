package be.swsb.typesafety.example5.external.legacybookingsystem.acl

import be.swsb.typesafety.example5.domain.booking.Booking
import be.swsb.typesafety.example5.domain.booking.Booking.Companion.booking
import be.swsb.typesafety.example5.domain.booking.BookingCode
import be.swsb.typesafety.example5.domain.booking.BookingRepository
import be.swsb.typesafety.example5.domain.booking.randomBookingCode
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.mapper.LegacyMapper
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.client.LegacyClient
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.types.LegacyBinder
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.types.LegacyBookingTypeTestBuilder.defaultLegacyBookingType
import be.swsb.typesafety.test.UnitTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class LegacyBookingServiceTest : UnitTest() {

    private lateinit var legacyBookingService: LegacyBookingService

    private val mapper: LegacyMapper = mock()
    private val legacyClient: LegacyClient = mock()
    private val repo: BookingRepository = mock()

    @BeforeEach
    internal fun setUp() {
        legacyBookingService = LegacyBookingService(repo, legacyClient, mapper)
    }

    @Test
    fun createBookingRecord_SendsAllBookingsAsXml() {
        val booking1 = defaultLegacyBookingType().withCode("12345678").build()
        val booking2 = defaultLegacyBookingType().withCode("87654321").build()
        val mappedBookings = Arrays.asList(booking1, booking2)
        val expectedBinder = LegacyBinder.collect(mappedBookings)

        val bookingToBeSent1 = booking(BookingCode.randomBookingCode())
        val bookingToBeSent2 = booking(BookingCode.randomBookingCode())
        val bookings = Arrays.asList<Booking>(bookingToBeSent1, bookingToBeSent2)
        whenever(repo.findUnsentBookings()).thenReturn(bookings)

        whenever(mapper.toXml(any()))
                .thenReturn(booking1)
                .thenReturn(booking2)

        legacyBookingService.createBookingRecord()

        verify(legacyClient).sendXml(expectedBinder)
    }
}