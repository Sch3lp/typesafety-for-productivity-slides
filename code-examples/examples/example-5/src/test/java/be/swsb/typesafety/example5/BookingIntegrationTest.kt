package be.swsb.typesafety.example5

import be.swsb.typesafety.example5.domain.booking.BookingRepository
import be.swsb.typesafety.example5.external.legacybookingsystem.LegacyMapper
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.LegacyBookingService
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.client.LegacyClient
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.types.LegacyBinder
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.types.LegacyBookingType
import be.swsb.typesafety.example5.rest.booking.BookingResource
import be.swsb.typesafety.example5.service.booking.BookingService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class BookingIntegrationTest {

    private lateinit var bookingResource: BookingResource
    private lateinit var legacyBookingService: LegacyBookingService
    private lateinit var legacyClient: LegacyClientForTesting

    @BeforeEach
    fun setUp() {
        val repo = BookingRepository()
        val bookingService = BookingService(repo)
        val mapper = LegacyMapper()

        bookingResource = BookingResource(bookingService)
        legacyClient = LegacyClientForTesting()
        legacyBookingService = LegacyBookingService(repo, legacyClient, mapper)
    }

    @Test
    fun savingACoupleOfBooks_AndSendingThem() {
        bookingResource.saveBooking("12345678")
        bookingResource.saveBooking("01928374")
        bookingResource.saveBooking("09876543")

        legacyBookingService.createBookingRecord()

        assertThat(legacyClient.binders[0].xmlbookings.map{ it.bookingCode })
                .containsExactly("12345678", "01928374", "09876543")
    }

    private inner class LegacyClientForTesting : LegacyClient {
        val binders = ArrayList<LegacyBinder>()

        override fun sendXml(binder: LegacyBinder) {
            binders.add(binder)
        }
    }
}
