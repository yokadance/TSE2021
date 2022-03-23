package IController;

import DTO.DTVaccinationCenter;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationCenterController {
  Long newVaccinationCenter(DTVaccinationCenter dtVaccinationCenter);
  List<DTVaccinationCenter> listVaccinationCenters();
  Long saveVaccinationCenter(DTVaccinationCenter dtVaccinationCenter);
  void deleteVaccinationCenter(Long id);
  void unassignVaccinationCenter(Long centerId, Long planId);
  DTVaccinationCenter getVaccinationCenterById(Long id);
  List<DTVaccinationCenter> getCentersByPlan(Long idVP);
  List<DTVaccinationCenter> getCentersAvailablesByPlan(Long idVP);
  String vaccinationCenterPassword();
  boolean validatePassword(Long vId, String password2check);
}
