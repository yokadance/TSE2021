package Controller;

import DTO.DTCitizen;
import DTO.DTRole;
import IController.IRoleController;
import IDAL.IRoleData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class RoleController implements IRoleController {

  @EJB
  IRoleData iroledata;

  public List<DTRole> getRoles() {
    return iroledata.getRoles();
  }

  public void saveRole(DTCitizen dtCitizen) {
    iroledata.saveRole(dtCitizen);
  }

  public void deleteRole(DTRole dtRole) {
    iroledata.deleteRole(dtRole);
  }

  public void saveAnyRole(DTRole dtRole) {
    iroledata.saveAnyRole(dtRole);
  }

  public void setIroledata(IRoleData iroledata) {
    this.iroledata = iroledata;
  }
}
