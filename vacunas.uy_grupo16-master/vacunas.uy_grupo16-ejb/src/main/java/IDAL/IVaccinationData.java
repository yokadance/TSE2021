package IDAL;

import DTO.DTVaccination;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationData {
  public void saveVaccination(DTVaccination dtVaccination);

  public DTVaccination getVaccinationById(Long id);

  public void deleteVaccination(Long id);

  public List<DTVaccination> listVaccinations();
}
