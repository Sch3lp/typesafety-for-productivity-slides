package be.swsb.typesafety.example5.domain.booking

import java.util.*

class Booking

private constructor(val id: UUID = UUID.randomUUID(),
                    val bookingCode: BookingCode?) {
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
