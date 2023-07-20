package com.daimler.integration.boundary.clients.rest;

import com.daimler.integration.entity.TargetMitglied;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Named("Targetdapter")
@Log
public final class TargetAdapter {

  @RestClient TargetMitgliedClient client;

  public void syncMitglied(@NonNull TargetMitglied mitglied) {
    client.syncMitglied(mitglied);
  }
}
