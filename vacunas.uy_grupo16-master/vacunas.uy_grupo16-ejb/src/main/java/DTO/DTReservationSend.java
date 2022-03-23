package DTO;

import enumerations.ReservationState;

import java.io.Serializable;
import java.util.Date;

public class DTReservationSend extends DTBaseModel implements Serializable {

  private Long scheduleId;
  private Long vaccinationPostId;
  //private Long citizenId;
  private String citizenName;
  //private Long personId;
  private String personId;
  private String reservationDate;
  private String reservationTime;
  private ReservationState reservationState;
  private Long vaccinatorCenterId;


  public DTReservationSend() {}

  public DTReservationSend(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    Long scheduleId,
    Long vaccinationPostId,
    Long vaccinatorCenterId,
    //Long citizenId,
    String citizenName,
    //Long personId,
    String personId,
    String reservationDate,
    String reservationTime,
    ReservationState reservationState
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.scheduleId = scheduleId;
    this.vaccinationPostId = vaccinationPostId;
    this.vaccinatorCenterId = vaccinatorCenterId;
    //this.citizenId = citizenId;
    this.citizenName = citizenName;
    this.personId = personId;
    this.reservationDate = reservationDate;
    this.reservationTime = reservationTime;
    this.reservationState = reservationState;
  }

  public Long getVaccinatorCenterId() {
    return vaccinatorCenterId;
  }

  public void setVaccinatorCenterId(Long vaccinatorCenterId) {
    this.vaccinatorCenterId = vaccinatorCenterId;
  }

  public Long getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Long scheduleId) {
    this.scheduleId = scheduleId;
  }

  public Long getVaccinationPostId() {
    return vaccinationPostId;
  }

  public void setVaccinationPostId(Long vaccinationPostId) {
    this.vaccinationPostId = vaccinationPostId;
  }

  //public Long getCitizenId() {
   // return citizenId;
 // }

 // public void setCitizenId(Long citizenId) {
  //  this.citizenId = citizenId;
  //}


  public String getCitizenName() {
    return citizenName;
  }

  public void setCitizenName(String citizenName) {
    this.citizenName = citizenName;
  }

  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  // public Long getPersonId() {
 //   return personId;
 // }

  //public void setPersonId(Long personId) {
   // this.personId = personId;
 // }

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

  public ReservationState getReservationState() {
    return reservationState;
  }

  public void setReservationState(ReservationState reservationState) {
    this.reservationState = reservationState;
  }
}
