package be.swsb.typesafety.example3.external.legacybookingsystem.acl.client;

import be.swsb.typesafety.example3.external.legacybookingsystem.acl.types.LegacyBinder;

public interface LegacyClient {
    void sendXml(LegacyBinder binder);
}
