package IDAL;

import DTO.DTDisease;
import DTO.DTVaccine;

import java.util.List;

public interface IDiseaseData {
  public Long saveDisease(DTDisease dtDisease);

  public DTDisease getDiseaseById(Long id);

  public void deleteDisease(Long id);

  public List<DTDisease> listDiseases();

  List<DTDisease> getDiseaseByName(String name);

  void addVaccineToDisease(Long diseaseId, DTVaccine dtVaccine);

  Long getDiseaseIdByName(String name);

  boolean checkDiseaseNameAvailability(String disName);
}
