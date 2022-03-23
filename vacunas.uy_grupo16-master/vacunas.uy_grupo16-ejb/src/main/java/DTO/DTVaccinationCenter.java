package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTVaccinationCenter extends DTBaseModel implements Serializable {

  private String name;
  private String location;
  private String longitude;
  private String latitude;
  private String password;
  private List<String> vaccinationPosts;
  private List<String> vaccinations;
  private List<String> schedules;
  private List<String> vaccinationPlans;
  private List<String> packages;

  public DTVaccinationCenter() {
    super();
  }

  public DTVaccinationCenter(
          String name,
          String location,
          String longitude,
          String latitude,
          String password
  ) {
    this.name = name;
    this.location = location;
    this.longitude = longitude;
    this.latitude = latitude;
    this.password = password;
  }

  public DTVaccinationCenter(
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String location,
    String longitude,
    String latitude,
    String password,
    List<String> vaccinationPosts,
    List<String> vaccinations,
    List<String> schedules,
    List<String> vaccinationPlans,
    List<String> packages
  ) {
    super(createDate, updateDate, deleteDate, userid);
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

  public List<String> getVaccinationPosts() {
    return vaccinationPosts;
  }

  public void setVaccinationPosts(List<String> vaccinationPosts) {
    this.vaccinationPosts = vaccinationPosts;
  }

  public List<String> getVaccinations() {
    return vaccinations;
  }

  public void setVaccinations(List<String> vaccinations) {
    this.vaccinations = vaccinations;
  }

  public List<String> getSchedules() {
    return schedules;
  }

  public void setSchedules(List<String> schedules) {
    this.schedules = schedules;
  }

  public List<String> getVaccinationPlans() {
    return vaccinationPlans;
  }

  public void setVaccinationPlans(List<String> vaccinationPlans) {
    this.vaccinationPlans = vaccinationPlans;
  }

  public List<String> getPackages() {
    return packages;
  }

  public void setPackages(List<String> packages) {
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
}
