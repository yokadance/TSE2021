package entities;

import DTO.DTVaccine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Vaccine extends BaseModel {

  private String name;
  private int doseQuantity;
  private float temperature;
  private int monthQuantity;
  private int inmunity;

  @OneToMany(mappedBy = "vaccine", fetch = FetchType.LAZY)
  private List<Batch> batches;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "Vaccine_Restriction",
    joinColumns = { @JoinColumn(name = "fk_Vaccine") },
    inverseJoinColumns = { @JoinColumn(name = "fk_Restriction") }
  )
  private List<Restriction> restrictions;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccine_laboratory")
  private Laboratory laboratory;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccine_disease")
  private Disease disease;

  public Vaccine() {}

  public Vaccine(
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
    List<Batch> batches,
    List<Restriction> restrictions,
    Laboratory laboratory,
    Disease disease
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

  public List<Restriction> getRestrictions() {
    return restrictions;
  }

  public void setRestrictions(List<Restriction> restrictions) {
    this.restrictions = restrictions;
  }

  public Laboratory getLaboratory() {
    return laboratory;
  }

  public void setLaboratory(Laboratory laboratory) {
    this.laboratory = laboratory;
  }

  public Disease getDisease() {
    return disease;
  }

  public void setDisease(Disease disease) {
    this.disease = disease;
  }

  public List<Batch> getBatches() {
    return batches;
  }

  public void setBatches(List<Batch> batches) {
    this.batches = batches;
  }

  public DTVaccine getDTVaccine() {
    List<String> dtBatchList = new ArrayList<>();
    if (this.getBatches() != null) {
      List<Batch> batchList = this.getBatches();
      for (Batch batch : batchList) {
        dtBatchList.add(batch.getId().toString());
      }
    }

    List<String> dtRestrictionList = new ArrayList<>();
    if (this.getRestrictions() != null) {
      List<Restriction> restrictionList = this.getRestrictions();
      for (Restriction restriction : restrictionList) {
        dtRestrictionList.add(restriction.getId().toString());
      }
    }

    String dtLab = "";
    if (this.getLaboratory() != null) {
      dtLab = this.getLaboratory().getId().toString();
    }

    String dtDis = "";
    if (this.getDisease() != null) {
      dtDis = this.getDisease().getId().toString();
    }

    return new DTVaccine(
      this.getId(),
      this.getCreateDate(),
      this.getUpdateDate(),
      this.getDeleteDate(),
      this.getUserid(),
      this.getName(),
      this.getDoseQuantity(),
      this.getTemperature(),
      this.getMonthQuantity(),
      this.getInmunity(),
      dtBatchList,
      dtRestrictionList,
      dtLab,
      dtDis
    );
    //    ModelMapper modelMapper = new ModelMapper();
    //    DTVaccine dt = modelMapper.map(this, DTVaccine.class);
    //    return dt;
  }
}
