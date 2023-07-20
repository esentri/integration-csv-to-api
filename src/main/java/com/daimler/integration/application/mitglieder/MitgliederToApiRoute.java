package com.daimler.integration.application.mitglieder;

import com.daimler.integration.entity.SourceMitglied;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.transform.Source;

@ApplicationScoped
public final class MitgliederToApiRoute extends RouteBuilder  {

    public final static String ROUTE_ID = MitgliederToApiRoute.class.getName();
    public final static String ROUTE_URL = "direct:" + ROUTE_ID;

    //spotless:off
    @Override
    public void configure() throws Exception {

        DataFormat mitgliederFormat = new BindyCsvDataFormat(SourceMitglied.class);

        from(ROUTE_URL)
                .routeId(ROUTE_ID)
                .errorHandler(noErrorHandler()) //propagate errors to the parent route
                .log(LoggingLevel.INFO, "START: " + ROUTE_ID + " called...")
                .log(LoggingLevel.INFO, "${body} ")

                .log(LoggingLevel.TRACE, "Translate input from CSV file into Java objects.")
                .unmarshal(mitgliederFormat)

                .log(LoggingLevel.INFO, "${body} ")

                .choice()
                    .when(simple("${body?.size} > 0"))
                        .split(body())

                            .log(LoggingLevel.INFO, "Translate ${body} to TargetMitglied...")
                            .bean("CsvToApiMitgliedMapper", "map(${body})")

                            .process(new Processor() {
                                @Override
                                public void process(Exchange exchange) throws Exception {
                                    //System.out.println("Debug-Support");
                                }
                            })

                            .log(LoggingLevel.INFO, "Send ${body} to API...")
                            .bean("Targetdapter", "syncMitglied(${body})")
                        .end()

                    .endChoice()
                .otherwise()
                    .log(LoggingLevel.INFO, "No records found in csv file.")
                .end()

                .log(LoggingLevel.INFO, "FINISHED: " + ROUTE_ID + ".");
    }
    //spotless:on
}
