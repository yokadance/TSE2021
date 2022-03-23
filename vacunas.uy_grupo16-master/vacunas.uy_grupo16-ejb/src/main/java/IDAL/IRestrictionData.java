package IDAL;

import DTO.DTRestriction;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IRestrictionData {
  Long saveRestriction(DTRestriction dtRestriction);
  DTRestriction getRestrictionById(Long id);
  void deleteRestriction(Long id);
  List<DTRestriction> listRestrictions();
  List<DTRestriction> getRestrictionsByPlan(Long idVP);
  List<DTRestriction> getRestrictionByData(Long idDS);
  String callAgeRestrictionApi(int ci, Long vaccinationPlan);
  List<DTRestriction> getRestrictionsByVaccinationPlan(Long vaccinationPlanId);
}
