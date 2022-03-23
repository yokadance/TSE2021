package DTO;

import enumerations.ReservationState;

import java.io.Serializable;
import java.util.Date;

public class DTReservation extends DTBaseModel implements Serializable {

  private String vaccinationCenter;
  private String schedule;
  private String vaccinationPost;
  private String citizen;
  private String date;
  private String time;
  private ReservationState reservationState;
  private int doses;

  public DTReservation() {
    super();
  }

  public DTReservation(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String vaccinationCenter,
    String schedule,
    String vaccinationPost,
    String citizen,
    String date,
    String time,
    ReservationState reservationState
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.vaccinationCenter = vaccinationCenter;
    this.schedule = schedule;
    this.vaccinationPost = vaccinationPost;
    this.citizen = citizen;
    this.date = date;
    this.time = time;
    this.reservationState = reservationState;
  }

  public String getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(String vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public String getSchedule() {
    return schedule;
  }

  public void setSchedule(String schedule) {
    this.schedule = schedule;
  }

  public String getVaccinationPost() {
    return vaccinationPost;
  }

  public void setVaccinationPost(String vaccinationPost) {
    this.vaccinationPost = vaccinationPost;
  }

  public String getCitizen() {
    return citizen;
  }

  public void setCitizen(String citizen) {
    this.citizen = citizen;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public ReservationState getReservationState() {
    return reservationState;
  }

  public void setReservationState(ReservationState reservationState) {
    this.reservationState = reservationState;
  }

  public int getDoses() {
    return doses;
  }

  public void setDoses(int doses) {
    this.doses = doses;
  }

  @Override
  public String toString() {
    return (
      "DTReservation{" +
      "vaccinationCenter=" +
      vaccinationCenter +
      ", schedule=" +
      schedule +
      ", vaccinationPost=" +
      vaccinationPost +
      ", citizen=" +
      citizen +
      ", date=" +
      date +
      ", time=" +
      time +
      ", reservationState=" +
      reservationState +
      "} " +
      super.toString()
    );
  }
}
