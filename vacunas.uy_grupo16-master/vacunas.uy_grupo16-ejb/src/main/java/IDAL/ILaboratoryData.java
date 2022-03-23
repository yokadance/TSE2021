package IDAL;

import DTO.DTLaboratory;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ILaboratoryData {
  Long saveLaboratory(DTLaboratory dtLab);
  DTLaboratory getLaboratoryById(Long id);
  void deleteLaboratory(Long id);
  List<DTLaboratory> listLaboratorys();
  List<DTLaboratory> getLaboratoryByName(String name);
  Long getLaboratoryIdByName(String name);
  boolean checkLaboratoryNameAvailability(String labName);

}
