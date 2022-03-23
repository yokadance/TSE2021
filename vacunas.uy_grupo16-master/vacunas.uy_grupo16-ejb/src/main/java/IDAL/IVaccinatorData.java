package IDAL;

import DTO.DTVaccinationPost;
import DTO.DTVaccinator;
import DTO.DTVaccinatorView;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinatorData {
  DTVaccinator saveVaccinator(DTVaccinator dtVaccinator);
  List<DTVaccinator> getVaccinators();
  DTVaccinator getByIdVaccinator(Long id);
  void deleteVaccinator(Long id);
  void setVCtoV(Long idVaccinator, DTVaccinationPost dtVaccinationPost);
  List<DTVaccinatorView> getVaccinatorDatabyCi(String ci);

  DTVaccinator getVaccinatorByCi(String ci);
}
