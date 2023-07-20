package com.daimler.integration.application.common.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class CamelController {

  private ProducerTemplate producerTemplate;

  @Inject
  CamelController(ProducerTemplate producerTemplate) {
    this.producerTemplate = producerTemplate;
  }

  public void startCamelRouteSend(String camelRouteUri, Object body) {
    producerTemplate.sendBody(camelRouteUri, body);
  }

  public Object startCamelRouteRequest(String camelRouteUri, Object body) {
    return producerTemplate.requestBody(camelRouteUri, body);
  }
}
