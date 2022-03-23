package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTLaboratory extends DTBaseModel implements Serializable {

  private String name;
  private String origin;
  private String email;
  private String phone;
  private List<DTVaccine> DTVaccines;

  public DTLaboratory() {}

  public DTLaboratory(
          String name,
          String origin,
          String email,
          String phone
  ) {
    this.name = name;
    this.origin = origin;
    this.email = email;
    this.phone = phone;
  }

  public DTLaboratory(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String origin,
    String email,
    String phone,
    List<DTVaccine> dtVaccines
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.origin = origin;
    this.email = email;
    this.phone = phone;
    this.DTVaccines = dtVaccines;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<DTVaccine> getDTVaccines() {
    return DTVaccines;
  }

  public void setDTVaccines(List<DTVaccine> DTVaccines) {
    this.DTVaccines = DTVaccines;
  }
}
