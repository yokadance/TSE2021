package entities;

import DTO.DTDisease;
import DTO.DTVaccine;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Disease extends BaseModel {

  private String name;
  private String symptons;

  @OneToMany(mappedBy = "disease", fetch = FetchType.LAZY)
  private List<Vaccine> vaccine;

  public Disease() {
    super();
  }

  public Disease(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String symptons,
    List<Vaccine> vaccine
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.symptons = symptons;
    this.vaccine = vaccine;
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

  public List<Vaccine> getVaccine() {
    return vaccine;
  }

  public void setVaccine(List<Vaccine> vaccine) {
    this.vaccine = vaccine;
  }

  public DTDisease getDTDisease() {
    //        ModelMapper modelMapper = new ModelMapper();
    //        DTDisease dt = modelMapper.map(this, DTDisease.class);
    //        return dt;

    List<DTVaccine> listDtVaccine = new ArrayList<>();
    if (this.getVaccine() != null) {
      List<Vaccine> vaccineList = this.getVaccine();
      for (Vaccine vac : vaccineList) {
        listDtVaccine.add(vac.getDTVaccine());
      }
    }

    return new DTDisease(
      this.getId(),
      this.getCreateDate(),
      this.getUpdateDate(),
      this.getDeleteDate(),
      this.getUserid(),
      this.getName(),
      this.getSymptons(),
      listDtVaccine
    );
  }
}
