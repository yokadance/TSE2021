package DAL;

import DTO.DTAuthority;
import IDAL.IAuthorityData;
import entities.Authority;
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
public class AuthorityData implements IAuthorityData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public DTAuthority saveAuthority(DTAuthority dtAuthority) {
    //    Authority authority = modelMapper.map(dtAuthority, Authority.class);

    Query query = data
      .createQuery("select p from Person p where p.id = :id", Person.class)
      .setParameter("id", dtAuthority.getDtPerson().getId());
    Person person = (Person) query.getSingleResult();
    List<Role> listDtRoles = person.getRoles();

    for (Role role : listDtRoles) {
      if ((role.getName().equalsIgnoreCase("authority")) && (role.getDeleteDate() != null)) {
        Authority authorityDeleted = data.find(Authority.class, role.getId());
        authorityDeleted.setDeleteDate(null);
        authorityDeleted.setUpdateDate(new Date());
        data.merge(authorityDeleted);
        return authorityDeleted.getDTAuthority();
      } else {
        if (role.getName().equalsIgnoreCase("authority")) {
          Authority authorityExist = data.find(Authority.class, role.getId());
          return authorityExist.getDTAuthority();
        }
      }
    }
    Authority authorityNew = new Authority();
    authorityNew.setName("Authority");
    authorityNew.setPerson(person);
    data.persist(authorityNew);
    return authorityNew.getDTAuthority();
  }

  @Override
  public List<DTAuthority> getAuthority() {
    List<Authority> authorityList = data.createQuery("select a from Authority a ").getResultList();
    List<DTAuthority> dtAuthorityList = new ArrayList<DTAuthority>();
    authorityList.forEach(
      authority -> {
        DTAuthority dtAuthority = authority.getDTAuthority();
        dtAuthorityList.add(dtAuthority);
      }
    );
    return dtAuthorityList;
  }

  @Override
  public DTAuthority getByIdAuthority(Long id) {
    return data.find(Authority.class, id).getDTAuthority();
  }

  @Override
  public void deleteAuthority(Long id) {
    Authority authority = data.find(Authority.class, id);
    Date date = new Date();
    authority.setDeleteDate(date);
    data.persist(authority);
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
