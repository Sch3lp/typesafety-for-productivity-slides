package be.swsb.typesafety.example3.external.legacybookingsystem;

import be.swsb.typesafety.example3.domain.booking.Booking;
import be.swsb.typesafety.example3.external.legacybookingsystem.acl.types.LegacyBookingType;

public class LegacyMapper {

    public LegacyBookingType toXml(final Booking booking) {
        final LegacyBookingType legacyBookingType = new LegacyBookingType();
        legacyBookingType.setCode(booking.getBookingCodeAsString());
        return legacyBookingType;
    }
}
