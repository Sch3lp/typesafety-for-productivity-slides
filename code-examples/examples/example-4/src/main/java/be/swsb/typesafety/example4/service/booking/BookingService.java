package be.swsb.typesafety.example4.service.booking;

import be.swsb.typesafety.example4.domain.booking.Booking;
import be.swsb.typesafety.example4.domain.booking.BookingCode;
import be.swsb.typesafety.example4.domain.booking.BookingRepository;

import java.util.Optional;
import java.util.UUID;

import static be.swsb.typesafety.example4.domain.booking.Booking.booking;

public class BookingService {

    private final BookingRepository repo;

    public BookingService(final BookingRepository repo) {
        this.repo = repo;
    }

    public UUID createNewBooking(final Optional<BookingCode> bookingCode) {
        Booking booking = booking(bookingCode);
        return repo.save(booking);
    }
}
