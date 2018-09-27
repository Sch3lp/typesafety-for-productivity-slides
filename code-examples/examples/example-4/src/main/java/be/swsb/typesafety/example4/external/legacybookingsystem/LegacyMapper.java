package be.swsb.typesafety.example4.external.legacybookingsystem;

import be.swsb.typesafety.example4.domain.booking.Booking;
import be.swsb.typesafety.example4.external.legacybookingsystem.acl.types.LegacyBookingType;

public class LegacyMapper {

    public LegacyBookingType toXml(final Booking booking) {
        final LegacyBookingType legacyBookingType = new LegacyBookingType();
        legacyBookingType.setCode(booking.getBookingCodeAsString().orElse(null));
        return legacyBookingType;
    }
}
