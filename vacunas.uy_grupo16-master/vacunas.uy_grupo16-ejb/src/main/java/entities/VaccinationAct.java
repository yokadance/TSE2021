package entities;

import DTO.DTVaccinationAct;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class VaccinationAct extends BaseModel {

  private String observation;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "citizen_VaccinationAct")
  private Citizen citizen;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccinationPost_VaccinationAct")
  private VaccinationPost vaccinationPost;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "package_VaccinationAct")
  private Package aPackage;

  public VaccinationAct() {}

  public VaccinationAct(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String user,
    String observation,
    Citizen citizen,
    VaccinationPost vaccinationPost,
    Package aPackage
  ) {
    super(id, createDate, updateDate, deleteDate, user);
    this.observation = observation;
    this.citizen = citizen;
    this.vaccinationPost = vaccinationPost;
    this.aPackage = aPackage;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public Citizen getCitizen() {
    return citizen;
  }

  public void setCitizen(Citizen citizen) {
    this.citizen = citizen;
  }

  public VaccinationPost getVaccinationPost() {
    return vaccinationPost;
  }

  public void setVaccinationPost(VaccinationPost vaccinationPost) {
    this.vaccinationPost = vaccinationPost;
  }

  public Package getaPackage() {
    return aPackage;
  }

  public void setaPackage(Package aPackage) {
    this.aPackage = aPackage;
  }

  public DTVaccinationAct getDTVaccinationAct() {
    ModelMapper modelMapper = new ModelMapper();
    DTVaccinationAct dt = modelMapper.map(this, DTVaccinationAct.class);
    return dt;
  }
}
