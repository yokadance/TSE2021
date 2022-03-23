package Controller;

import DTO.DTVaccinationPost;
import DTO.DTVaccinator;
import DTO.DTVaccinatorView;
import IController.IVaccinationPostController;
import IController.IVaccinatorController;
import IDAL.IVaccinatorData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class VaccinatorController implements IVaccinatorController {

  @EJB
  IVaccinatorData iVaccinatorData;

  @Inject
  IVaccinationPostController iVaccinationPostController;

  @Override
  public void saveVaccinator(DTVaccinator dtVaccinator) {
    iVaccinatorData.saveVaccinator(dtVaccinator);
  }

  @Override
  public DTVaccinator getByIdVaccinator(Long id) {
    return iVaccinatorData.getByIdVaccinator(id);
  }

  @Override
  public List<DTVaccinator> getVaccinators() {
    return iVaccinatorData.getVaccinators();
  }

  @Override
  public void deleteVaccinator(Long id) {
    iVaccinatorData.deleteVaccinator(id);
  }

  @Override
  public void setVCtoV(Long idVaccinator, Long idVaccinationPost) {
    DTVaccinationPost dtVaccinationPost = iVaccinationPostController.getByIdVaccinationPost(idVaccinationPost);
    iVaccinatorData.setVCtoV(idVaccinator, dtVaccinationPost);
  }

  @Override
  public DTVaccinator getVaccinatorByCi(String ci) {
    return iVaccinatorData.getVaccinatorByCi(ci);
  }

  @Override
  public List<DTVaccinatorView> getVaccinatorDatabyCi(String ci) {
    return iVaccinatorData.getVaccinatorDatabyCi(ci);
  }

  public void setiVaccinatorData(IVaccinatorData iVaccinatorData) {
    this.iVaccinatorData = iVaccinatorData;
  }

  public void setiVaccinationPostController(IVaccinationPostController iVaccinationPostController) {
    this.iVaccinationPostController = iVaccinationPostController;
  }
}
