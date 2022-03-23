package entities;

import DTO.DTVaccinationCenter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class VaccinationCenter extends BaseModel {

  private String name;
  private String location;
  private String longitude;
  private String latitude;
  private String password;

  @OneToMany(mappedBy = "vaccinationCenter", fetch = FetchType.LAZY)
  private List<VaccinationPost> vaccinationPosts;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Vaccination> vaccinations;

  @OneToMany(mappedBy = "vaccinationCenter", fetch = FetchType.LAZY)
  private List<Schedule> schedules;

  @ManyToMany(mappedBy = "vaccinationCenters", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<VaccinationPlan> vaccinationPlans;

  @OneToMany(mappedBy = "vaccinationCenter", fetch = FetchType.LAZY)
  private List<Package> packages;

  public VaccinationCenter() {}

  public VaccinationCenter(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String location,
    String longitude,
    String latitude,
    String password,
    List<VaccinationPost> vaccinationPosts,
    List<Vaccination> vaccinations,
    List<Schedule> schedules,
    List<VaccinationPlan> vaccinationPlans,
    List<Package> packages
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.location = location;
    this.longitude = longitude;
    this.latitude = latitude;
    this.password = password;
    this.vaccinationPosts = vaccinationPosts;
    this.vaccinations = vaccinations;
    this.schedules = schedules;
    this.vaccinationPlans = vaccinationPlans;
    this.packages = packages;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<VaccinationPost> getVaccinationPosts() {
    return vaccinationPosts;
  }

  public void setVaccinationPosts(List<VaccinationPost> vaccinationPosts) {
    this.vaccinationPosts = vaccinationPosts;
  }

  public List<Vaccination> getVaccinations() {
    return vaccinations;
  }

  public void setVaccinations(List<Vaccination> vaccinations) {
    this.vaccinations = vaccinations;
  }

  public List<Schedule> getSchedules() {
    return schedules;
  }

  public void setSchedules(List<Schedule> schedules) {
    this.schedules = schedules;
  }

  public List<VaccinationPlan> getVaccinationPlans() {
    return vaccinationPlans;
  }

  public void setVaccinationPlans(List<VaccinationPlan> vaccinationPlans) {
    this.vaccinationPlans = vaccinationPlans;
  }

  public List<Package> getPackages() {
    return packages;
  }

  public void setPackages(List<Package> packages) {
    this.packages = packages;
  }

  public DTVaccinationCenter getDTVaccinationCenter() {
    ModelMapper modelMapper = new ModelMapper();
    DTVaccinationCenter dt = modelMapper.map(this, DTVaccinationCenter.class);
    return dt;
  }
}
