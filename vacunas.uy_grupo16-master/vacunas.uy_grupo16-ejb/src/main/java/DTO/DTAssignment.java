package DTO;

import entities.AssignmentPK;

import java.io.Serializable;

public class DTAssignment extends DTBaseModel implements Serializable {

  private AssignmentPK pkId;
  private String startDate;
  private String endDate;
  private String startTime;
  private String endTime;
  private DTVaccinationPost vaccinationPost;
  private DTVaccinator vaccinator;
  private DTSchedule schedule;

  public DTAssignment() {}

  public DTAssignment(String startDate, String endDate, String startTime, String endTime) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.startTime = startTime;
    this.endTime = endTime;
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

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public DTVaccinationPost getVaccinationPost() {
    return vaccinationPost;
  }

  public void setVaccinationPost(DTVaccinationPost vaccinationPost) {
    this.vaccinationPost = vaccinationPost;
  }

  public DTVaccinator getVaccinator() {
    return vaccinator;
  }

  public void setVaccinator(DTVaccinator vaccinator) {
    this.vaccinator = vaccinator;
  }

  public DTSchedule getSchedule() {
    return schedule;
  }

  public void setSchedule(DTSchedule schedule) {
    this.schedule = schedule;
  }

  public AssignmentPK getPkId() {
    return pkId;
  }

  public void setPkId(AssignmentPK id) {
    this.pkId = id;
  }
}
