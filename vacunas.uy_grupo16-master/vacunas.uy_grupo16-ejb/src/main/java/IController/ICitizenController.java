package IController;

import DTO.DTCitizen;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICitizenController {
  void saveCitizen(DTCitizen dtCitizen);
  DTCitizen getByIdCitizen(Long id);
  List<DTCitizen> getCitizens();
  void deleteCitizen(Long id);
  int getCitizenIdByCi(String ci);
  DTCitizen getCitizenByCi(String ci);
  boolean setToken(String ci, String token);
}
