package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTDisease extends DTBaseModel implements Serializable {

  private String name;
  private String symptons;
  private List<DTVaccine> vaccine;

  public DTDisease() {
    super();
  }

  public DTDisease(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String symptons,
    List<DTVaccine> vaccine
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.symptons = symptons;
    this.vaccine = vaccine;
  }

  public DTDisease(String name, String symptons) {
    this.name = name;
    this.symptons = symptons;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSymptons() {
    return symptons;
  }

  public void setSymptons(String symptons) {
    this.symptons = symptons;
  }

  public List<DTVaccine> getVaccine() {
    return vaccine;
  }

  public void setVaccine(List<DTVaccine> vaccine) {
    this.vaccine = vaccine;
  }
}
