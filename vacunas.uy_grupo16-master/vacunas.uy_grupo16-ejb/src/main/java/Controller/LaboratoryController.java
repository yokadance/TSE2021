package Controller;

import DTO.DTLaboratory;
import DTO.DTVaccine;
import IController.ILaboratoryController;
import IDAL.ILaboratoryData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class LaboratoryController implements ILaboratoryController {

  @EJB
  ILaboratoryData iLaboratoryData;

  public void saveLaboratory(DTLaboratory dtLaboratory) {
    iLaboratoryData.saveLaboratory(dtLaboratory);
  }

  public Long newLaboratory(String name, String userId, String origin, String email, String phone, List<DTVaccine> dtVaccines) {
    //Date date = new Date();

    return iLaboratoryData.saveLaboratory(new DTLaboratory(0L, new Date(), null, null, userId, name, origin, email, phone, dtVaccines));
  }

  public DTLaboratory getLaboratoryById(Long id) {
    return iLaboratoryData.getLaboratoryById(id);
  }

  public void updateLaboratory(DTLaboratory dtLaboratory) {
    iLaboratoryData.saveLaboratory(dtLaboratory);
  }

  public List<DTLaboratory> listLaboratories() {
    return iLaboratoryData.listLaboratorys();
  }

  public void deleteLaboratory(Long id) {
    iLaboratoryData.deleteLaboratory(id);
  }

  public List<DTLaboratory> getLaboratoryByName(String name) {
    return iLaboratoryData.getLaboratoryByName(name);
  }

  public Long getLaboratoryIdByName(String name) {
    return iLaboratoryData.getLaboratoryIdByName(name);
  }

  public void setiLaboratoryData(ILaboratoryData iLaboratoryData) {
    this.iLaboratoryData = iLaboratoryData;
  }

  public boolean checkLaboratoryNameAvailability(String labName){return iLaboratoryData.checkLaboratoryNameAvailability(labName);}
}
