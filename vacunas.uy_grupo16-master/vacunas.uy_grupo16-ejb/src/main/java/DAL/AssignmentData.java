package DAL;

import DTO.DTAssignment;
import DTO.DTVaccinatorView;
import DTO.DTVaccinatorsVcenter;
import IController.IScheduleController;
import IController.IVaccinationPostController;
import IController.IVaccinatorController;
import IDAL.IAssignmentData;
import entities.*;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class AssignmentData implements IAssignmentData {

  @Inject
  IScheduleController iScheduleController;

  @Inject
  IVaccinatorController iVaccinatorController;

  @Inject
  IVaccinationPostController iVaccinationPostController;

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public void saveAssignment(DTAssignment dtAssignment) {
    Assignment assignment = modelMapper.map(dtAssignment, Assignment.class);
    try {
      Schedule schedule = data.find(Schedule.class, assignment.getPkId().getId_schedule());
      Vaccinator vaccinator = data.find(Vaccinator.class, assignment.getPkId().getId_vaccinator());
      VaccinationPost vp = data.find(VaccinationPost.class, assignment.getPkId().getId_vpost());
      assignment.setVaccinationPost(vp);
      assignment.setVaccinator(vaccinator);
      assignment.setSchedule(schedule);
      data.merge(assignment);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Override
  public void deleteAssignment(Long id) {
    try {
      Assignment ass = data.find(Assignment.class, id);
      ass.setDeleteDate(new Date());
      data.merge(ass);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Override
  public void unassignAssignment(Long id) {
    try {
      Assignment ass = data.find(Assignment.class, id);
      ass.setUpdateDate(new Date());
      ass.setPkId(null);
//      ass.setVaccinationPost(null);
//      ass.setSchedule(null);
//      ass.setVaccinator(null);
      data.merge(ass);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Override
  public List<DTAssignment> getAssignments() {
    List<Assignment> assignmentList = data.createQuery("select a from Assignment a where a.deleteDate is null").getResultList();
    List<DTAssignment> dtAssignments = new ArrayList<DTAssignment>();
    assignmentList.forEach(
      assignment -> {
        DTAssignment dtAss = new DTAssignment(
          assignment.getStartDate(),
          assignment.getEndDate(),
          assignment.getStartTime(),
          assignment.getEndTime()
        );
        dtAss.setId(assignment.getId());
        dtAssignments.add(dtAss);
      }
    );
    return dtAssignments;
  }

  @Override
  public DTAssignment getAssignmentById(Long id) {
    try {
      Assignment assignment = data.find(Assignment.class, id);
      DTAssignment dtAss = new DTAssignment(
        assignment.getStartDate(),
        assignment.getEndDate(),
        assignment.getStartTime(),
        assignment.getEndTime()
      );
      dtAss.setId(assignment.getId());
      dtAss.setSchedule(assignment.getSchedule().getDTSchedule());
      dtAss.setVaccinator(assignment.getVaccinator().getDTVaccinator());
      dtAss.setVaccinationPost(assignment.getVaccinationPost().getDTVaccinationPost());
      return dtAss;
    } catch (NoResultException nre) {
      return null;
    }
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public List<DTAssignment> getAssignmentsByPost(Long postId) {
    List<Assignment> assignmentList = data.createQuery("select a from Assignment a where a.deleteDate is null").getResultList();
    List<DTAssignment> dtAssignments = new ArrayList<DTAssignment>();

    for (Assignment assignment : assignmentList) {
      if(assignment.getPkId() != null ) {
       // if (assignment.getVaccinationPost().getId() == postId) {
        if (assignment.getVaccinationPost().getId().equals(postId)) {

          DTAssignment dtAss = new DTAssignment(
                  assignment.getStartDate(),
                  assignment.getEndDate(),
                  assignment.getStartTime(),
                  assignment.getEndTime()
          );
          dtAss.setId(assignment.getId());
          if(assignment.getVaccinator() != null)
          dtAss.setVaccinator(assignment.getVaccinator().getDTVaccinator());
          dtAssignments.add(dtAss);
        }
      }
    }

    return dtAssignments;
  }

  @Override
  public List<DTAssignment> getAssignmentsByPostAndPlan(Long postId, Long planId) {
    List<Assignment> assignmentList = data.createQuery("select a from Assignment a where a.deleteDate is null").getResultList();
    List<Schedule> scheduleList = data.find(VaccinationPlan.class, planId).getSchedule();
    List<DTAssignment> dtAssignments = new ArrayList<DTAssignment>();


    for (Assignment assignment : assignmentList) {
      if(assignment.getPkId() != null ) {
        //if (assignment.getVaccinationPost().getId() == postId) {
        if (assignment.getVaccinationPost().getId().equals(postId)) {
          for(Schedule sch : scheduleList){
            //if(sch.getId() == assignment.getSchedule().getId()){
            if(sch.getId().equals(assignment.getSchedule().getId())){
              DTAssignment dtAss = new DTAssignment(
                      assignment.getStartDate(),
                      assignment.getEndDate(),
                      assignment.getStartTime(),
                      assignment.getEndTime()
              );
              dtAss.setId(assignment.getId());
              if(assignment.getVaccinator() != null)
                dtAss.setVaccinator(assignment.getVaccinator().getDTVaccinator());
              dtAssignments.add(dtAss);
            }
          }

        }
      }
    }
    return dtAssignments;
  }

    @Override
    public boolean checkVaccinatorAvailability(String ci, String dateStart, String dateEnd){
      boolean res = true;
      if(getVaccinatorByDate(ci, dateStart, dateEnd).size() > 0)
          res = false;
      return res;
    }

  @Override
  public List<DTVaccinatorView> getVaccinatorByDate(String ci, String dateStart, String dateEnd) {
    try {
      List<Assignment> assignmentList = new ArrayList<>();
      assignmentList =
        data
          .createQuery(
            "select v from Assignment v where v.vaccinator.person.ci= :ci and ((:dateEnd between v.startDate and v.endDate) or (:dateStart between v.startDate and v.endDate)) ",
            Assignment.class
          )
          .setParameter("ci", ci)
          .setParameter("dateStart", dateStart)
          .setParameter("dateEnd", dateEnd)
          .getResultList();
      List<DTVaccinatorView> dtVaccinatorViews = new ArrayList<>();
      assignmentList.forEach(
        assignment -> {
          DTVaccinatorView dtVaccinatorView = new DTVaccinatorView();

//          dtVaccinatorView.setScheduleId(assignment.getSchedule().getId().toString());
//          dtVaccinatorView.setVaccinationPlanId(assignment.getSchedule().getVaccinationPlan().getId().toString());
//          dtVaccinatorView.setVaccinationPlanName(assignment.getSchedule().getVaccinationPlan().getName());
//          dtVaccinatorView.setVaccineName(assignment.getSchedule().getVaccinationPlan().getVaccine().getName());
//          dtVaccinatorView.setVaccinationCenterId(assignment.getVaccinationPost().getVaccinationCenter().getId().toString());
//          dtVaccinatorView.setVaccinationCenterName(assignment.getVaccinationPost().getVaccinationCenter().getName());
//          dtVaccinatorView.setVaccinationPostId(assignment.getVaccinationPost().getId().toString());
//          dtVaccinatorView.setVaccinationPostName(assignment.getVaccinationPost().getName());
          dtVaccinatorView.setDateStart(assignment.getStartDate());
          dtVaccinatorView.setDateEnd(assignment.getEndDate());
          dtVaccinatorView.setTimeEnd(assignment.getEndTime());
          dtVaccinatorView.setTimeStart(assignment.getStartTime());
          dtVaccinatorViews.add(dtVaccinatorView);
        }
      );
      return dtVaccinatorViews;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

public List<DTVaccinatorsVcenter> getVaccinatorsOnPosts(Long vCenterId){
  try {
    List<Assignment> assignmentList = new ArrayList<>();
    assignmentList =
            data
                    .createQuery(
                            "select a from Assignment a where a.schedule.vaccinationCenter.id=:vCenterId",
                            Assignment.class
                    )
                    .setParameter("vCenterId", vCenterId)
                    .getResultList();

    List<DTVaccinatorsVcenter> dtVaccinatorsVcenters = new ArrayList<>();
    assignmentList.forEach(
            assignment -> {
              DTVaccinatorsVcenter dtVaccinatorVcenter = new DTVaccinatorsVcenter();
              dtVaccinatorVcenter.setDate(assignment.getStartDate());
              dtVaccinatorVcenter.setDocumentNumber(assignment.getVaccinator().getPerson().getCi());
              dtVaccinatorVcenter.setName(assignment.getVaccinator().getPerson().getName() + " " + assignment.getVaccinator().getPerson().getLastname());
              dtVaccinatorVcenter.setVaccinationPostId(assignment.getVaccinationPost().getId().toString());
              dtVaccinatorVcenter.setId(assignment.getId());
              dtVaccinatorVcenter.setvPostName(assignment.getVaccinationPost().getName());
              dtVaccinatorsVcenters.add(dtVaccinatorVcenter);
            });
            return dtVaccinatorsVcenters;
  } catch (Exception e) {
    System.out.println(e);
    return null;
  }
  }
}



