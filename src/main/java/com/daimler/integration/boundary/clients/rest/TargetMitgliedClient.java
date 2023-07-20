package com.daimler.integration.boundary.clients.rest;

import com.daimler.integration.entity.TargetMitglied;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/target")
@RegisterRestClient(configKey = "target-client")
@ApplicationScoped
public interface TargetMitgliedClient {

  @POST
  @Path("/mitglied")
  @Consumes("application/json")
  @Retry(maxRetries = 2, delay = 1000)
  String syncMitglied(TargetMitglied mitglied);
}
