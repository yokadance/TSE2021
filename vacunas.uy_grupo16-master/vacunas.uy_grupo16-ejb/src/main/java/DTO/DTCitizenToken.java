package DTO;

import java.io.Serializable;

public class DTCitizenToken implements Serializable {

  private String token;
  private String name;
  private String vaccinationCenterName;
  private String vcaccinationCenterLocation;
  private String vaccinationPostName;
  private String reservationDate;
  private String reservationTime;

  public DTCitizenToken() {}

  public DTCitizenToken(
    String token,
    String name,
    String vaccinationCenterName,
    String vcaccinationCenterLocation,
    String vaccinationPostName,
    String reservationDate,
    String reservationTime
  ) {
    this.token = token;
    this.name = name;
    this.vaccinationCenterName = vaccinationCenterName;
    this.vcaccinationCenterLocation = vcaccinationCenterLocation;
    this.vaccinationPostName = vaccinationPostName;
    this.reservationDate = reservationDate;
    this.reservationTime = reservationTime;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVaccinationCenterName() {
    return vaccinationCenterName;
  }

  public void setVaccinationCenterName(String vaccinationCenterName) {
    this.vaccinationCenterName = vaccinationCenterName;
  }

  public String getVcaccinationCenterLocation() {
    return vcaccinationCenterLocation;
  }

  public void setVcaccinationCenterLocation(String vcaccinationCenterLocation) {
    this.vcaccinationCenterLocation = vcaccinationCenterLocation;
  }

  public String getVaccinationPostName() {
    return vaccinationPostName;
  }

  public void setVaccinationPostName(String vaccinationPostName) {
    this.vaccinationPostName = vaccinationPostName;
  }

  public String getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(String reservationDate) {
    this.reservationDate = reservationDate;
  }

  public String getReservationTime() {
    return reservationTime;
  }

  public void setReservationTime(String reservationTime) {
    this.reservationTime = reservationTime;
  }
}
