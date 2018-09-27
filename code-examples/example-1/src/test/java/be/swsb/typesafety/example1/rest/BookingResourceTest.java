package be.swsb.typesafety.example1.rest;

import be.swsb.typesafety.example1.rest.booking.BookingResource;
import be.swsb.typesafety.example1.service.booking.BookingService;
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

        verify(service).createNewBooking(bookingCode);
    }
}