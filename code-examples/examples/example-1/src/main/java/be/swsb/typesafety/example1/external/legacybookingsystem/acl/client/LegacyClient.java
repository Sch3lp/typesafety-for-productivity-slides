package be.swsb.typesafety.example1.external.legacybookingsystem.acl.client;

import be.swsb.typesafety.example1.external.legacybookingsystem.acl.types.LegacyBinder;

public interface LegacyClient {
    void sendXml(LegacyBinder binder);
}
