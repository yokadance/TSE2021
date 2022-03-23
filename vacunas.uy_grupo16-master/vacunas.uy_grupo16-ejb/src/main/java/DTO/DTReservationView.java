package DTO;

import enumerations.ReservationState;

import java.io.Serializable;
import java.util.Date;

public class DTReservationView implements Serializable {

  private Long reservationId;
  private Long vaccinationPlanId;
  private String vaccinationPlanName;
  private Long reservationCenterId;
  private String reservationCenterName;
  private String reservationDate;
  private String reservationTime;
  private ReservationState reservationState;
  private String vaccineName;
  private Long vaccinationPostId;
  private String vaccinationPostName;
  private int doses;

  public DTReservationView() {}

  public DTReservationView(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    Long reservationId,
    String vaccinationPlanName,
    Long reservationCenterId,
    String reservationCenterName,
    String reservationDate,
    String reservationTime,
    ReservationState reservationState,
    String vaccineName,
    Long vaccinationPostId,
    String vaccinationPostName
  ) {
    this.reservationId = reservationId;
    this.vaccinationPlanName = vaccinationPlanName;
    this.reservationCenterId = reservationCenterId;
    this.reservationCenterName = reservationCenterName;
    this.reservationDate = reservationDate;
    this.reservationTime = reservationTime;
    this.reservationState = reservationState;
    this.vaccineName = vaccineName;
    this.vaccinationPostId = vaccinationPostId;
    this.vaccinationPostName = vaccinationPostName;
  }

  public Long getReservationId() {
    return reservationId;
  }

  public void setReservationId(Long reservationId) {
    this.reservationId = reservationId;
  }

  public String getVaccinationPlanName() {
    return vaccinationPlanName;
  }

  public void setVaccinationPlanName(String vaccinationPlanName) {
    this.vaccinationPlanName = vaccinationPlanName;
  }

  public Long getReservationCenterId() {
    return reservationCenterId;
  }

  public void setReservationCenterId(Long reservationCenterId) {
    this.reservationCenterId = reservationCenterId;
  }

  public String getReservationCenterName() {
    return reservationCenterName;
  }

  public void setReservationCenterName(String reservationCenterName) {
    this.reservationCenterName = reservationCenterName;
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

  public ReservationState getReservationState() {
    return reservationState;
  }

  public void setReservationState(ReservationState reservationState) {
    this.reservationState = reservationState;
  }

  public String getVaccineName() {
    return vaccineName;
  }

  public void setVaccineName(String vaccineName) {
    this.vaccineName = vaccineName;
  }

  public Long getVaccinationPostId() {
    return vaccinationPostId;
  }

  public void setVaccinationPostId(Long vaccinationPostId) {
    this.vaccinationPostId = vaccinationPostId;
  }

  public String getVaccinationPostName() {
    return vaccinationPostName;
  }

  public void setVaccinationPostName(String vaccinationPostName) {
    this.vaccinationPostName = vaccinationPostName;
  }

  public Long getVaccinationPlanId() {
    return vaccinationPlanId;
  }

  public void setVaccinationPlanId(Long vaccinationPlanId) {
    this.vaccinationPlanId = vaccinationPlanId;
  }

  public int getDoses() {
    return doses;
  }

  public void setDoses(int doses) {
    this.doses = doses;
  }
}
