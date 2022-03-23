package Controller;

import DTO.DTRestriction;
import IController.IRestrictionController;
import IDAL.IRestrictionData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class RestrictionController implements IRestrictionController {

  @EJB
  IRestrictionData iRestrictionData;

  @Override
  public Long saveRestriction(DTRestriction dtRestriction) {
    return iRestrictionData.saveRestriction(dtRestriction);
  }

  @Override
  public DTRestriction getByIdRestriction(Long id) {
    return iRestrictionData.getRestrictionById(id);
  }

  @Override
  public List<DTRestriction> getRestrictions() {
    return iRestrictionData.listRestrictions();
  }

  @Override
  public void deleteRestriction(Long id) {
    iRestrictionData.deleteRestriction(id);
  }

  @Override
  public List<DTRestriction> getRestrictionsByPlan(Long idVP) {
    return iRestrictionData.getRestrictionsByPlan(idVP);
  }

  @Override
  public List<DTRestriction> getRestrictionByData(Long idDS) {
    return iRestrictionData.getRestrictionByData(idDS);
  }

  @Override
  public List<DTRestriction> getRestrictionsByVaccinationPlan(Long vaccinationPlanId) {
    return iRestrictionData.getRestrictionsByVaccinationPlan(vaccinationPlanId);
  }

  public void setiRestrictionData(IRestrictionData iRestrictionData) {
    this.iRestrictionData = iRestrictionData;
  }

  @Override
  public String callAgeRestrictionApi(int ci, Long idVaccinationPlan) {
    return iRestrictionData.callAgeRestrictionApi(ci, idVaccinationPlan);
  }
}
