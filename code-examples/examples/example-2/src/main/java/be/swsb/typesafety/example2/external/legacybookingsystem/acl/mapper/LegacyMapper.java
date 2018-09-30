package be.swsb.typesafety.example2.external.legacybookingsystem.acl.mapper;

import be.swsb.typesafety.example2.domain.booking.Booking;
import be.swsb.typesafety.example2.external.legacybookingsystem.acl.types.LegacyBookingType;

public class LegacyMapper {

    public LegacyBookingType toXml(final Booking booking) {
        final LegacyBookingType legacyBookingType = new LegacyBookingType();
        legacyBookingType.setCode(booking.getBookingCode());
        return legacyBookingType;
    }
}
