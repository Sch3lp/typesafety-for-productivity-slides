package be.swsb.typesafety.example1.external.legacybookingsystem;

import be.swsb.typesafety.example1.domain.booking.Booking;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.types.LegacyBookingType;
import be.swsb.typesafety.test.UnitTest;
import org.junit.jupiter.api.Test;

import static be.swsb.typesafety.example1.domain.booking.BookingTestBuilder.defaultBooking;
import static org.assertj.core.api.Assertions.assertThat;

class LegacyMapperTest extends UnitTest {

    private LegacyMapper mapper = new LegacyMapper();

    @Test
    void toXml() {
        final String bookingCode = "=-99hjkhdsf7876t23";
        final Booking booking = defaultBooking()
                .withBookingCode(bookingCode)
                .build();

        final LegacyBookingType legacyBookingType = mapper.toXml(booking);

        assertThat(legacyBookingType.getBookingCode()).isEqualTo(bookingCode);
    }
}