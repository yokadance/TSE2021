package entities;

import DTO.DTRole;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Role extends BaseModel {

  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "person_roles")
  private Person person;

  public Role() {
    super();
  }

  public Role(Long id, Date createDate, Date updateDate, Date deleteDate, String userid, String name, Person person) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.person = person;
  }

  public Role(DTRole dtRole) {
    super(dtRole.getId(), dtRole.getCreateDate(), dtRole.getUpdateDate(), dtRole.getDeleteDate(), dtRole.getUserid());
    this.name = dtRole.getName();
    this.person = new Person(dtRole.getDtPerson());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public DTRole getDTRole() {
    if (this instanceof Administrator) {
      return ((Administrator) this).getDTAdministrator();
    }else

    if (this instanceof Citizen) {
    return ((Citizen) this).getDTCitizen();
    }else

    if (this instanceof Vaccinator) {
      return ((Vaccinator) this).getDTVaccinator();
    }else

    if (this instanceof Authority) {
      return ((Authority) this).getDTAuthority();
    }else

    return null;


  }
}
