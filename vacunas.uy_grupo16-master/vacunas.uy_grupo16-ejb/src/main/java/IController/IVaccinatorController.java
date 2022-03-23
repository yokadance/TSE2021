package IController;

import DTO.DTVaccinator;
import DTO.DTVaccinatorView;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinatorController {
  void saveVaccinator(DTVaccinator dtVaccinator);
  DTVaccinator getByIdVaccinator(Long id);
  List<DTVaccinator> getVaccinators();
  void deleteVaccinator(Long id);

  void setVCtoV(Long idVaccinator, Long idVaccinationPost);

  DTVaccinator getVaccinatorByCi(String ci);

  List<DTVaccinatorView> getVaccinatorDatabyCi(String ci);
}
