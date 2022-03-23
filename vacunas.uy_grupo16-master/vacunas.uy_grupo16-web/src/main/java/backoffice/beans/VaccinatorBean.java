package backoffice.beans;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import DTO.*;
import IController.*;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.AssignmentPK;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;

@Named("VaccinatorBean")
@SessionScoped
public class VaccinatorBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long postId;
  private Long planId;
  private Long centerId;
  private Long id;

  private Date startDate;
  private Date endDate;
  private String startTime;
  private String endTime;

  private String nameTitle;

  private DTAssignment dtAss;

  private List<DTVaccinator> listDtVaccinators;
  private List<DTPerson> listDtPersonV;

  private DTVaccinator selectedVaccinator;

  private List<DTAssignment> listDtAssignments;

  private List<DTAssignment> selectedAssignments;

  private List<DTAssignment> filteredAssignments;

  Filter<DTAssignment> filter = new Filter<>(new DTAssignment());

  @EJB
  IPackageController iPackageController;

  @EJB
  IVaccinatorController iVaccinatorController;

  @EJB
  IPersonController iPersonController;

  @EJB
  IVaccinationPlanController iVaccinationPlanController;

  @EJB
  IAssignmentController iAssignmentController;

  @EJB
  IVaccinationPostController iVaccinationPostController;

  @EJB
  IScheduleController iScheduleController;

  public VaccinatorBean() {}

  public void init() throws ParseException {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTAssignment d = iAssignmentController.getAssignmentById(id);
      Date startD = new SimpleDateFormat("yyyy-MM-dd").parse(d.getStartDate());
      Date finalD = new SimpleDateFormat("yyyy-MM-dd").parse(d.getEndDate());

      setStartDate(startD);
      setEndDate(finalD);

      setStartTime(d.getStartTime());
      setEndTime(d.getEndTime());
      setSelectedVaccinator(d.getVaccinator());
    } else {
      catchData();
      DTAssignment d = new DTAssignment();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Long getPlanId() {
    return planId;
  }

  public void setPlanId(Long planId) {
    this.planId = planId;
  }

  public Long getCenterId() {
    return centerId;
  }

  public void setCenterId(Long centerId) {
    this.centerId = centerId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(String nameTitle) {
    this.nameTitle = nameTitle;
  }

  public DTAssignment getDtAss() {
    return dtAss;
  }

  public void setDtAss(DTAssignment dtAss) {
    this.dtAss = dtAss;
  }

  public List<DTVaccinator> getListDtVaccinators() {
    return listDtVaccinators;
  }

  public void setListDtVaccinators(List<DTVaccinator> listDtVaccinators) {
    this.listDtVaccinators = listDtVaccinators;
  }

  public List<DTPerson> getListDtPersonV() {
    return listDtPersonV;
  }

  public void setListDtPersonV(List<DTPerson> listDtPersonV) {
    this.listDtPersonV = listDtPersonV;
  }

  public DTVaccinator getSelectedVaccinator() {
    return selectedVaccinator;
  }

  public void setSelectedVaccinator(DTVaccinator selectedVaccinator) {
    this.selectedVaccinator = selectedVaccinator;
  }

  public List<DTAssignment> getListDtAssignments() {
    return listDtAssignments;
  }

  public void setListDtAssignments(List<DTAssignment> listDtAssignments) {
    this.listDtAssignments = listDtAssignments;
  }

  public List<DTAssignment> getSelectedAssignments() {
    return selectedAssignments;
  }

  public void setSelectedAssignments(List<DTAssignment> selectedAssignments) {
    this.selectedAssignments = selectedAssignments;
  }

  public List<DTAssignment> getFilteredAssignments() {
    return filteredAssignments;
  }

  public void setFilteredAssignments(List<DTAssignment> filteredAssignments) {
    this.filteredAssignments = filteredAssignments;
  }

  public Filter<DTAssignment> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTAssignment> filter) {
    this.filter = filter;
  }

  public String test() {
    Long pId = getPostId();
    clear();
    return "vaccinator?faces-redirect=true?includeViewParams=true&postId=" + pId;
  }
  public String back() {
    Long cId = getCenterId();
    Long pId = getPlanId();
    clear();
    return "vaccinationPost?faces-redirect=true?includeViewParams=true&centerId=" + cId + "&planId=" + pId;
  }

  public void save() throws IOException, ParseException {
    String msg;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String startD = dateFormat.format(getStartDate());
    String finalD = dateFormat.format(getEndDate());
    String vaccinatorCi = iPersonController.getPerson(getSelectedVaccinator().getDtPerson().getId()).getCi();

    if(startDate.before(endDate)){
      if(iAssignmentController.checkVaccinatorAvailability(vaccinatorCi, startD, finalD)) {

        DTVaccinationPlan dtPlan = iVaccinationPlanController.getVaccinationPlanById(getPlanId());
        Date planStartDate = dateFormat.parse(dtPlan.getStartDate());
        Date planEndDate = dateFormat.parse(dtPlan.getEndDate());

        if (planStartDate.compareTo(startDate) <= 0 && planEndDate.compareTo(endDate) >= 0) {

          DTAssignment d = new DTAssignment();
          d.setStartDate(startD);
          d.setEndDate(finalD);
          d.setStartTime(getStartTime());
          d.setEndTime(getEndTime());

          AssignmentPK assPk = new AssignmentPK();
          assPk.setId_vaccinator(getSelectedVaccinator().getId());
          assPk.setId_vpost(iVaccinationPostController.getByIdVaccinationPost(getPostId()).getId());

          List<DTSchedule> schedules = (iScheduleController.SchedulesbyVCandVP(getPlanId(), getCenterId()));
          for (DTSchedule sch : schedules) {
            assPk.setId_schedule(sch.getId());
          }
          d.setPkId(assPk);
          iAssignmentController.saveAssignment(d);
          msg = "Asignación creada";
          clear();
          addDetailMessage(msg);
          Faces.getFlash().setKeepMessages(true);
          Faces.redirect("vaccinator.jsf?postId=" + postId);
        }else{
          msg = "La asignación debe estar entre: " + dtPlan.getStartDate() + " y " + dtPlan.getEndDate();

        }
      }else {
        msg = "El vacunador no esta disponible en ese periodo";

      }
    }else {

      msg = "La fecha inicial debe ser previa a la fecha final";
    }
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
  }

  public boolean isNew() {
    Long i = getId();
    return dtAss == null || dtAss.getId() == null;
  }

  public void clear() {
    id = null;
    startDate = null;
    endDate = null;
    startTime = null;
    endTime = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iAssignmentController.unassignAssignment(id);
      DTAssignment d = iAssignmentController.getAssignmentById(id);
      addDetailMessage("Asignación de " + d.getVaccinator().getName() + " eliminada");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("vaccinator.jsf?postId=" + postId);
    }
  }

  private void catchData() {
    try {
      //      listDtPackages = iPackageController.getPackagesByPlanAndCenter(planId, centerId);
      listDtVaccinators = iVaccinatorController.getVaccinators();
      listDtPersonV = getPersonFromVaccinator(getListDtVaccinators());
      nameTitle = iVaccinationPostController.getByIdVaccinationPost(postId).getName();
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public List<DTPerson> getPersonFromVaccinator(List<DTVaccinator> listDtV){
    List<DTPerson> dtPersonList = new ArrayList<>();
    if(listDtV != null){
      for(DTVaccinator dtv : listDtV){
        dtPersonList.add(dtv.getDtPerson());
      }
    }
    return dtPersonList;
  }

  public String addAssignment(DTAssignment newAss) {
    try {
      iAssignmentController.saveAssignment(newAss);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remAssignment() throws IOException {
    try {
      for (DTAssignment l : selectedAssignments) {
        iAssignmentController.unassignAssignment(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedAssignments.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("vaccinator.jsf?postId=" + postId);
    return "ok";
  }

  public String updAssignment(DTAssignment updAss) {
    try {
      iAssignmentController.saveAssignment(updAss);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTVaccinator> getVaccinators() {
    setListDtVaccinators(iVaccinatorController.getVaccinators());
    return listDtVaccinators;
  }

  public List<DTAssignment> getAssignments() {
    //poner assignmetns del puesto ?
//    setListDtAssignments(iAssignmentController.getAssignments());
//    setListDtAssignments(iAssignmentController.getAssignmentsByPost(getPostId()));
    setListDtAssignments(iAssignmentController.getAssignmentsByPostAndPlan(getPostId(), getPlanId()));
    return listDtAssignments;
  }
}
