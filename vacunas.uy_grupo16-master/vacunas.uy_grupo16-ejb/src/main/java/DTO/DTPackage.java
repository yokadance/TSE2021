package DTO;

import enumerations.PackageState;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTPackage extends DTBaseModel implements Serializable {

  private Long packageNumber;
  private Long quantity;
  private PackageState packageState;
  private List<String> vaccinationActs;
  private String batch;
  private List<String> vaccination;
  private String vaccinationPlan;
  private String vaccinationCenter;

  public DTPackage() {
    super();
  }

  public DTPackage(Long packageNumber, Long quantity) {
    this.packageNumber = packageNumber;
    this.quantity = quantity;
  }

  public DTPackage(Long packageNumber, Long quantity, PackageState packageState) {
    this.packageNumber = packageNumber;
    this.quantity = quantity;
    this.packageState = packageState;
  }

  public DTPackage(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    Long packageNumber,
    Long quantity,
    PackageState packageState,
    List<String> vaccinationActs,
    String batch,
    List<String> vaccination,
    String vaccinationPlan,
    String vaccinationCenter
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.packageNumber = packageNumber;
    this.quantity = quantity;
    this.packageState = packageState;
    this.vaccinationActs = vaccinationActs;
    this.batch = batch;
    this.vaccination = vaccination;
    this.vaccinationPlan = vaccinationPlan;
    this.vaccinationCenter = vaccinationCenter;
  }

  public PackageState getPackageState() {
    return packageState;
  }

  public void setPackageState(PackageState packageState) {
    this.packageState = packageState;
  }

  public Long getPackageNumber() {
    return packageNumber;
  }

  public void setPackageNumber(Long packageNumber) {
    this.packageNumber = packageNumber;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public List<String> getVaccinationActs() {
    return vaccinationActs;
  }

  public void setVaccinationActs(List<String> vaccinationActs) {
    this.vaccinationActs = vaccinationActs;
  }

  public String getBatch() {
    return batch;
  }

  public void setBatch(String batch) {
    this.batch = batch;
  }

  public List<String> getVaccination() {
    return vaccination;
  }

  public void setVaccination(List<String> vaccination) {
    this.vaccination = vaccination;
  }

  public String getVaccinationPlan() {
    return vaccinationPlan;
  }

  public void setVaccinationPlan(String vaccinationPlan) {
    this.vaccinationPlan = vaccinationPlan;
  }

  public String getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(String vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }
}
