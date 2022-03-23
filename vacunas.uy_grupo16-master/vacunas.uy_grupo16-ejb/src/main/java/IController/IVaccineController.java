package IController;

import DTO.DTDisease;
import DTO.DTLaboratory;
import DTO.DTVaccine;
import DTO.DTVaccineReport;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccineController {
  List<DTVaccine> listVaccines();
  Long saveVaccine(DTVaccine dtVaccine);
  void deleteVaccine(Long id);
  DTVaccine getVaccineById(Long id);
  void addDiseaseToVaccine(Long vaccineId, DTDisease dtDisease);
  void addLaboratoryToVaccine(Long vaccineId, DTLaboratory dtLaboratory);
  List<DTVaccineReport> getVaccineReport(Long vaccineId);
}
