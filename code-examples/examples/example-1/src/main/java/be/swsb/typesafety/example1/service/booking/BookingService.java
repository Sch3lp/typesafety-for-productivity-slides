package be.swsb.typesafety.example1.service.booking;

import be.swsb.typesafety.example1.domain.booking.Booking;
import be.swsb.typesafety.example1.domain.booking.BookingRepository;

import java.util.UUID;

import static be.swsb.typesafety.example1.domain.booking.Booking.booking;

public class BookingService {

    private final BookingRepository repo;

    public BookingService(final BookingRepository repo) {
        this.repo = repo;
    }

    public UUID createNewBooking(final String bookingCode) {
        Booking booking = booking(bookingCode);
        return repo.save(booking);
    }
}
