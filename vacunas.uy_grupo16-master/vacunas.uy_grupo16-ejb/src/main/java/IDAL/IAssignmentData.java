package IDAL;

import DTO.DTAssignment;
import DTO.DTVaccinatorView;
import DTO.DTVaccinatorsVcenter;

import java.util.List;

public interface IAssignmentData {
  void saveAssignment(DTAssignment dtAssignment);
  void deleteAssignment(Long id);
  void unassignAssignment(Long id);
  List<DTAssignment> getAssignments();
  boolean checkVaccinatorAvailability(String ci, String dateStart, String dateEnd);
  DTAssignment getAssignmentById(Long id);
  List<DTAssignment> getAssignmentsByPost(Long postId);
  List<DTVaccinatorView> getVaccinatorByDate(String ci, String dateStart, String dateEnd);
  public List<DTVaccinatorsVcenter> getVaccinatorsOnPosts(Long vCenterId);
  List<DTAssignment> getAssignmentsByPostAndPlan(Long postId, Long planId);
}
