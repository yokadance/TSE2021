package IDAL;

import DTO.DTDisease;
import DTO.DTLaboratory;
import DTO.DTVaccine;
import DTO.DTVaccineReport;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccineData {
  public Long saveVaccine(DTVaccine dtVaccine);

  public DTVaccine getVaccineById(Long id);

  public void deleteVaccine(Long id);

  public List<DTVaccine> listVaccines();

  public void addDiseaseToVaccine(Long vaccineId, DTDisease dtDisease);

  public void addLaboratoryToVaccine(Long vaccineId, DTLaboratory dtLaboratory);

  List<DTVaccineReport> getVaccineReport(Long vaccineId);
}
