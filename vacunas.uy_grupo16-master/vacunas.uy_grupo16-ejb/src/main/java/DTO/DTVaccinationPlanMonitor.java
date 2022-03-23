package DTO;

import java.io.Serializable;
import java.util.List;

public class DTVaccinationPlanMonitor implements Serializable {

  private Long vPlanId;
  private String vPlanName;
  private String vaccineName;
  private String diseaseName;
  private String startDate;
  private String endDate;
  private String today;
  private String totalDosesAsssigned;
  private Integer doseQuantity;
  private Long doses1;
  private Long doses2;
  private Long doses3;
  private Long doses4;
  private Long doses5;
  private List<DtMonitorDate> dates;

  public DTVaccinationPlanMonitor() {}

  public DTVaccinationPlanMonitor(
    Long vPlanId,
    String vPlanName,
    String vaccineName,
    String diseaseName,
    String startDate,
    String endDate,
    String today,
    Long doses1,
    Long doses2,
    Long doses3,
    Long doses4,
    Long doses5
  ) {
    this.vPlanId = vPlanId;
    this.vPlanName = vPlanName;
    this.vaccineName = vaccineName;
    this.diseaseName = diseaseName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.today = today;
    this.doses1 = doses1;
    this.doses2 = doses2;
    this.doses3 = doses3;
    this.doses4 = doses4;
    this.doses5 = doses5;
  }

  public Long getvPlanId() {
    return vPlanId;
  }

  public void setvPlanId(Long vPlanId) {
    this.vPlanId = vPlanId;
  }

  public String getvPlanName() {
    return vPlanName;
  }

  public void setvPlanName(String vPlanName) {
    this.vPlanName = vPlanName;
  }

  public String getVaccineName() {
    return vaccineName;
  }

  public void setVaccineName(String vaccineName) {
    this.vaccineName = vaccineName;
  }

  public String getDiseaseName() {
    return diseaseName;
  }

  public void setDiseaseName(String diseaseName) {
    this.diseaseName = diseaseName;
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

  public String getToday() {
    return today;
  }

  public void setToday(String today) {
    this.today = today;
  }

  public Long getDoses1() {
    return doses1;
  }

  public void setDoses1(Long doses1) {
    this.doses1 = doses1;
  }

  public Long getDoses2() {
    return doses2;
  }

  public void setDoses2(Long doses2) {
    this.doses2 = doses2;
  }

  public Long getDoses3() {
    return doses3;
  }

  public void setDoses3(Long doses3) {
    this.doses3 = doses3;
  }

  public Long getDoses4() {
    return doses4;
  }

  public void setDoses4(Long doses4) {
    this.doses4 = doses4;
  }

  public Long getDoses5() {
    return doses5;
  }

  public void setDoses5(Long doses5) {
    this.doses5 = doses5;
  }

  public List<DtMonitorDate> getDates() {
    return dates;
  }

  public void setDates(List<DtMonitorDate> dates) {
    this.dates = dates;
  }

  public String getTotalDosesAsssigned() {
    return totalDosesAsssigned;
  }

  public void setTotalDosesAsssigned(String totalDosesAsssigned) {
    this.totalDosesAsssigned = totalDosesAsssigned;
  }

  public Integer getDoseQuantity() {
    return doseQuantity;
  }

  public void setDoseQuantity(Integer doseQuantity) {
    this.doseQuantity = doseQuantity;
  }

  @Override
  public String toString() {
    return (
      "DTVaccinationPlanMonitor{" +
      "vPlanId=" +
      vPlanId +
      ", vPlanName='" +
      vPlanName +
      '\'' +
      ", vaccineName='" +
      vaccineName +
      '\'' +
      ", diseaseName='" +
      diseaseName +
      '\'' +
      ", startDate='" +
      startDate +
      '\'' +
      ", endDate='" +
      endDate +
      '\'' +
      ", today='" +
      today +
      '\'' +
      ", totalDosesAsssigned='" +
      totalDosesAsssigned +
      '\'' +
      ", doses1=" +
      doses1 +
      ", doses2=" +
      doses2 +
      ", doses3=" +
      doses3 +
      ", doses4=" +
      doses4 +
      ", doses5=" +
      doses5 +
      ", dates=" +
      dates +
      '}'
    );
  }
}
