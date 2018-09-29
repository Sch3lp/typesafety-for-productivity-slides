package be.swsb.typesafety.example5.domain.booking

import java.util.*

open class BookingRepository {

    private val bookings = ArrayList<Booking>()

    open fun save(booking: Booking): UUID {
        bookings.add(booking)
        return booking.id
    }

    open fun findUnsentBookings(): List<Booking> {
        return bookings
    }
}
