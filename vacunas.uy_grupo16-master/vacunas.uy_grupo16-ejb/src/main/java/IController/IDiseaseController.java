package IController;

import DTO.DTDisease;
import DTO.DTVaccine;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IDiseaseController {
  List<DTDisease> listDiseases();
  Long saveDisease(DTDisease dtDisease);
  void deleteDisease(Long id);
  DTDisease getDiseaseById(Long id);
  List<DTDisease> getDiseaseByName(String name) throws Exception;
  public void addVaccineToDisease(Long diseaseId, DTVaccine dtVaccine);
  Long getDiseaseIdByName(String name);
  boolean checkDiseaseNameAvailability(String disName);
}
