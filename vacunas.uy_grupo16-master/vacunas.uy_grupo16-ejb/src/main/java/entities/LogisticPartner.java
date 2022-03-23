package entities;

import DTO.DTIot;
import DTO.DTLogistic;
import DTO.DTLogisticPartner;
import DTO.DTPackage;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class LogisticPartner extends BaseModel {

  private String name;
  private String businessName;
  private String rut;
  private String phone;
  private String email;
  private String reference;
  private String password;

  @OneToMany(fetch = FetchType.LAZY)
  private List<IOT> iot;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Logistic> logistics;

  @OneToMany
  private List<Package> packages;

  public LogisticPartner() {}

  public LogisticPartner(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String businessName,
    String rut,
    String phone,
    String email,
    String reference,
    String password,
    List<IOT> iot,
    List<Logistic> logistics
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.businessName = businessName;
    this.rut = rut;
    this.phone = phone;
    this.email = email;
    this.password = password;
    this.reference = reference;
    this.iot = iot;
    this.logistics = logistics;
  }

  public LogisticPartner(
    String name,
    String businessName,
    String rut,
    String phone,
    String email,
    String reference,
    String password,
    List<IOT> iot,
    List<Logistic> logistics,
    List<Package> packages
  ) {
    this.name = name;
    this.businessName = businessName;
    this.rut = rut;
    this.phone = phone;
    this.email = email;
    this.reference = reference;
    this.password = password;
    this.iot = iot;
    this.logistics = logistics;
    this.packages = packages;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getRut() {
    return rut;
  }

  public void setRut(String rut) {
    this.rut = rut;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public List<IOT> getIot() {
    return iot;
  }

  public void setIot(List<IOT> iot) {
    this.iot = iot;
  }

  public List<Logistic> getLogistics() {
    return logistics;
  }

  public void setLogistics(List<Logistic> logistics) {
    this.logistics = logistics;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public DTLogistic getDTLogistic() {
    ModelMapper modelMapper = new ModelMapper();
    DTLogistic dt = modelMapper.map(this, DTLogistic.class);
    return dt;
  }

  public List<Package> getPackages() {
    return packages;
  }

  public void setPackages(List<Package> packages) {
    this.packages = packages;
  }

  public DTLogisticPartner getDTLogisticPartner() {
    DTLogisticPartner newDTLogPartner = new DTLogisticPartner();
    newDTLogPartner.setId(this.getId());
    newDTLogPartner.setName(this.getName());
    newDTLogPartner.setBusinessName(this.getBusinessName());
    newDTLogPartner.setRut(this.getRut());
    newDTLogPartner.setPhone(this.getPhone());
    newDTLogPartner.setEmail(this.getEmail());
    newDTLogPartner.setReference(this.getReference());

    List<IOT> listIot = new ArrayList<>();
    List<DTIot> listDTIot = new ArrayList<>();

    if (this.getIot() != null) {
      listIot = this.getIot();
      for (IOT iot : listIot) {
        listDTIot.add(iot.getDTIot());
      }
    }
    newDTLogPartner.setIot(listDTIot);

    List<Logistic> listLog = new ArrayList<>();
    List<DTLogistic> listDTLog = new ArrayList<>();

    if (this.getLogistics() != null) {
      listLog = this.getLogistics();
      for (Logistic log : listLog) {
        listDTLog.add(log.getDTLogistic());
      }
    }
    newDTLogPartner.setLogistics(listDTLog);


    List<Package> listPck = new ArrayList<>();
    List<DTPackage> listDTPck = new ArrayList<>();

    if (this.getPackages() != null) {
      listPck = this.getPackages();
      for (Package pck : listPck) {
        listDTPck.add(pck.getDTPackage());
      }
    }
    newDTLogPartner.setPackages(listDTPck);

    return newDTLogPartner;
  }
}
