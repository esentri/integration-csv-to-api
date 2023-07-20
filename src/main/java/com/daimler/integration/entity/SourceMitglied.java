package com.daimler.integration.entity;

import io.quarkus.runtime.util.StringUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(
    separator = ";",
    generateHeaderColumns = true,
    quoting = true,
    quotingEscaped = true,
    skipFirstLine = true)
@Data
public final class SourceMitglied {

  @DataField(pos = 1, required = true, columnName = "Nachname")
  private String nachname;

  @DataField(pos = 2, columnName = "Vorname", required = true)
  private String vorname;

  @DataField(pos = 3, required = true, columnName = "Profil-ID")
  private String profilId;

  @DataField(pos = 4, columnName = "E-Mail")
  private String email;

  @DataField(pos = 5, columnName = "Abteilungen")
  private String abteilungen;

  @DataField(pos = 6, columnName = "Beiträge")
  private String beitraege;

  public List<String> getAbteilungenList() {
    return convertStringToList(abteilungen, ";");
  }

  public boolean hasAbteilung(String abteilung) {
    if (StringUtil.isNullOrEmpty(abteilung)) {
      return false;
    } else {
      return getAbteilungenList().contains(abteilung);
    }
  }

  public List<String> getBeitraegeList() {
    return convertStringToList(beitraege, ";");
  }

  public boolean hasBeitrag(String beitrag) {
    if (StringUtil.isNullOrEmpty(beitrag)) {
      return false;
    } else {
      return getBeitraegeList().contains(beitrag);
    }
  }

  private List<String> convertStringToList(String value, String delimiter) {
    return Arrays.stream(value.split(delimiter)).map(String::trim).collect(Collectors.toList());
  }
}
