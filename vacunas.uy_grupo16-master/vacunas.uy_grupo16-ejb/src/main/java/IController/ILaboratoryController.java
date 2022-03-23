package IController;

import DTO.DTLaboratory;
import DTO.DTVaccine;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ILaboratoryController {
  void saveLaboratory(DTLaboratory dtLaboratory);

  public Long newLaboratory(String name, String userId, String origin, String email, String phone, List<DTVaccine> dtVaccines);

  public DTLaboratory getLaboratoryById(Long id);

  public void updateLaboratory(DTLaboratory dtLaboratory);

  public List<DTLaboratory> listLaboratories();

  public void deleteLaboratory(Long id);

  public List<DTLaboratory> getLaboratoryByName(String name);

  Long getLaboratoryIdByName(String name);

  boolean checkLaboratoryNameAvailability(String labName);


}
