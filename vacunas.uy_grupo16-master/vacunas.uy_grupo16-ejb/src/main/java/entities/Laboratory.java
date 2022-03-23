package entities;

import DTO.DTLaboratory;
import DTO.DTVaccine;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Laboratory extends BaseModel {

  private String name;
  private String origin;
  private String email;
  private String phone;

  @OneToMany(mappedBy = "laboratory", fetch = FetchType.LAZY)
  private List<Vaccine> vaccine;

  public Laboratory() {
    super();
  }

  public Laboratory(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String origin,
    String email,
    String phone,
    List<Vaccine> vaccine
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.origin = origin;
    this.email = email;
    this.phone = phone;
    this.vaccine = vaccine;
  }

  public Laboratory(DTLaboratory dtLab) {
    super();
    List<Vaccine> vaccines = null;
    this.name = dtLab.getName();
    this.origin = dtLab.getOrigin();
    this.email = dtLab.getEmail();
    this.phone = dtLab.getPhone();
    this.vaccine = vaccines;
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

  public List<Vaccine> getVaccine() {
    return vaccine;
  }

  public void setVaccine(List<Vaccine> vaccine) {
    this.vaccine = vaccine;
  }

  public DTLaboratory getDTLaboratory() {
    //        ModelMapper modelMapper = new ModelMapper();
    //        DTLaboratory dt = modelMapper.map(this, DTLaboratory.class);
    //        return dt;

    List<DTVaccine> listDtVaccine = new ArrayList<>();
    if (this.getVaccine() != null) {
      List<Vaccine> vaccineList = this.getVaccine();
      for (Vaccine vac : vaccineList) {
        listDtVaccine.add(vac.getDTVaccine());
      }
    }

    return new DTLaboratory(
      this.getId(),
      this.getCreateDate(),
      this.getUpdateDate(),
      this.getDeleteDate(),
      this.getUserid(),
      this.getName(),
      this.getOrigin(),
      this.getEmail(),
      this.getPhone(),
      listDtVaccine
    );
  }
}
