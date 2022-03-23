package DAL;

import DTO.*;
import IDAL.*;
import entities.Citizen;
import entities.Role;
import org.modelmapper.ModelMapper;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class RoleData implements IRoleData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  @EJB
  ICitizenData iCitizenData;

  @EJB
  IAuthorityData iAuthorityData;

  @EJB
  IVaccinatorData iVaccinatorData;

  @EJB
  IAdministratorData iAdministratorData;

  ModelMapper modelMapper = new ModelMapper();

  public DTRole getByIdRole(Long id) {
    DTRole dto = data.find(Role.class, id).getDTRole();
    return dto;
  }

  public void saveRole(DTCitizen dtCitizen) {
    Citizen citizen = modelMapper.map(dtCitizen, Citizen.class);
    if (citizen.getId() == null) {
      data.persist(citizen);
    } else {
      citizen.setCreateDate(data.find(Citizen.class, citizen.getId()).getCreateDate());
      data.merge(citizen);
    }
  }

  public List<DTRole> getRoles() {
    List<Role> roleList = (List<Role>) data.createQuery("select r from Role r").getResultList();
    List<DTRole> dtRoleList = new ArrayList<DTRole>();
    roleList.forEach(
      role -> {
        DTRole DTRole = role.getDTRole();
        dtRoleList.add(DTRole);
      }
    );
    return dtRoleList;
  }

  public void deleteRole(DTRole dtRole) {
    if (dtRole instanceof DTCitizen) iCitizenData.deleteCitizen(dtRole.getId());
    if (dtRole instanceof DTAdministrator) iAdministratorData.deleteAdministrator(dtRole.getId());
    if (dtRole instanceof DTAuthority) iAuthorityData.deleteAuthority(dtRole.getId());
    if (dtRole instanceof DTVaccinator) iVaccinatorData.deleteVaccinator(dtRole.getId());
  }

  public void saveAnyRole(DTRole dtRole) {
    if (dtRole instanceof DTCitizen) iCitizenData.saveCitizen((DTCitizen) dtRole);
    if (dtRole instanceof DTAdministrator) iAdministratorData.saveAdministrator((DTAdministrator) dtRole);
    if (dtRole instanceof DTAuthority) iAuthorityData.saveAuthority((DTAuthority) dtRole);
    if (dtRole instanceof DTVaccinator) iVaccinatorData.saveVaccinator((DTVaccinator) dtRole);
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setiCitizenData(ICitizenData iCitizenData) {
    this.iCitizenData = iCitizenData;
  }

  public void setiAuthorityData(IAuthorityData iAuthorityData) {
    this.iAuthorityData = iAuthorityData;
  }

  public void setiVaccinatorData(IVaccinatorData iVaccinatorData) {
    this.iVaccinatorData = iVaccinatorData;
  }

  public void setiAdministratorData(IAdministratorData iAdministratorData) {
    this.iAdministratorData = iAdministratorData;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
