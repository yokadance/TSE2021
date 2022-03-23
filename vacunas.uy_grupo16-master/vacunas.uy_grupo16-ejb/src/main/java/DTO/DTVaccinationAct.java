package DTO;

import java.io.Serializable;
import java.util.Date;

public class DTVaccinationAct extends DTBaseModel implements Serializable {

  private String observation;
  private DTCitizen citizen;
  private DTVaccinationPost vaccinationPost;
  private DTPackage aPackage;

  public DTVaccinationAct() {
    super();
  }

  public DTVaccinationAct(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String observation,
    DTCitizen citizen,
    DTVaccinationPost vaccinationPost,
    DTPackage aPackage
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.observation = observation;
    this.citizen = citizen;
    this.vaccinationPost = vaccinationPost;
    this.aPackage = aPackage;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public DTCitizen getCitizen() {
    return citizen;
  }

  public void setCitizen(DTCitizen citizen) {
    this.citizen = citizen;
  }

  public DTVaccinationPost getVaccinationPost() {
    return vaccinationPost;
  }

  public void setVaccinationPost(DTVaccinationPost vaccinationPost) {
    this.vaccinationPost = vaccinationPost;
  }

  public DTPackage getaPackage() {
    return aPackage;
  }

  public void setaPackage(DTPackage aPackage) {
    this.aPackage = aPackage;
  }
}
