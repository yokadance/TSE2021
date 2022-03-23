package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTVaccine extends DTBaseModel implements Serializable {

  private String name;
  private int doseQuantity;
  private float temperature;
  private int monthQuantity;
  private int inmunity;
  private List<String> batches;
  private List<String> restrictions;
  private String laboratory;
  private String disease;

  public DTVaccine() {
    super();
  }

  public DTVaccine(
          String name,
          int doseQuantity,
          float temperature,
          int monthQuantity,
          int inmunity
  ) {
    this.name = name;
    this.doseQuantity = doseQuantity;
    this.temperature = temperature;
    this.monthQuantity = monthQuantity;
    this.inmunity = inmunity;
  }

  public DTVaccine(
          String name,
          int doseQuantity,
          float temperature,
          int monthQuantity,
          int inmunity,
          String disease
  ) {
    this.name = name;
    this.doseQuantity = doseQuantity;
    this.temperature = temperature;
    this.monthQuantity = monthQuantity;
    this.inmunity = inmunity;
    this.disease = disease;
  }

  public DTVaccine(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    int doseQuantity,
    float temperature,
    int monthQuantity,
    int inmunity,
    List<String> batches,
    List<String> restrictions,
    String laboratory,
    String disease
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.doseQuantity = doseQuantity;
    this.temperature = temperature;
    this.monthQuantity = monthQuantity;
    this.inmunity = inmunity;
    this.batches = batches;
    this.restrictions = restrictions;
    this.laboratory = laboratory;
    this.disease = disease;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDoseQuantity() {
    return doseQuantity;
  }

  public void setDoseQuantity(int doseQuantity) {
    this.doseQuantity = doseQuantity;
  }

  public float getTemperature() {
    return temperature;
  }

  public void setTemperature(float temperature) {
    this.temperature = temperature;
  }

  public int getMonthQuantity() {
    return monthQuantity;
  }

  public void setMonthQuantity(int monthQuantity) {
    this.monthQuantity = monthQuantity;
  }

  public int getInmunity() {
    return inmunity;
  }

  public void setInmunity(int inmunity) {
    this.inmunity = inmunity;
  }

  public List<String> getBatches() {
    return batches;
  }

  public void setBatches(List<String> batches) {
    this.batches = batches;
  }

  public List<String> getRestrictions() {
    return restrictions;
  }

  public void setRestrictions(List<String> restrictions) {
    this.restrictions = restrictions;
  }

  public String getLaboratory() {
    return laboratory;
  }

  public void setLaboratory(String laboratory) {
    this.laboratory = laboratory;
  }

  public String getDisease() {
    return disease;
  }

  public void setDisease(String disease) {
    this.disease = disease;
  }
}
