package entities;

import DTO.DTVaccinationPost;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class VaccinationPost extends BaseModel {

  private String name;
  private String observation;

  @OneToMany(mappedBy = "vaccinationPost")
  private List<Assignment> assignments;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccinationCenter_vaccinationpost")
  private VaccinationCenter vaccinationCenter;

  @OneToMany(mappedBy = "vaccinationPost")
  private List<Reservation> reservations;

  @OneToMany(mappedBy = "vaccinationPost", fetch = FetchType.LAZY)
  private List<VaccinationAct> vaccinationActs;

  public VaccinationPost() {}

  public VaccinationPost(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String observation,
    List<Assignment> assignments,
    VaccinationCenter vaccinationCenter,
    List<Reservation> reservations,
    List<VaccinationAct> vaccinationActs
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.observation = observation;
    this.assignments = assignments;
    this.vaccinationCenter = vaccinationCenter;
    this.reservations = reservations;
    this.vaccinationActs = vaccinationActs;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public List<Assignment> getAssignments() {
    return assignments;
  }

  public void setAssignments(List<Assignment> assignments) {
    this.assignments = assignments;
  }

  public VaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  public List<VaccinationAct> getVaccinationActs() {
    return vaccinationActs;
  }

  public void setVaccinationActs(List<VaccinationAct> vaccinationActs) {
    this.vaccinationActs = vaccinationActs;
  }

  public DTVaccinationPost getDTVaccinationPost() {
    DTVaccinationPost dt = new DTVaccinationPost();
    dt.setId(this.getId());
    dt.setCreateDate(this.getCreateDate());
    dt.setUpdateDate(this.getUpdateDate());
    dt.setDeleteDate(this.getDeleteDate());
    dt.setUserid(this.getUserid());
    dt.setName(this.getName());
    dt.setObservation(this.getObservation());
    return dt;
  }
}
