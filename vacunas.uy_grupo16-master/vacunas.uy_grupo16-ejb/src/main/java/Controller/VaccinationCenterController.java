package Controller;

import DTO.DTVaccinationCenter;
import IController.IVaccinationCenterController;
import IDAL.IVaccinationCenterData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class VaccinationCenterController implements IVaccinationCenterController {

  @EJB
  IVaccinationCenterData iVaccinationCenterData;

  //CAMBIO RETORNO YA QUE NO TENGO CÓMO OBTENER ID
  @Override
  public Long newVaccinationCenter(DTVaccinationCenter dtVaccinationCenter) {
    return iVaccinationCenterData.newVaccinationCenter(dtVaccinationCenter);
  }

  @Override
  public List<DTVaccinationCenter> listVaccinationCenters() {
    return iVaccinationCenterData.listVaccinationsCenters();
  }

  @Override
  public Long saveVaccinationCenter(DTVaccinationCenter dtVaccinationCenter) {
    return iVaccinationCenterData.saveVaccinationCenter(dtVaccinationCenter);
  }

  @Override
  public void deleteVaccinationCenter(Long id) {
    iVaccinationCenterData.deleteVaccinationCenter(id);
  }

  @Override
  public void unassignVaccinationCenter(Long centerId, Long planId){ iVaccinationCenterData.unassignVaccinationCenter(centerId, planId);}

  @Override
  public DTVaccinationCenter getVaccinationCenterById(Long id) {
    return iVaccinationCenterData.getVaccinationCenterById(id);
  }

  @Override
  public List<DTVaccinationCenter> getCentersByPlan(Long idVP) {
    return iVaccinationCenterData.getCentersByPlan(idVP);
  }

  @Override
  public List<DTVaccinationCenter> getCentersAvailablesByPlan(Long idVP) {
    return iVaccinationCenterData.getCentersAvailablesByPlan(idVP);
  }

  public void setiVaccinationCenterData(IVaccinationCenterData iVaccinationCenterData) {
    this.iVaccinationCenterData = iVaccinationCenterData;
  }

  @Override
  public String vaccinationCenterPassword() {
    return iVaccinationCenterData.vaccinationCenterPassword();
  }

  public boolean validatePassword(Long vId, String password2check) {
    return iVaccinationCenterData.validatePassword(vId, password2check);
  }
}
