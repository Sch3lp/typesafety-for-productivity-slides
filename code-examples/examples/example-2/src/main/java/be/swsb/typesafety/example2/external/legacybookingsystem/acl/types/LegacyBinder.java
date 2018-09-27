package be.swsb.typesafety.example2.external.legacybookingsystem.acl.types;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class LegacyBinder {
    private final List<LegacyBookingType> xmlbookings;

    public static LegacyBinder collect(final List<LegacyBookingType> xmlbookings) {
        return new LegacyBinder(xmlbookings);
    }

    private LegacyBinder(final List<LegacyBookingType> xmlbookings) {
        this.xmlbookings = xmlbookings;
    }

    public List<LegacyBookingType> getXmlbookings() {
        return xmlbookings;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final LegacyBinder that = (LegacyBinder) o;

        return new EqualsBuilder()
                .append(xmlbookings, that.xmlbookings)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(xmlbookings)
                .toHashCode();
    }
}
