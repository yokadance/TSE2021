package entities;

import DTO.DTBasicPerson;
import DTO.DTPerson;
import DTO.DTRole;
import enumerations.Sex;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Person extends BaseModel {

  private String idUruguay;

  private String name;

  private String surname;

  private String lastname;

  private String secondlastname;

  private String ci;

  private Sex sex;

  @Temporal(TemporalType.DATE)
  private Date birthday;

  private String email;

  @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
  private List<Role> roles;

  public Person() {}

  public Person(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String idUruguay,
    String ci,
    Sex sex,
    Date birthday,
    String email,
    List<Role> roles
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.idUruguay = idUruguay;
    this.ci = ci;
    this.sex = sex;
    this.birthday = birthday;
    this.email = email;
    this.roles = roles;
  }

  public Person(DTPerson dtPerson) {
    super(dtPerson.getId(), dtPerson.getCreateDate(), dtPerson.getUpdateDate(), dtPerson.getDeleteDate(), dtPerson.getUserid());
    this.idUruguay = dtPerson.getIdUruguay();
    this.ci = dtPerson.getCi();
    this.sex = dtPerson.getSex();
    this.birthday = dtPerson.getBirthday();
    this.email = dtPerson.getEmail();
    //  this.roles = new Role(dtPerson.getRoles());
  }

  public Person(Long id, String userid, String idUruguay, String ci, Sex sex, Date birthday, String email, List<Role> roles) {
    super(id, userid);
    this.idUruguay = idUruguay;
    this.ci = ci;
    this.sex = sex;
    this.birthday = birthday;
    this.email = email;
    this.roles = roles;
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

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public DTPerson getDTPerson() {
    DTPerson dtPerson = new DTPerson();
    dtPerson.setId(this.getId());
    dtPerson.setLastname(this.getLastname());
    dtPerson.setName(this.getName());
    dtPerson.setSurname(this.getSurname());
    dtPerson.setSecondlastname(this.getSecondlastname());
    dtPerson.setCreateDate(this.getCreateDate());
    dtPerson.setUpdateDate(this.getUpdateDate());
    dtPerson.setDeleteDate(this.getDeleteDate());
    dtPerson.setCi(this.getCi());
    dtPerson.setEmail(this.getEmail());
    dtPerson.setBirthday(this.getBirthday());
    dtPerson.setUserid(this.getUserid());
    dtPerson.setIdUruguay(this.getIdUruguay());
    dtPerson.setSex(this.getSex());

    List<Role> roleList = this.getRoles();
    List<DTRole> roleDtList = new ArrayList<>();

    if (roleList == null) {
      dtPerson.setRoles(roleDtList);
    } else {
      for (Role r : roleList) {
        roleDtList.add(r.getDTRole());
      }
      dtPerson.setRoles(roleDtList);
    }

    return dtPerson;
  }

  public DTBasicPerson getDTBasicPerson() {
    DTBasicPerson dtBasPerson = new DTBasicPerson();
    dtBasPerson.setId(this.getId());
    dtBasPerson.setCreateDate(this.getCreateDate());
    dtBasPerson.setUpdateDate(this.getUpdateDate());
    dtBasPerson.setDeleteDate(this.getDeleteDate());
    dtBasPerson.setCi(this.getCi());
    dtBasPerson.setEmail(this.getEmail());
    dtBasPerson.setBirthday(this.getBirthday());
    dtBasPerson.setUserid(this.getUserid());
    dtBasPerson.setIdUruguay(this.getIdUruguay());
    dtBasPerson.setSex(this.getSex());

    List<Role> roleList = this.getRoles();
    List<String> roleStringList = new ArrayList<>();

    if (roleList == null) {
      dtBasPerson.setRoles(roleStringList);
    } else {
      for (Role r : roleList) {
        roleStringList.add(r.getDTRole().getName());
      }
      dtBasPerson.setRoles(roleStringList);
    }

    return dtBasPerson;
  }
}
