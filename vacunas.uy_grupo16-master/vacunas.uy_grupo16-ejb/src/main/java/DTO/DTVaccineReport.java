package DTO;

import java.io.Serializable;
import java.util.Date;

public class DTVaccineReport extends DTBaseModel implements Serializable {

  private String vaccineName;
  private String planName;
  private String centerName;
  private String diseaseName;
  private Long batchNumber;
  private int batchAvailable;
  private Long pQuantity;
  private Long pNumber;
  private String pState;

  public DTVaccineReport() {
    super();
  }

  public DTVaccineReport(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String vaccineName,
    String planName,
    String centerName,
    String diseaseName,
    Long batchNumber,
    int batchAvailable,
    Long pQuantity,
    Long pNumber,
    String pState
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.vaccineName = vaccineName;
    this.planName = planName;
    this.centerName = centerName;
    this.diseaseName = diseaseName;
    this.batchNumber = batchNumber;
    this.batchAvailable = batchAvailable;
    this.pQuantity = pQuantity;
    this.pNumber = pNumber;
    this.pState = pState;
  }

  public DTVaccineReport(
    String vaccineName,
    String planName,
    String centerName,
    String diseaseName,
    Long batchNumber,
    int batchAvailable,
    Long pQuantity,
    Long pNumber,
    String pState
  ) {
    this.vaccineName = vaccineName;
    this.planName = planName;
    this.centerName = centerName;
    this.diseaseName = diseaseName;
    this.batchNumber = batchNumber;
    this.batchAvailable = batchAvailable;
    this.pQuantity = pQuantity;
    this.pNumber = pNumber;
    this.pState = pState;
  }

  public String getVaccineName() {
    return vaccineName;
  }

  public void setVaccineName(String vaccineName) {
    this.vaccineName = vaccineName;
  }

  public String getPlanName() {
    return planName;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }

  public String getCenterName() {
    return centerName;
  }

  public void setCenterName(String centerName) {
    this.centerName = centerName;
  }

  public String getDiseaseName() {
    return diseaseName;
  }

  public void setDiseaseName(String diseaseName) {
    this.diseaseName = diseaseName;
  }

  public Long getBatchNumber() {
    return batchNumber;
  }

  public void setBatchNumber(Long batchNumber) {
    this.batchNumber = batchNumber;
  }

  public int getBatchAvailable() {
    return batchAvailable;
  }

  public void setBatchAvailable(int batchAvailable) {
    this.batchAvailable = batchAvailable;
  }

  public Long getpQuantity() {
    return pQuantity;
  }

  public void setpQuantity(Long pQuantity) {
    this.pQuantity = pQuantity;
  }

  public Long getpNumber() {
    return pNumber;
  }

  public void setpNumber(Long pNumber) {
    this.pNumber = pNumber;
  }

  public String getpState() {
    return pState;
  }

  public void setpState(String pState) {
    this.pState = pState;
  }
}
