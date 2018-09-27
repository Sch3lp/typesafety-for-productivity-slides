package be.swsb.typesafety.example1.rest.booking;

import be.swsb.typesafety.example1.service.booking.BookingService;

import java.util.UUID;

public class BookingResource {

    private final BookingService bookingService;

    public BookingResource(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public UUID saveBooking(String bookingCode) {
        return bookingService.createNewBooking(bookingCode);
    }
}
