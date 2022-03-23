package IController;

import DTO.DTVaccinationAct;
import DTO.DTVaccinationActView;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationActController {
  List<DTVaccinationAct> listVaccinationActs();
  void saveVaccinationAct(DTVaccinationAct dtVaccinationAct);
  void deleteVaccinationAct(Long id);
  DTVaccinationAct getVaccinationActById(Long id);
  List<DTVaccinationActView> vaccinationActByCi(String ci);
  List<DTVaccinationActView> getVaccinationActsView();
}
