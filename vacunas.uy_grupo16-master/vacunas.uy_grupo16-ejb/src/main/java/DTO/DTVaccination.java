package DTO;

import enumerations.VaccinationState;

import java.io.Serializable;
import java.util.Date;

public class DTVaccination extends DTEvent implements Serializable {

  private VaccinationState state;
  private DTVaccinationCenter vaccinationCenter;
  private DTPackage aPackage;

  public DTVaccination() {}

  public DTVaccination(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    VaccinationState state,
    DTVaccinationCenter vaccinationCenter,
    DTPackage aPackage,
    Date date
  ) {
    super(id, createDate, updateDate, deleteDate, userid, date);
    this.state = state;
    this.vaccinationCenter = vaccinationCenter;
    this.aPackage = aPackage;
  }

  public VaccinationState getState() {
    return state;
  }

  public void setState(VaccinationState state) {
    this.state = state;
  }

  public DTVaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(DTVaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public DTPackage getaPackage() {
    return aPackage;
  }

  public void setaPackage(DTPackage aPackage) {
    this.aPackage = aPackage;
  }
}
