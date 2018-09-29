package be.swsb.typesafety.example5.external.legacybookingsystem.acl.client;

import be.swsb.typesafety.example5.external.legacybookingsystem.acl.types.LegacyBinder;

public interface LegacyClient {
    void sendXml(LegacyBinder binder);
}
