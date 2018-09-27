package be.swsb.typesafety.example4.external.legacybookingsystem;

import be.swsb.typesafety.example4.domain.booking.Booking;
import be.swsb.typesafety.example4.domain.booking.BookingCode;
import be.swsb.typesafety.example4.domain.booking.BookingTestBuilder;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.types.LegacyBookingType;
import be.swsb.typesafety.test.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.swsb.typesafety.example4.domain.booking.BookingCodeForTests.randomBookingCode;
import static be.swsb.typesafety.example4.domain.booking.BookingTestBuilder.defaultBooking;
import static org.assertj.core.api.Assertions.assertThat;

class LegacyMapperTest extends UnitTest {

    private LegacyMapper mapper = new LegacyMapper();

    @Test
    void toXml() {
        final Optional<BookingCode> bookingCode = randomBookingCode();
        final Booking booking = defaultBooking()
                .withBookingCode(bookingCode)
                .build();

        final LegacyBookingType legacyBookingType = mapper.toXml(booking);

        assertThat(legacyBookingType.getBookingCode()).isEqualTo(bookingCode.get().asString());
    }

    @Test
    void toXml_BookingCodeIsEmpty_NoCodeOnXml() {
        final Booking booking = defaultBooking()
                .withBookingCode(Optional.empty())
                .build();

        final LegacyBookingType legacyBookingType = mapper.toXml(booking);

        assertThat(legacyBookingType.getBookingCode()).isNull();
    }
}