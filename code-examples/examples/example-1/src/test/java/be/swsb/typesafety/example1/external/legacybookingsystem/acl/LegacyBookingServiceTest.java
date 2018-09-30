package be.swsb.typesafety.example1.external.legacybookingsystem.acl;

import be.swsb.typesafety.example1.domain.booking.Booking;
import be.swsb.typesafety.example1.domain.booking.BookingRepository;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.mapper.LegacyMapper;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.types.LegacyBinder;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.types.LegacyBookingType;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.types.LegacyBookingTypeTestBuilder;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.client.LegacyClient;
import be.swsb.typesafety.test.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static be.swsb.typesafety.example1.domain.booking.BookingTestBuilder.defaultBooking;
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
        final LegacyBookingType booking1 = LegacyBookingTypeTestBuilder.defaultLegacyBookingType().withCode("oaudf87932").build();
        final LegacyBookingType booking2 = LegacyBookingTypeTestBuilder.defaultLegacyBookingType().withCode("oaudf87932").build();
        final List<LegacyBookingType> mappedBookings = Arrays.asList(booking1, booking2);
        final LegacyBinder expectedBinder = LegacyBinder.collect(mappedBookings);

        final Booking bookingToBeSent1 = defaultBooking().withBookingCode("9786sdf").build();
        final Booking bookingToBeSent2 = defaultBooking().withBookingCode("978iasudf80ufda").build();
        final List<Booking> bookings = Arrays.asList(bookingToBeSent1, bookingToBeSent2);
        when(repo.findUnsentBookings()).thenReturn(bookings);

        when(mapper.toXml(any(Booking.class)))
                .thenReturn(booking1)
                .thenReturn(booking2);

        legacyBookingService.createBookingRecord();

        verify(legacyClient).sendXml(expectedBinder);
    }
}