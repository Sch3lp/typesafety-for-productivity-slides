package be.swsb.typesafety.example4.external.legacybookingsystem.acl;

import be.swsb.typesafety.example4.domain.booking.Booking;
import be.swsb.typesafety.example4.domain.booking.BookingRepository;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.mapper.LegacyMapper;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.types.LegacyBinder;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.types.LegacyBookingType;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.client.LegacyClient;
import be.swsb.typesafety.test.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static be.swsb.typesafety.example4.domain.booking.BookingCodeForTests.randomBookingCode;
import static be.swsb.typesafety.example4.domain.booking.BookingTestBuilder.defaultBooking;
import static be.swsb.typesafety.example4.external.legacybookingsystem.acl.types.LegacyBookingTypeTestBuilder.defaultLegacyBookingType;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LegacyBookingServiceTest extends UnitTest {

    @InjectMocks
    private LegacyBookingService legacyBookingService;

    @Mock
    private LegacyMapper mapper;
    @Mock
    private LegacyClient legacyClient;
    @Mock
    private BookingRepository repo;

    @Test
    void createBookingRecord_SendsAllBookingsAsXml() {
        final LegacyBookingType booking1 = defaultLegacyBookingType().withCode("12345678").build();
        final LegacyBookingType booking2 = defaultLegacyBookingType().withCode("87654321").build();
        final List<LegacyBookingType> mappedBookings = Arrays.asList(booking1, booking2);
        final LegacyBinder expectedBinder = LegacyBinder.collect(mappedBookings);

        final Booking bookingToBeSent1 = defaultBooking().withBookingCode(randomBookingCode()).build();
        final Booking bookingToBeSent2 = defaultBooking().withBookingCode(randomBookingCode()).build();
        final List<Booking> bookings = Arrays.asList(bookingToBeSent1, bookingToBeSent2);
        when(repo.findUnsentBookings()).thenReturn(bookings);

        when(mapper.toXml(any(Booking.class)))
                .thenReturn(booking1)
                .thenReturn(booking2);

        legacyBookingService.createBookingRecord();

        verify(legacyClient).sendXml(expectedBinder);
    }
}