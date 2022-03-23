package Controller;

import DTO.DTDisease;
import DTO.DTLaboratory;
import DTO.DTVaccine;
import DTO.DTVaccineReport;
import IController.IVaccineController;
import IDAL.IVaccineData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class VaccineController implements IVaccineController {

  @EJB
  IVaccineData iVaccineData;

  @Override
  public List<DTVaccine> listVaccines() {
    return iVaccineData.listVaccines();
  }

  @Override
  public Long saveVaccine(DTVaccine dtVaccine) {
    return iVaccineData.saveVaccine(dtVaccine);
  }

  @Override
  public void deleteVaccine(Long id) {
    iVaccineData.deleteVaccine(id);
  }

  @Override
  public DTVaccine getVaccineById(Long id) {
    return iVaccineData.getVaccineById(id);
  }

  @Override
  public void addDiseaseToVaccine(Long vaccineId, DTDisease dtDisease) {
    iVaccineData.addDiseaseToVaccine(vaccineId, dtDisease);
  }

  @Override
  public void addLaboratoryToVaccine(Long vaccineId, DTLaboratory dtLaboratory) {
    iVaccineData.addLaboratoryToVaccine(vaccineId, dtLaboratory);
  }

  @Override
  public List<DTVaccineReport> getVaccineReport(Long vaccineId) {
    return iVaccineData.getVaccineReport(vaccineId);
  }

  public void setiVaccineData(IVaccineData iVaccineData) {
    this.iVaccineData = iVaccineData;
  }
}
