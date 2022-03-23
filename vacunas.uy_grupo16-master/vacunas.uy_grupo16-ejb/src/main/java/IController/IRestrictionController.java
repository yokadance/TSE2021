package IController;

import DTO.DTRestriction;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IRestrictionController {
  Long saveRestriction(DTRestriction dtRestriction);
  DTRestriction getByIdRestriction(Long id);
  List<DTRestriction> getRestrictions();
  void deleteRestriction(Long id);
  List<DTRestriction> getRestrictionsByPlan(Long idVP);
  List<DTRestriction> getRestrictionByData(Long idDS);
  String callAgeRestrictionApi(int ci, Long vaccinationPlanId);
  List<DTRestriction> getRestrictionsByVaccinationPlan(Long vaccinationPlanId);
}
