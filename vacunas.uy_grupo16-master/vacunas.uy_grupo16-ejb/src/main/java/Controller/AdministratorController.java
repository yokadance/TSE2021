package Controller;

import DTO.DTAdministrator;
import IController.IAdministratorController;
import IDAL.IAdministratorData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class AdministratorController implements IAdministratorController {

  @EJB
  IAdministratorData iAdministratorData;

  @Override
  public void saveAdministrator(DTAdministrator dtAdministrator) {
    iAdministratorData.saveAdministrator(dtAdministrator);
  }

  @Override
  public DTAdministrator getByIdAdministrator(Long id) {
    return iAdministratorData.getByIdAdministrator(id);
  }

  @Override
  public List<DTAdministrator> getAdministrators() {
    return iAdministratorData.getAdministrators();
  }

  @Override
  public void deleteAdministrator(Long id) {
    iAdministratorData.deleteAdministrator(id);
  }

  public void setiAdministratorData(IAdministratorData iAdministratorData) {
    this.iAdministratorData = iAdministratorData;
  }
}
