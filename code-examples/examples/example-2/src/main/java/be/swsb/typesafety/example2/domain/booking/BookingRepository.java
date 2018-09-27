package be.swsb.typesafety.example2.domain.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingRepository {

    private List<Booking> bookings = new ArrayList<>();

    public UUID save(final Booking booking) {
        //booking is saved to a database or whatever
        bookings.add(booking);
        return booking.getId();
    }

    public List<Booking> findUnsentBookings() {
        return bookings;
    }
}
