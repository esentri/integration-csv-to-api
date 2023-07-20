package com.daimler.integration.boundary.services.file;

import com.daimler.integration.application.mitglieder.MitgliederToApiRoute;
import javax.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public final class MitgliederFileReader extends RouteBuilder {

  public static final String ROUTE_ID = MitgliederFileReader.class.getName();
  public static final String ROUTE_URL = "direct:" + ROUTE_ID;

  // spotless:off
  @Override
  public void configure() throws Exception {

    getCamelContext().setUseMDCLogging(true);
    getCamelContext().setUseBreadcrumb(true);

    from("file:src/test/resources/?include=mitglieder-.*.csv&charset=UTF-8")
        .routeId(ROUTE_ID)
        .setHeader(Exchange.BREADCRUMB_ID, simple("${date:now:YYYYMMddHH:mm:ss}"))
        .log(
            LoggingLevel.INFO,
            "============ BEGIN: docnum=${header.breadcrumbId} file=${file:name} ============ ")
        .to(MitgliederToApiRoute.ROUTE_URL)
        .log(
            LoggingLevel.INFO,
            "============ FINISHED: docnum=${header.breadcrumbId} file=${file:name} ============ ");
  }
  // spotless:on

}
