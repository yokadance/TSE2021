package entities;

import DTO.DTCitizen;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Citizen extends Role {

  @OneToMany(mappedBy = "citizen", fetch = FetchType.LAZY)
  private List<VaccinationAct> vaccinationActs;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "citizen")
  private List<Reservation> reservations;

  private String token;

  public Citizen() {}

  public Citizen(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    List<Person> people,
    List<VaccinationAct> vaccinationActs,
    List<Reservation> reservations,
    String token
  ) {
    //super(id, createDate, updateDate, deleteDate, userid, name, people);
    this.vaccinationActs = vaccinationActs;
    this.reservations = reservations;
    this.token = token;
  }

  public List<VaccinationAct> getVaccinationActs() {
    return vaccinationActs;
  }

  public void setVaccinationActs(List<VaccinationAct> vaccinationActs) {
    this.vaccinationActs = vaccinationActs;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public DTCitizen getDTCitizen() {
    DTCitizen dtC = new DTCitizen();
    dtC.setId(this.getId());
    dtC.setName(this.getName());
    dtC.setToken(this.getToken());
    return dtC;
    //    ModelMapper modelMapper = new ModelMapper();
    //    DTCitizen dt = modelMapper.map(this, DTCitizen.class);
    //    return dt;
  }
}
