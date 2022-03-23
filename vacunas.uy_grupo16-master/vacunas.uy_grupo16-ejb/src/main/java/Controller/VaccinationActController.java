package Controller;

import DTO.DTVaccinationAct;
import DTO.DTVaccinationActView;
import IController.ICitizenController;
import IController.IPackageController;
import IController.IVaccinationActController;
import IController.IVaccinationPostController;
import IDAL.IVaccinationActData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class VaccinationActController implements IVaccinationActController {

  @EJB
  IVaccinationActData iVaccinationActData;

  @Inject
  ICitizenController iCitizenController;

  @Inject
  IPackageController iPackageController;

  @Inject
  IVaccinationPostController iVaccinationPostController;

  @Override
  public List<DTVaccinationAct> listVaccinationActs() {
    return iVaccinationActData.listVaccinationAct();
  }

  @Override
  public void saveVaccinationAct(DTVaccinationAct dtVaccinationAct) {
    iVaccinationActData.saveVaccinationAct(dtVaccinationAct);
  }

  @Override
  public void deleteVaccinationAct(Long id) {
    iVaccinationActData.deleteVaccinationAct(id);
  }

  @Override
  public DTVaccinationAct getVaccinationActById(Long id) {
    return iVaccinationActData.getVaccinationActById(id);
  }

  @Override
  public List<DTVaccinationActView> vaccinationActByCi(String ci) {
    return iVaccinationActData.vaccinationActByCi(ci);
  }

  public void setiVaccinationActData(IVaccinationActData iVaccinationActData) {
    this.iVaccinationActData = iVaccinationActData;
  }

  public void setiCitizenController(ICitizenController iCitizenController) {
    this.iCitizenController = iCitizenController;
  }

  public void setiPackageController(IPackageController iPackageController) {
    this.iPackageController = iPackageController;
  }

  public void setiVaccinationPostController(IVaccinationPostController iVaccinationPostController) {
    this.iVaccinationPostController = iVaccinationPostController;
  }

  public List<DTVaccinationActView> getVaccinationActsView(){ return iVaccinationActData.getVaccinationActsView(); }
}
