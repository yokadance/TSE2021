package DTO;

import java.io.Serializable;

public class DTVaccinatorView implements Serializable {

  private String scheduleId;
  private String vaccinationPlanId;
  private String vaccinationPlanName;
  private String vaccineName;
  private String vaccinationCenterId;
  private String vaccinationCenterName;
  private String vaccinationPostId;
  private String vaccinationPostName;
  private String dateStart;
  private String dateEnd;
  private String timeStart;
  private String timeEnd;

  public DTVaccinatorView() {}

  public DTVaccinatorView(
    String scheduleId,
    String vaccinationPlanId,
    String vaccinationPlanName,
    String vaccineName,
    String vaccinationCenterId,
    String vaccinationCenterName,
    String vaccinationPostId,
    String vaccinationPostName,
    String dateStart,
    String dateEnd,
    String timeStart,
    String timeEnd
  ) {
    this.scheduleId = scheduleId;
    this.vaccinationPlanId = vaccinationPlanId;
    this.vaccinationPlanName = vaccinationPlanName;
    this.vaccineName = vaccineName;
    this.vaccinationCenterId = vaccinationCenterId;
    this.vaccinationCenterName = vaccinationCenterName;
    this.vaccinationPostId = vaccinationPostId;
    this.vaccinationPostName = vaccinationPostName;
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
    this.timeStart = timeStart;
    this.timeEnd = timeEnd;
  }

  public String getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(String scheduleId) {
    this.scheduleId = scheduleId;
  }

  public String getVaccinationPlanId() {
    return vaccinationPlanId;
  }

  public void setVaccinationPlanId(String vaccinationPlanId) {
    this.vaccinationPlanId = vaccinationPlanId;
  }

  public String getVaccinationPlanName() {
    return vaccinationPlanName;
  }

  public void setVaccinationPlanName(String vaccinationPlanName) {
    this.vaccinationPlanName = vaccinationPlanName;
  }

  public String getVaccineName() {
    return vaccineName;
  }

  public void setVaccineName(String vaccineName) {
    this.vaccineName = vaccineName;
  }

  public String getVaccinationCenterId() {
    return vaccinationCenterId;
  }

  public void setVaccinationCenterId(String vaccinationCenterId) {
    this.vaccinationCenterId = vaccinationCenterId;
  }

  public String getVaccinationCenterName() {
    return vaccinationCenterName;
  }

  public void setVaccinationCenterName(String vaccinationCenterName) {
    this.vaccinationCenterName = vaccinationCenterName;
  }

  public String getVaccinationPostId() {
    return vaccinationPostId;
  }

  public void setVaccinationPostId(String vaccinationPostId) {
    this.vaccinationPostId = vaccinationPostId;
  }

  public String getVaccinationPostName() {
    return vaccinationPostName;
  }

  public void setVaccinationPostName(String vaccinationPostName) {
    this.vaccinationPostName = vaccinationPostName;
  }

  public String getDateStart() {
    return dateStart;
  }

  public void setDateStart(String dateStart) {
    this.dateStart = dateStart;
  }

  public String getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(String dateEnd) {
    this.dateEnd = dateEnd;
  }

  public String getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(String timeStart) {
    this.timeStart = timeStart;
  }

  public String getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }
}
