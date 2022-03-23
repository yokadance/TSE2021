package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTVaccinationPost extends DTBaseModel implements Serializable {

  private String name;
  private String observation;
  private List<String> vaccinators;
  private String vaccinationCenter;
  private List<String> reservations;
  private List<String> vaccinationActs;

  public DTVaccinationPost() {
    super();
  }

  public DTVaccinationPost(
          String name,
          String observation
  ) {
    this.name = name;
    this.observation = observation;
  }

  public DTVaccinationPost(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String observation,
    List<String> vaccinators,
    String vaccinationCenter,
    List<String> reservations,
    List<String> vaccinationActs
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.observation = observation;
    this.vaccinators = vaccinators;
    this.vaccinationCenter = vaccinationCenter;
    this.reservations = reservations;
    this.vaccinationActs = vaccinationActs;
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

  public List<String> getVaccinators() {
    return vaccinators;
  }

  public void setVaccinators(List<String> vaccinators) {
    this.vaccinators = vaccinators;
  }

  public String getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(String vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public List<String> getReservations() {
    return reservations;
  }

  public void setReservations(List<String> reservations) {
    this.reservations = reservations;
  }

  public List<String> getVaccinationActs() {
    return vaccinationActs;
  }

  public void setVaccinationActs(List<String> vaccinationActs) {
    this.vaccinationActs = vaccinationActs;
  }
}
