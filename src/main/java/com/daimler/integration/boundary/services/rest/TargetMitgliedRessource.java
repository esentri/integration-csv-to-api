package com.daimler.integration.boundary.services.rest;

import com.daimler.integration.entity.TargetMitglied;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.NonNull;
import lombok.extern.java.Log;

@Log
@Path("/target")
public final class TargetMitgliedRessource {

  @POST
  @Path("/mitglied")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response syncMitglied(@Context HttpHeaders headers, @NonNull TargetMitglied mitglied) {

    log.info("Received " + mitglied + " from Camel.");
    return Response.ok().build();
  }
}
