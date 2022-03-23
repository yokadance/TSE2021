package DTO;

import enumerations.Sex;

import java.io.Serializable;
import java.util.Date;

public class DTNewPerson extends DTBaseModel implements Serializable {

  private String idUruguay;
  private String name;
  private String surname;
  private String lastname;
  private String secondlastname;
  private String ci;
  private Sex sex;
  private Date birthday;
  private String email;
  private String role;

  public DTNewPerson() {
    super();
  }

  public DTNewPerson(String idUruguay, String ci, Sex sex, Date birthday, String email, String role) {
    this.idUruguay = idUruguay;
    this.ci = ci;
    this.sex = sex;
    this.birthday = birthday;
    this.email = email;
    this.role = role;
  }

  public DTNewPerson(
    String idUruguay,
    String name,
    String surname,
    String lastname,
    String secondlastname,
    String ci,
    Sex sex,
    Date birthday,
    String email,
    String role
  ) {
    this.idUruguay = idUruguay;
    this.name = name;
    this.surname = surname;
    this.lastname = lastname;
    this.secondlastname = secondlastname;
    this.ci = ci;
    this.sex = sex;
    this.birthday = birthday;
    this.email = email;
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getSecondlastname() {
    return secondlastname;
  }

  public void setSecondlastname(String secondlastname) {
    this.secondlastname = secondlastname;
  }

  public String getIdUruguay() {
    return idUruguay;
  }

  public void setIdUruguay(String idUruguay) {
    this.idUruguay = idUruguay;
  }

  public String getCi() {
    return ci;
  }

  public void setCi(String ci) {
    this.ci = ci;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRoles() {
    return role;
  }

  public void setRoles(String roles) {
    this.role = roles;
  }
}
