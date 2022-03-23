package entities;

import DTO.DTVaccinationPlan;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class VaccinationPlan extends BaseModel {

  private String name;

  private String startDate;

  private String endDate;

  private int vaccineQuantity;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "authority_VaccinationPlan")
  private Authority authority;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinTable(
    name = "VaccinationPlan_VaccinationCenter",
    joinColumns = { @JoinColumn(name = "fk_VaccinationPlan") },
    inverseJoinColumns = { @JoinColumn(name = "fk_VaccinationCenter") }
  )
  private List<VaccinationCenter> vaccinationCenters;

  @OneToMany(mappedBy = "vaccinationPlan", fetch = FetchType.LAZY)
  private List<Schedule> schedule;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "VaccinationPlan_Restriction",
    joinColumns = { @JoinColumn(name = "fk_VaccinationPlan") },
    inverseJoinColumns = { @JoinColumn(name = "fk_Restriction") }
  )
  private List<Restriction> restriction;

  @OneToMany(mappedBy = "vaccinationPlan", fetch = FetchType.LAZY)
  private List<Package> aPackage;

  @ManyToOne
  private Vaccine vaccine;

  public VaccinationPlan() {
    super();
  }

  public VaccinationPlan(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String startDate,
    String endDate,
    Integer vaccineQuantity,
    Authority authority,
    List<VaccinationCenter> vaccinationCenters,
    List<Schedule> schedule,
    List<Restriction> restriction,
    List<Package> aPackage,
    Vaccine vaccine
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.vaccineQuantity = vaccineQuantity;
    this.authority = authority;
    this.vaccinationCenters = vaccinationCenters;
    this.schedule = schedule;
    this.restriction = restriction;
    this.aPackage = aPackage;
    this.vaccine = vaccine;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Integer getVaccineQuantity() {
    return vaccineQuantity;
  }

  public void setVaccineQuantity(Integer vaccineQuantity) {
    this.vaccineQuantity = vaccineQuantity;
  }

  public Authority getAuthority() {
    return authority;
  }

  public void setAuthority(Authority authority) {
    this.authority = authority;
  }

  public List<VaccinationCenter> getVaccinationCenters() {
    return vaccinationCenters;
  }

  public void setVaccinationCenters(List<VaccinationCenter> vaccinationCenters) {
    this.vaccinationCenters = vaccinationCenters;
  }

  public List<Schedule> getSchedule() {
    return schedule;
  }

  public void setSchedule(List<Schedule> schedule) {
    this.schedule = schedule;
  }

  public List<Restriction> getRestriction() {
    return restriction;
  }

  public void setRestriction(List<Restriction> restriction) {
    this.restriction = restriction;
  }

  public List<Package> getaPackage() {
    return aPackage;
  }

  public void setaPackage(List<Package> aPackage) {
    this.aPackage = aPackage;
  }

  public void addVaccinationCenter(VaccinationCenter item) {
    this.vaccinationCenters.add(item);
  }

  public Vaccine getVaccine() {
    return vaccine;
  }

  public void setVaccine(Vaccine vaccine) {
    this.vaccine = vaccine;
  }

  public DTVaccinationPlan getDTVaccinationPlan() {
    ModelMapper modelMapper = new ModelMapper();
    DTVaccinationPlan dt = modelMapper.map(this, DTVaccinationPlan.class);
    return dt;
  }
}
