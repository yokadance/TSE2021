package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTLogisticPartner extends DTBaseModel implements Serializable {

  private String name;
  private String businessName;
  private String rut;
  private String phone;
  private String email;
  private String reference;
  private String password;
  private List<DTIot> iot;
  private List<DTLogistic> logistics;
  private List<DTPackage> packages;

  public DTLogisticPartner() {
    super();
  }

  public DTLogisticPartner(
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
    List<DTIot> iot,
    List<DTLogistic> logistics
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.businessName = businessName;
    this.rut = rut;
    this.phone = phone;
    this.email = email;
    this.reference = reference;
    this.password = password;
    this.iot = iot;
    this.logistics = logistics;
  }

  public DTLogisticPartner(
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
    List<DTIot> iot,
    List<DTLogistic> logistics,
    List<DTPackage> packages
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<DTIot> getIot() {
    return iot;
  }

  public void setIot(List<DTIot> iot) {
    this.iot = iot;
  }

  public List<DTLogistic> getLogistics() {
    return logistics;
  }

  public void setLogistics(List<DTLogistic> logistics) {
    this.logistics = logistics;
  }

  public List<DTPackage> getPackages() {
    return packages;
  }

  public void setPackages(List<DTPackage> packages) {
    this.packages = packages;
  }
}
