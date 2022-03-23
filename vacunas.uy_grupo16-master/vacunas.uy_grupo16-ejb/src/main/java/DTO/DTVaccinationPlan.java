package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTVaccinationPlan extends DTBaseModel implements Serializable {

  private String name;
  private String startDate;
  private String endDate;
  private int vaccineQuantity;
  private String authority;
  private List<String> vaccinationCenters;
  private List<String> schedule;
  private List<String> restriction;
  private List<String> aPackage;
  private DTVaccine dtvaccine;

  public DTVaccinationPlan() {
    super();
  }

  public DTVaccinationPlan(String name, String startDate, String endDate, int vaccineQuantity, String authority) {
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.vaccineQuantity = vaccineQuantity;
    this.authority = authority;
  }

  public DTVaccinationPlan(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String startDate,
    String endDate,
    int vaccineQuantity,
    String authority,
    List<String> vaccinationCenters,
    List<String> schedule,
    List<String> restriction,
    List<String> aPackage,
    DTVaccine dtvaccine
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.vaccineQuantity = vaccineQuantity;
    this.authority = authority;
    this.vaccinationCenters = vaccinationCenters;
    this.schedule = schedule;
    this.restriction = restriction;
    this.aPackage = aPackage;
    this.dtvaccine = dtvaccine;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public int getVaccineQuantity() {
    return vaccineQuantity;
  }

  public void setVaccineQuantity(int vaccineQuantity) {
    this.vaccineQuantity = vaccineQuantity;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public List<String> getVaccinationCenters() {
    return vaccinationCenters;
  }

  public void setVaccinationCenters(List<String> vaccinationCenters) {
    this.vaccinationCenters = vaccinationCenters;
  }

  public List<String> getSchedule() {
    return schedule;
  }

  public void setSchedule(List<String> schedule) {
    this.schedule = schedule;
  }

  public List<String> getRestriction() {
    return restriction;
  }

  public void setRestriction(List<String> restriction) {
    this.restriction = restriction;
  }

  public List<String> getaPackage() {
    return aPackage;
  }

  public void setaPackage(List<String> aPackage) {
    this.aPackage = aPackage;
  }

  public DTVaccine getVaccine() {
    return dtvaccine;
  }

  public void setVaccine(DTVaccine vaccine) {
    this.dtvaccine = vaccine;
  }
}
