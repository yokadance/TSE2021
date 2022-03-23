package DAL;

import DTO.*;
import IDAL.IPersonData;
import entities.*;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class PersonData implements IPersonData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  public void savePerson(DTPerson dtp) {
    Person person = modelMapper.map(dtp, Person.class);
    try {
      String ci = person.getCi();
      Person personToUpdate = data
        .createQuery("select p from Person p where p.ci = :ci", Person.class)
        .setParameter("ci", ci)
        .getSingleResult();
      if (person.getBirthday() != null) {
        personToUpdate.setBirthday(person.getBirthday());
      }
      if (person.getEmail() != null) {
        personToUpdate.setEmail(person.getEmail());
      }
      if (person.getSex() != null) {
        personToUpdate.setSex(person.getSex());
      }
      if (person.getName() != null) {
        personToUpdate.setName(person.getName());
      }
      if (person.getLastname() != null) {
        personToUpdate.setLastname(person.getLastname());
      }
      if(personToUpdate.getDeleteDate() != null){
        personToUpdate.setDeleteDate(null);
      }
      data.merge(personToUpdate);
    } catch (NoResultException e) {
      data.persist(person);
      System.out.println(e);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public int createPersonCitizen(DTPerson dtp, DTCitizen dtCitizen) {
    Person person = new Person();
    person = modelMapper.map(dtp, Person.class);
    Citizen citizen = modelMapper.map(dtCitizen, Citizen.class);
    Citizen c1 = data.getReference(Citizen.class, citizen.getId());
    c1.setPerson(person);
    data.persist(person);
    return citizen.getId().intValue();
  }

  public int createPersonAdministrator(DTPerson dtp, DTAdministrator dtAdministrator) {
    Person person = modelMapper.map(dtp, Person.class);
    Administrator administrator = modelMapper.map(dtAdministrator, Administrator.class);
    List<Role> roleList = new ArrayList<>();
    roleList.add(administrator);
    person.setRoles(roleList);
    data.merge(person);
    return administrator.getId().intValue();
  }

  public int createPersonAuthority(DTPerson dtp, DTAuthority dtAuthority) {
    Person person = modelMapper.map(dtp, Person.class);
    Authority authority = modelMapper.map(dtAuthority, Authority.class);
    List<Role> roleList = new ArrayList<>();
    if (person.getId() != null) {
      //verificar q existe y actualizar
      person.getRoles().add(authority);
      data.merge(person);
    } else {
      roleList.add(authority);
      person.setRoles(roleList);
      data.merge(person);
    }
    return authority.getId().intValue();
  }

  public int createPersonVaccinator(DTPerson dtp, DTVaccinator dtVaccinator) {
    Person person = modelMapper.map(dtp, Person.class);
    Vaccinator vaccinator = modelMapper.map(dtVaccinator, Vaccinator.class);
    List<Role> roleList = new ArrayList<>();
    if (person.getRoles() != null) {
      person.getRoles().add(vaccinator);
      data.merge(person);
    } else {
      roleList.add(vaccinator);
      person.setRoles(roleList);
      data.merge(person);
    }
    return vaccinator.getId().intValue();
  }

  public DTPerson getByIdPerson(Long id) {
    DTPerson dto = data.find(Person.class, id).getDTPerson();
    return dto;
  }

  public List<DTPerson> getPersons() {
    List<Person> personList = data.createQuery("select p from Person p where p.deleteDate is null").getResultList();
    List<DTPerson> dtPersonList = new ArrayList<DTPerson>();
    for (Person person : personList) {
      dtPersonList.add(person.getDTPerson());
    }
    return dtPersonList;
  }

  public List<DTBasicPerson> getBasicPersons() {
    List<Person> personList = data.createQuery("select p from Person p").getResultList();
    List<DTBasicPerson> dtBasicPersonList = new ArrayList<>();
    for (Person person : personList) {
      dtBasicPersonList.add(person.getDTBasicPerson());
    }
    return dtBasicPersonList;
  }

  public void deletePerson(Long id) {
    Person p = data.find(Person.class, id);
    Date date = new Date();
    p.setDeleteDate(date);
    data.persist(p);
  }

  public DTPerson getPersonByCI(String ci) throws Exception {
    //    DTPerson dto = data.find(Person.class, ci).getDTPerson();
   // DTPerson dto = null;
    DTPerson dto = new DTPerson();
    Query query = data.createQuery("select p from Person p where p.ci = :ci", Person.class).setParameter("ci", ci);
    List<Person> listQuery = query.getResultList();

    if (listQuery != null) {
      for (Person person : listQuery) {
        dto=person.getDTPerson();
       // return person.getDTPerson();
      }
    }
    return dto;
  }

  public boolean existPersonByCI(String ci) throws Exception {
    DTPerson dto = getPersonByCI(ci);

    if (dto == null) return false; else return true;
  }

  public List<String> getUserRoles(String ci) {
    List<String> ret = new ArrayList<>();

    Query query = data.createQuery("select p from Person p where p.ci = :ci").setParameter("ci", ci);
    List<Person> personList = query.getResultList();

    if (personList != null) {
      for (Person p : personList) {
        for (Role r : p.getRoles()) {
          String roleName = r.getName();
          ret.add(roleName);
        }
      }
    }

    return ret;
  }

  public List<DTRole> getDtRolesFromUser(String ci) {
    List<DTRole> ret = new ArrayList<>();

    Query query = data.createQuery("select p from Person p where p.ci = :ci").setParameter("ci", ci);
    List<Person> personList = query.getResultList();

    if (personList != null) {
      for (Person p : personList) {
        for (Role r : p.getRoles()) {
          if (r.getDeleteDate() == null) {
            DTRole dtr = r.getDTRole();
            dtr.setDtPerson(p.getDTPerson());
            ret.add(dtr);
          }
        }
      }
    }

    return ret;
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
