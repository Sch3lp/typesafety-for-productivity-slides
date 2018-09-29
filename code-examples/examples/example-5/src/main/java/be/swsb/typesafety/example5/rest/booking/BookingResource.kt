package be.swsb.typesafety.example5.rest.booking

import be.swsb.typesafety.example5.domain.booking.BookingCode
import be.swsb.typesafety.example5.service.booking.BookingService
import java.util.*

class BookingResource(private val bookingService: BookingService) {

    // This method is being called from some HTML with an input type (without validation)
    // <input type="text" id="bookingCode" />
    // Imagine other fields like name, groupsize, ... being passed along as well in some JSON object
    fun saveBooking(bookingCode: String): UUID {
        return bookingService.createNewBooking(BookingCode.bookingCode(bookingCode))
    }
}
