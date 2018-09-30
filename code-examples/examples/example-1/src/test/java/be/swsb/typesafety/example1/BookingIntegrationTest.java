package be.swsb.typesafety.example1;

import be.swsb.typesafety.example1.domain.booking.BookingRepository;
import be.swsb.typesafety.example1.rest.booking.BookingResource;
import be.swsb.typesafety.example1.service.booking.BookingService;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.mapper.LegacyMapper;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.LegacyBookingService;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.client.LegacyClient;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.types.LegacyBinder;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.types.LegacyBookingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookingIntegrationTest {

    private BookingResource bookingResource;
    private LegacyBookingService legacyBookingService;
    private LegacyClientForTesting legacyClient;

    @BeforeEach
    void setUp() {
        BookingRepository repo = new BookingRepository();
        final BookingService bookingService = new BookingService(repo);
        final LegacyMapper mapper = new LegacyMapper();

        bookingResource = new BookingResource(bookingService);
        legacyClient = new LegacyClientForTesting();
        legacyBookingService = new LegacyBookingService(repo, legacyClient, mapper);
    }

    @Test
    void savingACoupleOfBooks_AndSendingThem() {
        bookingResource.saveBooking("9876kjadsf");
        bookingResource.saveBooking("snarfsnarf");
        bookingResource.saveBooking("Lion-O");
        legacyBookingService.createBookingRecord();

        assertThat(legacyClient.binders.get(0).getXmlbookings())
                .extracting(LegacyBookingType::getBookingCode)
                .containsExactly("9876kjadsf","snarfsnarf","Lion-O");
    }

    private class LegacyClientForTesting implements LegacyClient {
        private List<LegacyBinder> binders = new ArrayList<>();

        @Override
        public void sendXml(final LegacyBinder binder) {
            binders.add(binder);
        }
    }
}
