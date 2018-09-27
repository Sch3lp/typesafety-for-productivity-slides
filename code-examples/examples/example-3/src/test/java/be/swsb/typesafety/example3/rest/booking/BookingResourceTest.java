package be.swsb.typesafety.example3.rest.booking;

import be.swsb.typesafety.example3.domain.booking.BookingCode;
import be.swsb.typesafety.example3.rest.booking.BookingResource;
import be.swsb.typesafety.example3.service.booking.BookingService;
import be.swsb.typesafety.test.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class BookingResourceTest extends UnitTest {

    @InjectMocks
    private BookingResource resource;

    @Mock
    private BookingService service;

    @Test
    void saveBooking_ShouldCreateANewBookingAndReturnItsUUID() {
        final String bookingCode = "12345678909876543";

        resource.saveBooking(bookingCode);

        verify(service).createNewBooking(BookingCode.bookingCode(bookingCode));
    }

    // What if bookingCode is null?
    // What if bookingCode is empty?
}