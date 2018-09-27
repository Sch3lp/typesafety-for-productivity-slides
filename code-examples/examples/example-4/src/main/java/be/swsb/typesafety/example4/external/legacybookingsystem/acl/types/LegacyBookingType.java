package be.swsb.typesafety.example4.external.legacybookingsystem.acl.types;

//This is supposed to represent an XSD generated class
//So just imagine a bunch of @XmlElement annotations or whatever
public class LegacyBookingType {

    //minOccurs = 0
    private String bookingCode;

    public LegacyBookingType() {
    }

    public void setCode(final String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final LegacyBookingType that = (LegacyBookingType) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(bookingCode, that.bookingCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(bookingCode)
                .toHashCode();
    }
}
