package IDAL;

import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPlan;
import DTO.DTVaccinationPlanMonitor;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationPlanData {
  Long saveVaccinationPlan(DTVaccinationPlan dtVaccinationPlan, List<DTVaccinationCenter> vaccinationCenterList);

  DTVaccinationPlan getVaccinationPlanById(Long id);

  void deleteVaccinationPlan(Long id);

  List<DTVaccinationPlan> listVaccinationsPlans();

  void unassignCenterOfPlan(Long centerId, Long planId);

  List<DTVaccinationCenter> vaccinationCentersByVaccinationPlan(Long id);

  void addCenterToPlan(Long planId, Long centerId);

  void addRestrictionToPlan(Long planId, Long restrictionId);

  DTVaccinationPlanMonitor getDataMonVPlan(Long id);
}
