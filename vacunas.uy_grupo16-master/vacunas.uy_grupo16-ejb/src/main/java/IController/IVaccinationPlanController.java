package IController;

import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPlan;
import DTO.DTVaccinationPlanMonitor;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationPlanController {
  void newVaccinationPlan(DTVaccinationPlan dtVaccinationPlan);
  List<DTVaccinationPlan> listVaccinationPlans();
  Long saveVaccinationPlan(DTVaccinationPlan dtVaccinationPlan);
  void deleteVaccinationPlan(Long id);
  DTVaccinationPlan getVaccinationPlanById(Long id);
  List<DTVaccinationCenter> vaccinationCentersByVaccinationPlan(Long id);
  void unassignCenterOfPlan(Long centerId, Long planId);
  void addCenterToPlan(Long planId, Long centerId);
  void addRestrictionToPlan(Long planId, Long restrictionId);

  DTVaccinationPlanMonitor getDataMonVPlan(Long id);
}
