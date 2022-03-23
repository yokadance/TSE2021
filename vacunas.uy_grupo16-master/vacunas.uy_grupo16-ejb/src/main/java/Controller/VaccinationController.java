package Controller;

import DTO.DTVaccination;
import IController.IVaccinationController;
import IDAL.IVaccinationData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class VaccinationController implements IVaccinationController {

  @EJB
  IVaccinationData iVaccinationData;

  @Override
  public void newVaccination(DTVaccination dtVaccination) {
    iVaccinationData.saveVaccination(dtVaccination);
  }

  @Override
  public List<DTVaccination> listVaccination() {
    return iVaccinationData.listVaccinations();
  }

  @Override
  public void saveVaccination(DTVaccination dtVaccination) {
    iVaccinationData.saveVaccination(dtVaccination);
  }

  @Override
  public void deleteVaccination(Long id) {
    iVaccinationData.deleteVaccination(id);
  }

  public DTVaccination getVaccinationById(Long id) {
    DTVaccination dtVaccination = iVaccinationData.getVaccinationById(id);
    return dtVaccination;
  }

  public void setiVaccinationData(IVaccinationData iVaccinationData) {
    this.iVaccinationData = iVaccinationData;
  }
}
