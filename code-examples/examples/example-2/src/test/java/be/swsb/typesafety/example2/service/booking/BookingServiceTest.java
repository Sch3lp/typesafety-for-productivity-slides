package be.swsb.typesafety.example2.service.booking;

import be.swsb.typesafety.example2.domain.booking.Booking;
import be.swsb.typesafety.example2.domain.booking.BookingRepository;
import be.swsb.typesafety.example2.service.booking.BookingService;
import be.swsb.typesafety.test.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class BookingServiceTest extends UnitTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository repository;

    @Captor
    private ArgumentCaptor<Booking> bookingCaptor;

    @Test
    void createNewBooking_CreatesNewBooking_SavesIt_ReturnsGeneratedUUID() {
        final String bookingCode = "8974t8d4833dda";

        final UUID randomUUID = UUID.randomUUID();
        when(repository.save(bookingCaptor.capture())).thenReturn(randomUUID);

        final UUID newlyCreatedBookingId = bookingService.createNewBooking(bookingCode);

        assertThat(newlyCreatedBookingId).isEqualTo(randomUUID);

        final Booking createdBooking = bookingCaptor.getValue();

        assertThat(createdBooking.getBookingCode()).isEqualTo(bookingCode);
    }

    // What if bookingCode is null?
    // What if bookingCode is empty?
}