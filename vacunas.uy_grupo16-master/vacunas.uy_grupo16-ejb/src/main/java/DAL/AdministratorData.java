package DAL;

import DTO.DTAdministrator;
import IDAL.IAdministratorData;
import entities.Administrator;
import entities.Person;
import entities.Role;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class AdministratorData implements IAdministratorData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public DTAdministrator saveAdministrator(DTAdministrator dtAdministrator) {
    //    Administrator administrator = modelMapper.map(dtAdministrator, Administrator.class);
    Query query = data
      .createQuery("select p from Person p where p.id = :id", Person.class)
      .setParameter("id", dtAdministrator.getDtPerson().getId());
    Person person = (Person) query.getSingleResult();
    List<Role> listDtRoles = person.getRoles();

    for (Role role : listDtRoles) {
      if ((role.getName().equalsIgnoreCase("administrator")) && (role.getDeleteDate() != null)) {
        Administrator administratorDeleted = data.find(Administrator.class, role.getId());
        administratorDeleted.setDeleteDate(null);
        administratorDeleted.setUpdateDate(new Date());
        data.merge(administratorDeleted);
        return administratorDeleted.getDTAdministrator();
      } else {
        if (role.getName().equalsIgnoreCase("administrator")) {
          Administrator administratorExist = data.find(Administrator.class, role.getId());
          return administratorExist.getDTAdministrator();
        }
      }
    }
    Administrator administratorNew = new Administrator();
    administratorNew.setName("Administrator");
    administratorNew.setPerson(person);
    data.persist(administratorNew);
    return administratorNew.getDTAdministrator();
  }

  @Override
  public List<DTAdministrator> getAdministrators() {
    List<Administrator> administratorList = data.createQuery("select a from Administrator a ").getResultList();
    List<DTAdministrator> dtAdministratorList = new ArrayList<DTAdministrator>();
    administratorList.forEach(
      administrator -> {
        DTAdministrator dtAdministrator = administrator.getDTAdministrator();
        dtAdministratorList.add(dtAdministrator);
      }
    );
    return dtAdministratorList;
  }

  @Override
  public DTAdministrator getByIdAdministrator(Long id) {
    return data.find(Administrator.class, id).getDTAdministrator();
  }

  @Override
  public void deleteAdministrator(Long id) {
    Administrator ad = data.find(Administrator.class, id);
    Date date = new Date();
    ad.setDeleteDate(date);
    data.persist(ad);
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
