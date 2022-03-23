package DTO;

import java.io.Serializable;

public class DTScheduleView implements Serializable {

  private static final long serialVersionUID = 1L;

  private String startDate;
  private String endDate;
  private String startTimeDaily;
  private String endTimeDaily;
  private String vaccinationPlan;
  private String vaccinationPlanId;
  private String vaccineName;
  private String vaccinationCenterName;
  private Boolean saturday;
  private Boolean sunday;

  public DTScheduleView() {}

  public DTScheduleView(
    String startDate,
    String endDate,
    String startTimeDaily,
    String endTimeDaily,
    String vaccinationPlan,
    String vaccinationPlanId,
    String vaccineName,
    String vaccinationCenterName,
    Boolean saturday,
    Boolean sunday
  ) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.startTimeDaily = startTimeDaily;
    this.endTimeDaily = endTimeDaily;
    this.vaccinationPlan = vaccinationPlan;
    this.vaccinationPlanId = vaccinationPlanId;
    this.vaccineName = vaccineName;
    this.vaccinationCenterName = vaccinationCenterName;
    this.saturday = saturday;
    this.sunday = sunday;
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

  public String getStartTimeDaily() {
    return startTimeDaily;
  }

  public void setStartTimeDaily(String startTimeDaily) {
    this.startTimeDaily = startTimeDaily;
  }

  public String getEndTimeDaily() {
    return endTimeDaily;
  }

  public void setEndTimeDaily(String endTimeDaily) {
    this.endTimeDaily = endTimeDaily;
  }

  public String getVaccinationPlan() {
    return vaccinationPlan;
  }

  public void setVaccinationPlan(String vaccinationPlan) {
    this.vaccinationPlan = vaccinationPlan;
  }

  public String getVaccinationPlanId() {
    return vaccinationPlanId;
  }

  public void setVaccinationPlanId(String vaccinationPlanId) {
    this.vaccinationPlanId = vaccinationPlanId;
  }

  public String getVaccineName() {
    return vaccineName;
  }

  public void setVaccineName(String vaccineName) {
    this.vaccineName = vaccineName;
  }

  public String getVaccinationCenterName() {
    return vaccinationCenterName;
  }

  public void setVaccinationCenterName(String vaccinationCenterName) {
    this.vaccinationCenterName = vaccinationCenterName;
  }

  public Boolean getSaturday() {
    return saturday;
  }

  public void setSaturday(Boolean saturday) {
    this.saturday = saturday;
  }

  public Boolean getSunday() {
    return sunday;
  }

  public void setSunday(Boolean sunday) {
    this.sunday = sunday;
  }
}
