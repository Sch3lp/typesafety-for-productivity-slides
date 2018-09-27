package be.swsb.typesafety.example3.rest.booking;

import be.swsb.typesafety.example3.domain.booking.BookingCode;
import be.swsb.typesafety.example3.service.booking.BookingService;

import java.util.UUID;

import static be.swsb.typesafety.example3.domain.booking.BookingCode.bookingCode;

public class BookingResource {

    private final BookingService bookingService;

    public BookingResource(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // This method is being called from some HTML with an input type (without validation)
    // <input type="text" id="bookingCode" />
    // Imagine other fields like name, groupsize, ... being passed along as well in some JSON object
    public UUID saveBooking(String bookingCode) {
        return bookingService.createNewBooking(bookingCode(bookingCode));
    }
}
