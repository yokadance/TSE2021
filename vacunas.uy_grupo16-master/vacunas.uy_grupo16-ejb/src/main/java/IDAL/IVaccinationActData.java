package IDAL;

import DTO.DTVaccinationAct;
import DTO.DTVaccinationActView;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationActData {
  void saveVaccinationAct(DTVaccinationAct dtVaccinationAct);

  // int createVaccinationAct(DTVaccinationActNew dtVaccinationAct, DTCitizen dtCitizen, DTVaccinationPost dtVaccinationPost, DTPackage dtPackage);

  DTVaccinationAct getVaccinationActById(Long id);

  void deleteVaccinationAct(Long id);

  List<DTVaccinationAct> listVaccinationAct();

  List<DTVaccinationActView> vaccinationActByCi(String ci);

  List<DTVaccinationActView> getVaccinationActsView();
}
