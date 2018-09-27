package be.swsb.typesafety.example4.service.booking;

import be.swsb.typesafety.example4.domain.booking.Booking;
import be.swsb.typesafety.example4.domain.booking.BookingCode;
import be.swsb.typesafety.example4.domain.booking.BookingRepository;
import be.swsb.typesafety.example4.service.booking.BookingService;
import be.swsb.typesafety.test.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static be.swsb.typesafety.example4.domain.booking.BookingCodeForTests.randomBookingCode;
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
        final UUID randomUUID = UUID.randomUUID();
        when(repository.save(bookingCaptor.capture())).thenReturn(randomUUID);

        final Optional<BookingCode> bookingCode = randomBookingCode();
        final UUID newlyCreatedBookingId = bookingService.createNewBooking(bookingCode);

        assertThat(newlyCreatedBookingId).isEqualTo(randomUUID);

        final Booking createdBooking = bookingCaptor.getValue();

        assertThat(createdBooking.getBookingCode()).isEqualTo(bookingCode);
    }

    // What if bookingCode is null? --> dealt with in BookingCode wrapper
    // What if bookingCode is empty? --> dealt with in BookingCode wrapper
}