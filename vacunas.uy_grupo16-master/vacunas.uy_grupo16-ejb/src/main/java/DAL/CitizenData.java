package DAL;

import DTO.DTCitizen;
import IDAL.ICitizenData;
import entities.Citizen;
import entities.Person;
import entities.Role;
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
public class CitizenData implements ICitizenData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public DTCitizen saveCitizen(DTCitizen dtCitizen) {
    Query query = data
      .createQuery("select p from Person p where p.id = :id", Person.class)
      .setParameter("id", dtCitizen.getDtPerson().getId());
    Person person = (Person) query.getSingleResult();
    List<Role> listDtRoles = person.getRoles();

    for (Role role : listDtRoles) {
      if ((role.getName().equalsIgnoreCase("citizen")) && (role.getDeleteDate() != null)) {
        Citizen citizenDeleted = data.find(Citizen.class, role.getId());
        citizenDeleted.setDeleteDate(null);
        citizenDeleted.setUpdateDate(new Date());
        data.merge(citizenDeleted);
        return citizenDeleted.getDTCitizen();
      } else {
        if (role.getName().equalsIgnoreCase("citizen")) {
          Citizen citizenExist = data.find(Citizen.class, role.getId());
          return citizenExist.getDTCitizen();
        }
      }
    }
    Citizen citizenNew = new Citizen();
    citizenNew.setName("Citizen");
    citizenNew.setPerson(person);
    data.persist(citizenNew);
    return citizenNew.getDTCitizen();
  }

  @Override
  public List<DTCitizen> getCitizens() {
    List<Citizen> citizenList = data.createQuery("select c from Citizen c ").getResultList();
    List<DTCitizen> dtCitizenList = new ArrayList<DTCitizen>();
    citizenList.forEach(
      citizen -> {
        DTCitizen dtCitizen = citizen.getDTCitizen();
        dtCitizenList.add(dtCitizen);
      }
    );
    return dtCitizenList;
  }

  @Override
  public DTCitizen getByIdCitizen(Long id) {
    return data.find(Citizen.class, id).getDTCitizen();
  }

  @Override
  public void deleteCitizen(Long id) {
    Citizen citizen = data.find(Citizen.class, id);
    Date date = new Date();
    citizen.setDeleteDate(date);
    data.persist(citizen);
  }

  @Override
  public int getCitizenIdByCi(String ci) {
    try {
      Long idl = (Long) data.createQuery("select c.id from Citizen c where c.person.ci=:ci").setParameter("ci", ci).getSingleResult();
      return idl.intValue();
    } catch (NoResultException nre) {
      return 0;
    }
  }

  @Override
  public DTCitizen getCitizenByCi(String ci) {
    try {
      DTCitizen dtCitizen = data
        .createQuery("select c from Citizen c where c.person.ci=:ci", Citizen.class)
        .setParameter("ci", ci)
        .getSingleResult()
        .getDTCitizen();
      return dtCitizen;
    } catch (NoResultException nre) {
      return null;
    }
  }

  @Override
  public boolean setToken(String ci, String token) {
    try {
      Citizen citizen = data
        .createQuery("select c from Citizen c where c.person.ci=:ci", Citizen.class)
        .setParameter("ci", ci)
        .getSingleResult();
      citizen.setToken(token);
      return true;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
