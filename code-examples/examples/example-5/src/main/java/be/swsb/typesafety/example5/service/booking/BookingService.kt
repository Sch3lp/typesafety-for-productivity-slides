package be.swsb.typesafety.example5.service.booking

import be.swsb.typesafety.example5.domain.booking.Booking.Companion.booking
import be.swsb.typesafety.example5.domain.booking.BookingCode
import be.swsb.typesafety.example5.domain.booking.BookingRepository
import java.util.*

open class BookingService(private val repo: BookingRepository) {

    open fun createNewBooking(bookingCode: BookingCode?): UUID {
        val booking = booking(bookingCode)
        return repo.save(booking)
    }
}
