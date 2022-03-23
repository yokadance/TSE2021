package DTO;

import java.io.Serializable;

public class DTVaccinationActView implements Serializable {

  private String personName;
  private String personSurname;
  private String personLastName;
  private String personCi;
  private String personBirthday;
  private String vaccinationActDate;
  private String vaccine;
  private String vaccinationCenter;
  private String disease;

  public DTVaccinationActView() {}

  public DTVaccinationActView(
    String personName,
    String personSurname,
    String personLastName,
    String personCi,
    String personBirthday,
    String vaccinationActDate,
    String vaccine,
    String vaccinationCenter,
    String disease
  ) {
    this.personName = personName;
    this.personSurname = personSurname;
    this.personLastName = personLastName;
    this.personCi = personCi;
    this.personBirthday = personBirthday;
    this.vaccinationActDate = vaccinationActDate;
    this.vaccine = vaccine;
    this.vaccinationCenter = vaccinationCenter;
    this.disease = disease;
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  public String getPersonSurname() {
    return personSurname;
  }

  public void setPersonSurname(String personSurname) {
    this.personSurname = personSurname;
  }

  public String getPersonLastName() {
    return personLastName;
  }

  public void setPersonLastName(String personLastName) {
    this.personLastName = personLastName;
  }

  public String getPersonCi() {
    return personCi;
  }

  public void setPersonCi(String personCi) {
    this.personCi = personCi;
  }

  public String getPersonBirthday() {
    return personBirthday;
  }

  public void setPersonBirthday(String personBirthday) {
    this.personBirthday = personBirthday;
  }

  public String getVaccinationActDate() {
    return vaccinationActDate;
  }

  public void setVaccinationActDate(String vaccinationActDate) {
    this.vaccinationActDate = vaccinationActDate;
  }

  public String getVaccine() {
    return vaccine;
  }

  public void setVaccine(String vaccine) {
    this.vaccine = vaccine;
  }

  public String getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(String vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public String getDisease() {
    return disease;
  }

  public void setDisease(String disease) {
    this.disease = disease;
  }

  @Override
  public String toString() {
    return (
      "DTVaccinationActView{" +
      "personName='" +
      personName +
      '\'' +
      ", personSurname='" +
      personSurname +
      '\'' +
      ", personLastName='" +
      personLastName +
      '\'' +
      ", personCi='" +
      personCi +
      '\'' +
      ", personBirthday='" +
      personBirthday +
      '\'' +
      ", vaccinationActDate='" +
      vaccinationActDate +
      '\'' +
      ", vaccine='" +
      vaccine +
      '\'' +
      ", vaccinationCenter='" +
      vaccinationCenter +
      '\'' +
      '}'
    );
  }
}
