package com.daimler.integration.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class TargetMitglied {

  private String nachname;

  private String vorname;

  private String profilId;

  private String email;
}
