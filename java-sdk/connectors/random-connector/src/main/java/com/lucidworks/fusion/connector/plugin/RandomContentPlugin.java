package com.lucidworks.fusion.connector.plugin;

import com.google.inject.AbstractModule;
import com.lucidworks.fusion.connector.plugin.api.plugin.ConnectorPlugin;
import com.lucidworks.fusion.connector.plugin.api.plugin.ConnectorPluginProvider;
import com.lucidworks.fusion.connector.plugin.impl.DefaultRandomContentGenerator;

public class RandomContentPlugin implements ConnectorPluginProvider {

  @Override
  public ConnectorPlugin get() {
    AbstractModule nonGenModule = new AbstractModule() {
      @Override
      protected void configure() {
        bind(RandomContentGenerator.class)
            .to(DefaultRandomContentGenerator.class)
            .asEagerSingleton();
      }
    };
    return ConnectorPlugin.builder(RandomContentConfig.class)
      .withFetcher("content", RandomContentFetcher.class, nonGenModule)
      .build();
  }
}
