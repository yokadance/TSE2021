package IDAL;

import DTO.DTVaccinationCenter;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationCenterData {
  Long newVaccinationCenter(DTVaccinationCenter dtVaccinationCenter);

  Long saveVaccinationCenter(DTVaccinationCenter dtVaccinationCenter);

  DTVaccinationCenter getVaccinationCenterById(Long id);

  void deleteVaccinationCenter(Long id);

  void unassignVaccinationCenter(Long centerId, Long planId);

  List<DTVaccinationCenter> listVaccinationsCenters();

  List<DTVaccinationCenter> getCentersByPlan(Long idVP);

  List<DTVaccinationCenter> getCentersAvailablesByPlan(Long idVP);

  String vaccinationCenterPassword();

  boolean validatePassword(Long vId, String password2check);
}
