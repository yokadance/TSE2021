package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTCitizen extends DTRole implements Serializable {

  private List<DTVaccinationAct> vaccinationActs;
  private List<DTReservation> reservations;
  private String token;

  public DTCitizen() {
    super();
  }

  public DTCitizen(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    DTPerson dtPerson,
    List<DTVaccinationAct> vaccinationActs,
    List<DTReservation> reservations,
    String token
  ) {
    super(id, createDate, updateDate, deleteDate, userid, name, dtPerson);
    this.vaccinationActs = vaccinationActs;
    this.reservations = reservations;
    this.token = token;
  }

  public List<DTVaccinationAct> getVaccinationActs() {
    return vaccinationActs;
  }

  public void setVaccinationActs(List<DTVaccinationAct> vaccinationActs) {
    this.vaccinationActs = vaccinationActs;
  }

  public List<DTReservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<DTReservation> reservations) {
    this.reservations = reservations;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return (
      "DTCitizen{" +
      "vaccinationActs=" +
      vaccinationActs +
      ", reservations=" +
      reservations +
      ", token='" +
      token +
      '\'' +
      "} " +
      super.toString()
    );
  }
}
