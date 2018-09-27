package be.swsb.typesafety.example4.rest.booking;

import be.swsb.typesafety.example4.domain.booking.BookingCode;
import be.swsb.typesafety.example4.rest.booking.BookingResource;
import be.swsb.typesafety.example4.service.booking.BookingService;
import be.swsb.typesafety.test.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static be.swsb.typesafety.example4.domain.booking.BookingCode.bookingCode;
import static org.mockito.Mockito.verify;

class BookingResourceTest extends UnitTest {

    @InjectMocks
    private BookingResource resource;

    @Mock
    private BookingService service;

    @Test
    void saveBooking_ShouldCreateANewBookingAndReturnItsUUID() {
        final String bookingCode = "12345678";

        resource.saveBooking(bookingCode);

        verify(service).createNewBooking(bookingCode(bookingCode));
    }

    // What if bookingCode is null? --> taken care of by Wrapper
    // What if bookingCode is empty? --> taken care of by Wrapper
}