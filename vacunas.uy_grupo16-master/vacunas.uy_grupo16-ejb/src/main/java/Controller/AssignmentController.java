package Controller;

import DTO.DTAssignment;
import DTO.DTVaccinatorView;
import DTO.DTVaccinatorsVcenter;
import IController.IAssignmentController;
import IDAL.IAssignmentData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class AssignmentController implements IAssignmentController {

  @EJB
  IAssignmentData iAssignmentData;

  @Override
  public void saveAssignment(DTAssignment dtAssignment) {
    iAssignmentData.saveAssignment(dtAssignment);
  }

  @Override
  public void deleteAssignment(Long id) {
    iAssignmentData.deleteAssignment(id);
  }

  @Override
  public List<DTAssignment> getAssignmentsByPostAndPlan(Long postId, Long planId){ return iAssignmentData.getAssignmentsByPostAndPlan(postId, planId); }

  @Override
  public void unassignAssignment(Long id){ iAssignmentData.unassignAssignment(id);}

  @Override
  public List<DTAssignment> getAssignments() {
    return iAssignmentData.getAssignments();
  }

  @Override
  public DTAssignment getAssignmentById(Long id) {
    return iAssignmentData.getAssignmentById(id);
  }

  public void setiAssignmentData(IAssignmentData iAssignmentData) {
    this.iAssignmentData = iAssignmentData;
  }

  public List<DTAssignment> getAssignmentsByPost(Long postId) {
    return iAssignmentData.getAssignmentsByPost(postId);
  }

  public boolean checkVaccinatorAvailability(String ci, String dateStart, String dateEnd){ return iAssignmentData.checkVaccinatorAvailability(ci, dateStart, dateEnd); }

  @Override
  public List<DTVaccinatorView> getVaccinatorByDate(String ci, String dateStart, String dateEnd) {
    return iAssignmentData.getVaccinatorByDate(ci, dateStart, dateEnd);
  }

  @Override
  public List<DTVaccinatorsVcenter> getVaccinatorsOnPosts(Long vCenterId){
    return iAssignmentData.getVaccinatorsOnPosts(vCenterId);
  }
}
