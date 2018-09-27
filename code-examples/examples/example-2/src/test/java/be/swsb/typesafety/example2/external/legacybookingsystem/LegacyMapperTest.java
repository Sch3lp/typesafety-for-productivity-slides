package be.swsb.typesafety.example2.external.legacybookingsystem;

import be.swsb.typesafety.example2.domain.booking.Booking;
import be.swsb.typesafety.example2.domain.booking.BookingTestBuilder;
import be.swsb.typesafety.example2.external.legacybookingsystem.acl.types.LegacyBookingType;
import be.swsb.typesafety.test.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LegacyMapperTest extends UnitTest {

    private LegacyMapper mapper = new LegacyMapper();

    @Test
    void toXml() {
        final String bookingCode = "=-99hjkhdsf7876t23";
        final Booking booking = BookingTestBuilder.defaultBooking()
                .withBookingCode(bookingCode)
                .build();

        final LegacyBookingType legacyBookingType = mapper.toXml(booking);

        assertThat(legacyBookingType.getBookingCode()).isEqualTo(bookingCode);
    }
}