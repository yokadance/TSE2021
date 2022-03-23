package entities;

import DTO.DTPackage;
import enumerations.PackageState;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Package extends BaseModel {

  private Long packageNumber;
  private Long quantity;
  private PackageState packageState;

  @OneToMany(mappedBy = "aPackage", fetch = FetchType.LAZY)
  private List<VaccinationAct> vaccinationActs;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "batch_package")
  private Batch batch;

  @OneToMany(mappedBy = "aPackage", fetch = FetchType.LAZY)
  private List<Vaccination> vaccination;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccinationPlan_package")
  private VaccinationPlan vaccinationPlan;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccinationCenter_package")
  private VaccinationCenter vaccinationCenter;

  public Package() {}

  public Package(Long packageNumber, Long quantity) {
    this.packageNumber = packageNumber;
    this.quantity = quantity;
  }

  public Package(Long packageNumber, Long quantity, PackageState packageState) {
    this.packageNumber = packageNumber;
    this.quantity = quantity;
    this.packageState = packageState;
  }

  public Package(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    Long packageNumber,
    Long quantity,
    PackageState packageState,
    List<VaccinationAct> vaccinationActs,
    Batch batch,
    List<Vaccination> vaccination,
    VaccinationPlan vaccinationPlan,
    VaccinationCenter vaccinationCenter
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.packageNumber = packageNumber;
    this.quantity = quantity;
    this.packageState = packageState;
    this.vaccinationActs = vaccinationActs;
    this.batch = batch;
    this.vaccination = vaccination;
    this.vaccinationPlan = vaccinationPlan;
    this.vaccinationCenter = vaccinationCenter;
  }

  public PackageState getPackageState() {
    return packageState;
  }

  public void setPackageState(PackageState packageState) {
    this.packageState = packageState;
  }

  public Long getPackageNumber() {
    return packageNumber;
  }

  public void setPackageNumber(Long packageNumber) {
    this.packageNumber = packageNumber;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public List<VaccinationAct> getVaccinationActs() {
    return vaccinationActs;
  }

  public void setVaccinationActs(List<VaccinationAct> vaccinationActs) {
    this.vaccinationActs = vaccinationActs;
  }

  public Batch getBatch() {
    return batch;
  }

  public void setBatch(Batch batch) {
    this.batch = batch;
  }

  public List<Vaccination> getVaccination() {
    return vaccination;
  }

  public void setVaccination(List<Vaccination> vaccination) {
    this.vaccination = vaccination;
  }

  public VaccinationPlan getVaccinationPlan() {
    return vaccinationPlan;
  }

  public void setVaccinationPlan(VaccinationPlan vaccinationPlan) {
    this.vaccinationPlan = vaccinationPlan;
  }

  public VaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public DTPackage getDTPackage() {
    ModelMapper modelMapper = new ModelMapper();
    DTPackage dt = modelMapper.map(this, DTPackage.class);
    return dt;
  }
}
