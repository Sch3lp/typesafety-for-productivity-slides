package be.swsb.typesafety.example5.external.legacybookingsystem

import be.swsb.typesafety.example5.domain.booking.Booking
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.types.LegacyBookingType

open class LegacyMapper {

    open fun toXml(booking: Booking): LegacyBookingType {
        val legacyBookingType = LegacyBookingType()
        legacyBookingType.setCode(booking.getBookingCodeAsString())
        return legacyBookingType
    }
}
