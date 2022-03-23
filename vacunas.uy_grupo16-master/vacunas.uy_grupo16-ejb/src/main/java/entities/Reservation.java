package entities;

import DTO.DTReservation;
import enumerations.ReservationState;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Reservation extends BaseModel {

  @ManyToOne
  @JoinTable(name = "vaccinationCenter_reservation")
  private VaccinationCenter vaccinationCenter;

  @ManyToOne
  @JoinTable(name = "schedule_reservation")
  private Schedule schedule;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccinationpost_reservation")
  private VaccinationPost vaccinationPost;

  @JoinTable(name = "citizen_reservation")
  @ManyToOne(fetch = FetchType.EAGER)
  private Citizen citizen;

  private String date;

  private String time;

  private ReservationState reservationState;

  private int doses;

  public Reservation() {
    super();
  }

  public Reservation(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    VaccinationCenter vaccinationCenter,
    Schedule schedule,
    VaccinationPost vaccinationPost,
    Citizen citizen,
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

  public VaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  public VaccinationPost getVaccinationPost() {
    return vaccinationPost;
  }

  public void setVaccinationPost(VaccinationPost vaccinationPost) {
    this.vaccinationPost = vaccinationPost;
  }

  public Citizen getCitizen() {
    return citizen;
  }

  public void setCitizen(Citizen citizen) {
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

  public DTReservation getDTReservation() {
    ModelMapper modelMapper = new ModelMapper();
    DTReservation dt = modelMapper.map(this, DTReservation.class);
    return dt;
  }
}
