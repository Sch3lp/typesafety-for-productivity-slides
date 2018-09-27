package be.swsb.typesafety.example1.external.legacybookingsystem.acl.types;

public class LegacyBookingTypeTestBuilder {

    private String code;

    private LegacyBookingTypeTestBuilder() {
    }

    public static LegacyBookingTypeTestBuilder legacyBookingType() {
        return new LegacyBookingTypeTestBuilder();
    }

    public static LegacyBookingTypeTestBuilder defaultLegacyBookingType() {
        return legacyBookingType();
    }

    public LegacyBookingType build() {
        final LegacyBookingType legacyBookingType = new LegacyBookingType();
        legacyBookingType.setCode(code);
        return legacyBookingType;
    }

    public LegacyBookingTypeTestBuilder withCode(final String code) {
        this.code = code;
        return this;
    }
}