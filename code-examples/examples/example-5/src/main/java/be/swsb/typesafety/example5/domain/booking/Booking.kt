package be.swsb.typesafety.example5.domain.booking

import java.time.LocalDateTime
import java.util.*

class Booking(val id: UUID = UUID.randomUUID(),
              val bookingCode: BookingCode? = null,
              val reservationDate: LocalDateTime = LocalDateTime.now()) {
    //imagine a bunch of other fields

    fun getBookingCodeAsString(): String? {
        return bookingCode?.asString()
    }

    companion object {
        fun booking(bookingCode: BookingCode?): Booking {
            return Booking(bookingCode = bookingCode)
        }
    }
}
