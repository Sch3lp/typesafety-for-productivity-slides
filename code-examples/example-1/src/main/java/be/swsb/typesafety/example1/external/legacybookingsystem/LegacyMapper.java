package be.swsb.typesafety.example1.external.legacybookingsystem;

import be.swsb.typesafety.example1.domain.booking.Booking;
import be.swsb.typesafety.example1.external.legacybookingsystem.acl.types.LegacyBookingType;

public class LegacyMapper {

    public LegacyBookingType toXml(final Booking booking) {
        final LegacyBookingType legacyBookingType = new LegacyBookingType();
        legacyBookingType.setCode(booking.getBookingCode());
        return legacyBookingType;
    }
}
