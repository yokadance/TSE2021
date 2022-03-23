package DTO;

import java.io.Serializable;
import java.util.Date;

public class DTVaccinationPostNew extends DTBaseModel implements Serializable {

  private String name;
  private String observation;
  private Long idVaccinatonCenter;

  public DTVaccinationPostNew() {}

  public DTVaccinationPostNew(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String observation,
    long idVaccinatonCenter
  ) {
    this.name = name;
    this.observation = observation;
    this.idVaccinatonCenter = idVaccinatonCenter;
  }

  public DTVaccinationPostNew(String name, String observation, Long idVaccinatonCenter) {
    this.name = name;
    this.observation = observation;
    this.idVaccinatonCenter = idVaccinatonCenter;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public long getIdVaccinatonCenter() {
    return idVaccinatonCenter;
  }

  public void setIdVaccinatonCenter(Long idVaccinatonCenter) {
    this.idVaccinatonCenter = idVaccinatonCenter;
  }
}
