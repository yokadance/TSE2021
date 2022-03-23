package backoffice.beans;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import DTO.DTSchedule;
import DTO.DTVaccinationPlan;
import IController.IVaccinationCenterController;
import IController.IVaccinationPlanController;
import IController.IScheduleController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;

@Named("ScheduleBean")
@SessionScoped
public class ScheduleBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long planId;
  private Long centerId;
  private Long id;

  private Date startDate;
  private Date endDate;
  private String startTimeDaily;
  private String endTimeDaily;
  private int fraction;
  private boolean saturday;
  private boolean sunday;

  private DTSchedule dtSch;

  private String nameTitle;

  private Boolean hideDiv;

  private List<DTSchedule> listDtSchedules;

  private List<DTSchedule> selectedSchedules;

  private List<DTSchedule> filteredSchedules;

  Filter<DTSchedule> filter = new Filter<>(new DTSchedule());

  @EJB
  IScheduleController iScheduleController;

  @EJB
  IVaccinationPlanController iVaccinationPlanController;

  @EJB
  IVaccinationCenterController iVaccinationCenterController;

  public ScheduleBean() {}

  public void init() throws ParseException {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTSchedule d = iScheduleController.getScheduleById(id);

      Date startD = new SimpleDateFormat("yyyy-MM-dd").parse(d.getStartDate());
      Date finalD = new SimpleDateFormat("yyyy-MM-dd").parse(d.getEndDate());

      setStartDate(startD);
      setEndDate(finalD);
      setStartTimeDaily(d.getStartTimeDaily());
      setEndTimeDaily(d.getEndTimeDaily());
      setFraction(d.getFraction());
      setSaturday(d.isSaturday());
      setSunday(d.isSunday());
      setHideDiv(false);
    } else {
      DTSchedule d = new DTSchedule();
      setPlanId(planId);
      setHideDiv(true);
      catchData();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getStartTimeDaily() {
    return startTimeDaily;
  }

  public void setStartTimeDaily(String startTimeDaily) {
    this.startTimeDaily = startTimeDaily;
  }

  public String getEndTimeDaily() {
    return endTimeDaily;
  }

  public void setEndTimeDaily(String endTimeDaily) {
    this.endTimeDaily = endTimeDaily;
  }

  public int getFraction() {
    return fraction;
  }

  public void setFraction(int fraction) {
    this.fraction = fraction;
  }

  public boolean getSaturday() {
    return saturday;
  }

  public void setSaturday(boolean saturday) {
    this.saturday = saturday;
  }

  public boolean getSunday() {
    return sunday;
  }

  public void setSunday(boolean sunday) {
    this.sunday = sunday;
  }

  public DTSchedule getDtBat() {
    return dtSch;
  }

  public void setDtBat(DTSchedule dtSch) {
    this.dtSch = dtSch;
  }

  public Boolean getHideDiv() {
    return hideDiv;
  }

  public void setHideDiv(Boolean hideDiv) {
    this.hideDiv = hideDiv;
  }

  public List<DTSchedule> getListDtSchedules() {
    return listDtSchedules;
  }

  public void setListDtSchedules(List<DTSchedule> listDtSchedules) {
    this.listDtSchedules = listDtSchedules;
  }

  public List<DTSchedule> getSelectedSchedules() {
    return selectedSchedules;
  }

  public void setSelectedSchedules(List<DTSchedule> selectedSchedules) {
    this.selectedSchedules = selectedSchedules;
  }

  public List<DTSchedule> getFilteredSchedules() {
    return filteredSchedules;
  }

  public void setFilteredSchedules(List<DTSchedule> filteredSchedules) {
    this.filteredSchedules = filteredSchedules;
  }

  public Filter<DTSchedule> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTSchedule> filter) {
    this.filter = filter;
  }

  public String getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(String nameTitle) {
    this.nameTitle = nameTitle;
  }

  public String nameTitle() {
    return iVaccinationPlanController.getVaccinationPlanById(planId).getName();
  }

  public String test() {
    Long pId = getPlanId();
    clear();
    return "schedule?faces-redirect=true?includeViewParams=true&planId=" + pId;
  }
  public String back() {
    Long pId = getPlanId();
    clear();
    return "vaccinationCenter?faces-redirect=true?includeViewParams=true&planId=" + pId;
  }

  public void save() throws IOException, ParseException {
    String msg;

    if(startDate.before(endDate)) {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      DTVaccinationPlan dtPlan = iVaccinationPlanController.getVaccinationPlanById(getPlanId());
      Date planStartDate = dateFormat.parse(dtPlan.getStartDate());
      Date planEndDate = dateFormat.parse(dtPlan.getEndDate());

//      if(planStartDate.before(startDate) && planEndDate.after(endDate)) {
      if(planStartDate.compareTo(startDate) <= 0 && planEndDate.compareTo(endDate) >= 0) {
        String startD = dateFormat.format(getStartDate());
        String finalD = dateFormat.format(getEndDate());

        DTSchedule d = new DTSchedule(startD, finalD, getStartTimeDaily(), getEndTimeDaily(), getFraction(), getSaturday(), getSunday());
        d.setVaccinationPlan(iVaccinationPlanController.getVaccinationPlanById(getPlanId()));
        d.setVaccinationCenter(iVaccinationCenterController.getVaccinationCenterById(getCenterId()));

        if (getId() == null) {
          iScheduleController.saveSchedule(d);
          msg = "Agenda creada";
        } else {
          d.setId(getId());
          iScheduleController.saveSchedule(d);
          msg = "Agenda actualizado";
        }
        clear();
        addDetailMessage(msg);
        Faces.getFlash().setKeepMessages(true);
        Faces.redirect("schedule.jsf?planId=" + planId);
      }else{
        msg = "La agenda debe estar entre: " + dtPlan.getStartDate() + " y " + dtPlan.getEndDate();
        addDetailMessage(msg);
        Faces.getFlash().setKeepMessages(true);
      }
    }else {
      msg = "La fecha inicial debe ser previa a la fecha final";
      addDetailMessage(msg);
      Faces.getFlash().setKeepMessages(true);
    }
  }

  public boolean isNew() {
    Long i = getId();
    return dtSch == null || dtSch.getId() == null;
  }

  public void clear() {
    id = null;
    startDate = null;
    endDate = null;
    startTimeDaily = null;
    endTimeDaily = null;
    fraction = 0;
    saturday = false;
    sunday = false;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iScheduleController.deleteSchedule(id);
      DTSchedule d = iScheduleController.getScheduleById(id);
      addDetailMessage("Agenda eliminada");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("schedule.jsf?planId=" + planId);
    }
  }

  private void catchData() {
    try {
      listDtSchedules = iScheduleController.SchedulesbyVCandVP(getPlanId(), getCenterId());
      setNameTitle(iVaccinationCenterController.getVaccinationCenterById(centerId).getName());
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addSchedule(DTSchedule newSch) {
    try {
      iScheduleController.saveSchedule(newSch);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remSchedules() throws IOException {
    try {
      for (DTSchedule l : selectedSchedules) {
        if(!iScheduleController.unnasignSchedule(l.getId())){
          selectedSchedules.clear();
          addDetailMessage("No se permite borrar agendas con reservas");
          Faces.getFlash().setKeepMessages(true);
          Faces.redirect("schedule.jsf?planId=" + planId);
        }
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedSchedules.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("schedule.jsf?planId=" + planId);
    return "ok";
  }

  public String updSchedule(DTSchedule updSch) {
    try {
      iScheduleController.saveSchedule(updSch);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTSchedule> getSchedules() {
    return listDtSchedules;
  }

  public DTSchedule getSchedleById(Long id) {
    try {
      return iScheduleController.getScheduleById(id);
    } catch (Exception e) {
      return new DTSchedule();
    }
  }
}
