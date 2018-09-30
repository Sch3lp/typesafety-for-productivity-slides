package be.swsb.typesafety.example4;

import be.swsb.typesafety.example4.domain.booking.BookingRepository;
import be.swsb.typesafety.example4.rest.booking.BookingResource;
import be.swsb.typesafety.example4.service.booking.BookingService;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.mapper.LegacyMapper;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.LegacyBookingService;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.client.LegacyClient;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.types.LegacyBinder;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.types.LegacyBookingType;
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
        bookingResource.saveBooking("12345678");
        bookingResource.saveBooking("01928374");
        bookingResource.saveBooking("09876543");

        legacyBookingService.createBookingRecord();

        assertThat(legacyClient.binders.get(0).getXmlbookings())
                .extracting(LegacyBookingType::getBookingCode)
                .containsExactly("12345678", "01928374", "09876543");
    }

    private class LegacyClientForTesting implements LegacyClient {
        private List<LegacyBinder> binders = new ArrayList<>();

        @Override
        public void sendXml(final LegacyBinder binder) {
            binders.add(binder);
        }
    }
}
