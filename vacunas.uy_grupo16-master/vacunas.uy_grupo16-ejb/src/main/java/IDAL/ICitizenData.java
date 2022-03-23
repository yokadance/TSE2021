package IDAL;

import DTO.DTCitizen;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICitizenData {
  DTCitizen saveCitizen(DTCitizen dtCitizen);
  List<DTCitizen> getCitizens();
  DTCitizen getByIdCitizen(Long id);
  void deleteCitizen(Long id);
  int getCitizenIdByCi(String ci);
  DTCitizen getCitizenByCi(String ci);
  boolean setToken(String ci, String token);
}
