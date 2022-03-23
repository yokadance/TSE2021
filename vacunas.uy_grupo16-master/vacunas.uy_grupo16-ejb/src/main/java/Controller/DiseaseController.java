package Controller;

import DTO.DTDisease;
import DTO.DTVaccine;
import IController.IDiseaseController;
import IDAL.IDiseaseData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class DiseaseController implements IDiseaseController {

  @EJB
  IDiseaseData iDiseaseData;

  @Override
  public List<DTDisease> listDiseases() {
    return iDiseaseData.listDiseases();
  }

  @Override
  public Long saveDisease(DTDisease dtDisease) {
    return iDiseaseData.saveDisease(dtDisease);
  }

  @Override
  public void deleteDisease(Long id) {
    iDiseaseData.deleteDisease(id);
  }

  @Override
  public DTDisease getDiseaseById(Long id) {
    return iDiseaseData.getDiseaseById(id);
  }

  @Override
  public List<DTDisease> getDiseaseByName(String name) throws Exception {
    if (name == null) throw new Exception("Ingrese nombre");

    return iDiseaseData.getDiseaseByName(name);
  }

  @Override
  public void addVaccineToDisease(Long diseaseId, DTVaccine dtVaccine) {
    iDiseaseData.addVaccineToDisease(diseaseId, dtVaccine);
  }

  @Override
  public boolean checkDiseaseNameAvailability(String disName){ return iDiseaseData.checkDiseaseNameAvailability(disName);}

  @Override
  public Long getDiseaseIdByName(String name) {
    return iDiseaseData.getDiseaseIdByName(name);
  }

  public void setiDiseaseData(IDiseaseData iDiseaseData) {
    this.iDiseaseData = iDiseaseData;
  }


}
