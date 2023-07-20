package com.daimler.integration.application.mitglieder;

import com.daimler.integration.entity.SourceMitglied;
import com.daimler.integration.entity.TargetMitglied;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import lombok.NonNull;
import lombok.extern.java.Log;

@ApplicationScoped
@Named("CsvToApiMitgliedMapper")
@Log
final class CsvToApiMitgliedMapper {

  public TargetMitglied map(@NonNull SourceMitglied sourceMitglied) {

    TargetMitglied targetMitglied =
        TargetMitglied.builder()
            .profilId(sourceMitglied.getProfilId())
            .vorname(sourceMitglied.getVorname())
            .nachname(sourceMitglied.getNachname())
            .email(sourceMitglied.getEmail())
            .build();
    return targetMitglied;
  }
}
