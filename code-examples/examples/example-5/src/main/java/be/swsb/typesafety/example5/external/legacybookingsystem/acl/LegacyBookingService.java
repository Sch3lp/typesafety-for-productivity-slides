package be.swsb.typesafety.example5.external.legacybookingsystem.acl;

import be.swsb.typesafety.example5.domain.booking.Booking;
import be.swsb.typesafety.example5.domain.booking.BookingRepository;
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.mapper.LegacyMapper;
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.client.LegacyClient;
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.types.LegacyBinder;
import be.swsb.typesafety.example5.external.legacybookingsystem.acl.types.LegacyBookingType;

import java.util.List;
import java.util.stream.Collectors;

public class LegacyBookingService {

    private final LegacyMapper mapper;
    private final LegacyClient legacyClient;
    private final BookingRepository repo;

    public LegacyBookingService(final BookingRepository repo,
                                final LegacyClient legacyClient,
                                final LegacyMapper mapper) {
        this.repo = repo;
        this.legacyClient = legacyClient;
        this.mapper = mapper;
    }

    public void createBookingRecord() {
        List<Booking> bookings = repo.findUnsentBookings();
        List<LegacyBookingType> legacyBookings = bookings.stream().map(mapper::toXml).collect(Collectors.toList());

        LegacyBinder binder = LegacyBinder.collect(legacyBookings);

        legacyClient.sendXml(binder);
    }
}
