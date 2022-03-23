package DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Date;
import java.util.List;

public class DTSchedule extends DTBaseModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private String startDate;
  private String endDate;
  private String startTimeDaily;
  private String endTimeDaily;
  private int fraction;
  private boolean saturday;
  private boolean sunday;
  private DTAuthority authority;
  private DTVaccinationPlan vaccinationPlan;
  private List<DTReservation> reservations;
  private DTVaccinationCenter vaccinationCenter;

  public DTSchedule() {
    super();
  }

  public DTSchedule(
    String startDate,
    String endDate,
    String startTimeDaily,
    String endTimeDaily,
    int fraction,
    boolean saturday,
    boolean sunday
  ) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.startTimeDaily = startTimeDaily;
    this.endTimeDaily = endTimeDaily;
    this.fraction = fraction;
    this.saturday = saturday;
    this.sunday = sunday;
  }

  public DTSchedule(
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
    DTAuthority authority,
    DTVaccinationPlan vaccinationPlan,
    List<DTReservation> reservations,
    DTVaccinationCenter vaccinationCenter
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

  public DTAuthority getAuthority() {
    return authority;
  }

  public void setAuthority(DTAuthority authority) {
    this.authority = authority;
  }

  public DTVaccinationPlan getVaccinationPlan() {
    return vaccinationPlan;
  }

  public void setVaccinationPlan(DTVaccinationPlan vaccinationPlan) {
    this.vaccinationPlan = vaccinationPlan;
  }

  public List<DTReservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<DTReservation> reservations) {
    this.reservations = reservations;
  }

  public DTVaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(DTVaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public Period durationbetween() {
    LocalDate lds = LocalDate.parse((String) this.getStartDate());
    LocalDate lde = LocalDate.parse((String) this.getEndDate());
    Period d = Period.between(lds, lde);
    LocalTime lts = LocalTime.parse((String) this.getStartTimeDaily());
    LocalTime lte = LocalTime.parse((String) this.getEndTimeDaily());
    //    System.out.println(Period.between(lds, lde).getMonths());
    //    System.out.println(Period.between(lds, lde).getDays());
    //    System.out.println(lts.plusMinutes(fraction));
    //
    //    System.out.println(Duration.between(lts, lte).toMinutes());

    return d;
  }
}
