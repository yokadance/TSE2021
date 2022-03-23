package entities;

import DTO.DTVaccination;
import enumerations.VaccinationState;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Vaccination extends Event {

  private VaccinationState state;

  @ManyToOne(fetch = FetchType.EAGER)
  private VaccinationCenter vaccinationCenter;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "package_vaccination")
  private Package aPackage;

  public Vaccination() {}

  public Vaccination(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String user,
    Date date,
    VaccinationState state,
    VaccinationCenter vaccinationCenter,
    Package aPackage
  ) {
    super(id, createDate, updateDate, deleteDate, user, date);
    this.state = state;
    this.vaccinationCenter = vaccinationCenter;
    this.aPackage = aPackage;
  }

  public VaccinationState getState() {
    return state;
  }

  public void setState(VaccinationState state) {
    this.state = state;
  }

  public VaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public Package getaPackage() {
    return aPackage;
  }

  public void setaPackage(Package aPackage) {
    this.aPackage = aPackage;
  }

  public DTVaccination getDTVaccination() {
    ModelMapper modelMapper = new ModelMapper();
    DTVaccination dt = modelMapper.map(this, DTVaccination.class);
    return dt;
  }
}
