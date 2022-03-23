package entities;

import DTO.DTSchedule;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Schedule extends BaseModel {

  private String startDate;
  private String endDate;
  private String startTimeDaily;
  private String endTimeDaily;

  private int fraction;
  private boolean saturday;
  private boolean sunday;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "authority_Schedule")
  private Authority authority;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccinationPlan_Schedule")
  private VaccinationPlan vaccinationPlan;

  @OneToMany(mappedBy = "schedule")
  private List<Reservation> reservations;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccinationCenter_Schedule")
  private VaccinationCenter vaccinationCenter;

  @OneToMany(mappedBy = "schedule")
  private List<Assignment> assignment;

  public Schedule() {
    super();
  }

  public Schedule(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String startDate,
    String endDate,
    String startTimeDaily,
    String endTimeDaily,
    int fraction,
    boolean saturday,
    boolean sunday,
    Authority authority,
    VaccinationPlan vaccinationPlan,
    List<Reservation> reservations,
    VaccinationCenter vaccinationCenter
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.startDate = startDate;
    this.endDate = endDate;
    this.startTimeDaily = startTimeDaily;
    this.endTimeDaily = endTimeDaily;
    this.fraction = fraction;
    this.saturday = saturday;
    this.sunday = sunday;
    this.authority = authority;
    this.vaccinationPlan = vaccinationPlan;
    this.reservations = reservations;
    this.vaccinationCenter = vaccinationCenter;
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

  public int getFraction() {
    return fraction;
  }

  public void setFraction(int fraction) {
    this.fraction = fraction;
  }

  public boolean isSaturday() {
    return saturday;
  }

  public void setSaturday(boolean saturday) {
    this.saturday = saturday;
  }

  public boolean isSunday() {
    return sunday;
  }

  public void setSunday(boolean sunday) {
    this.sunday = sunday;
  }

  public Authority getAuthority() {
    return authority;
  }

  public void setAuthority(Authority authority) {
    this.authority = authority;
  }

  public VaccinationPlan getVaccinationPlan() {
    return vaccinationPlan;
  }

  public void setVaccinationPlan(VaccinationPlan vaccinationPlan) {
    this.vaccinationPlan = vaccinationPlan;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  public VaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public List<Assignment> getAssignment() {
    return assignment;
  }

  public void setAssignment(List<Assignment> assignment) {
    this.assignment = assignment;
  }

  public void addReservation(Reservation item) {
    this.reservations.add(item);
    item.setSchedule(this);
  }

  public DTSchedule getDTSchedule() {
    ModelMapper modelMapper = new ModelMapper();
    DTSchedule dt = modelMapper.map(this, DTSchedule.class);
    return dt;
  }
}
