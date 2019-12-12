package com.lucidworks.fusion.connector.plugin;

import com.google.inject.AbstractModule;
import com.lucidworks.fusion.connector.plugin.api.plugin.ConnectorPlugin;
import com.lucidworks.fusion.connector.plugin.api.plugin.ConnectorPluginProvider;
import com.lucidworks.fusion.connector.plugin.client.ImapClient;
import com.lucidworks.fusion.connector.plugin.client.ImapStore;
import com.lucidworks.fusion.connector.plugin.client.impl.JavaxImapStore;

public class ImapPlugin implements ConnectorPluginProvider {

  @Override
  public ConnectorPlugin get() {
    AbstractModule module = new AbstractModule() {
      @Override
      protected void configure() {
        bind(ImapClient.class).asEagerSingleton();
        bind(ImapStore.class)
            .to(JavaxImapStore.class)
            .asEagerSingleton();
      }
    };
    return ConnectorPlugin.builder(ImapConfig.class)
        .withFetcher(ImapFetcher.class, module)
        .withValidator(ImapConfigValidator.class)
        .build();
  }
}
