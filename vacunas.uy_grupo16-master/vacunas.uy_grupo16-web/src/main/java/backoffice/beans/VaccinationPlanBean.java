package backoffice.beans;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import DTO.*;
import IController.IRestrictionController;
import IController.IVaccinationCenterController;
import IController.IVaccinationPlanController;
import IController.IVaccineController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Authority;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;

@Named("VaccinationPlanBean")
@ViewScoped
public class VaccinationPlanBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long id;

  @ManagedProperty("#{param.id}")
  private Long pid;

  private String name;

  private Date startDate;

  private Date endDate;

  private int vaccineQuantity;

  private String authority;

  private List<DTVaccine> vaccinesList;

  private DTVaccine selectedVaccine;

  private List<DTRestriction> restrictionList;

  private List<DTRestriction> selectedRestrictions;

  private DTVaccinationPlan dtVPlan;

  private DTVaccinationCenter dtvc;

  private List<DTVaccinationCenter> vacCenterList;

  private List<DTVaccinationCenter> vacCenterFullList;

  private List<DTVaccinationCenter> selectedCenters;

  private List<DTVaccinationPlan> listDtVaccinationPlans;

  private List<DTVaccinationPlan> selectedVaccinationPlans;

  private List<DTVaccinationPlan> filteredVaccinationPlans;

  Filter<DTVaccinationPlan> filter = new Filter<>(new DTVaccinationPlan());

  @EJB
  IVaccinationPlanController iVaccinationPlanController;

  @EJB
  IVaccinationCenterController iVaccinationCenterController;

  @EJB
  IVaccineController iVaccineController;

  @EJB
  IRestrictionController iRestrictionController;

  public VaccinationPlanBean() {}

  public void init() throws ParseException {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTVaccinationPlan d = iVaccinationPlanController.getVaccinationPlanById(id);

      Date startD = new SimpleDateFormat("yyyy-MM-dd").parse(d.getStartDate());
      Date finalD = new SimpleDateFormat("yyyy-MM-dd").parse(d.getEndDate());

      setName(d.getName());
      setStartDate(startD);
      setEndDate(finalD);
      setVaccineQuantity(d.getVaccineQuantity());
      setAuthority(d.getAuthority());
      setVacCenterList(iVaccinationCenterController.getCentersAvailablesByPlan(getId()));
      setSelectedVaccine(d.getVaccine());

    } else {
      clear();
      DTVaccinationPlan d = new DTVaccinationPlan();
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

  public Long getPid() {
    return pid;
  }

  public void setPid(Long id) {
    this.pid = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public int getVaccineQuantity() {
    return vaccineQuantity;
  }

  public void setVaccineQuantity(int vaccineQuantity) {
    this.vaccineQuantity = vaccineQuantity;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public List<DTVaccine> getVaccinesList() {
    return vaccinesList;
  }

  public void setVaccinesList(List<DTVaccine> vaccinesList) {
    this.vaccinesList = vaccinesList;
  }

  public DTVaccine getSelectedVaccine() {
    return selectedVaccine;
  }

  public void setSelectedVaccine(DTVaccine selectedVaccine) {
    this.selectedVaccine = selectedVaccine;
  }

  public List<DTRestriction> getRestrictionList() {
    return restrictionList;
  }

  public void setRestrictionList(List<DTRestriction> restrictionList) {
    this.restrictionList = restrictionList;
  }

  public List<DTRestriction> getSelectedRestrictions() {
    return selectedRestrictions;
  }

  public void setSelectedRestrictions(List<DTRestriction> selectedRestrictions) {
    this.selectedRestrictions = selectedRestrictions;
  }

  public DTVaccinationPlan getDtVPlan() {
    return dtVPlan;
  }

  public void setDtVPlan(DTVaccinationPlan dtVPlan) {
    this.dtVPlan = dtVPlan;
  }

  public List<DTVaccinationCenter> getVacCenterList() {
    return vacCenterList;
  }

  public void setVacCenterList(List<DTVaccinationCenter> vacCenterList) {
    this.vacCenterList = vacCenterList;
  }

  public List<DTVaccinationCenter> getVacCenterFullList() {
    return vacCenterFullList;
  }

  public void setVacCenterFullList(List<DTVaccinationCenter> vacCenterFullList) {
    this.vacCenterFullList = vacCenterFullList;
  }

  public DTVaccinationCenter getDtvc() {
    return dtvc;
  }

  public void setDtvc(DTVaccinationCenter dtvc) {
    this.dtvc = dtvc;
  }

  public List<DTVaccinationCenter> getSelectedCenters() {
    return selectedCenters;
  }

  public void setSelectedCenters(List<DTVaccinationCenter> selectedCenters) {
    this.selectedCenters = selectedCenters;
  }

  public List<DTVaccinationPlan> getListDtVaccinationPlans() {
    return listDtVaccinationPlans;
  }

  public void setListDtVaccinationPlans(List<DTVaccinationPlan> listDtVaccinationPlans) {
    this.listDtVaccinationPlans = listDtVaccinationPlans;
  }

  public List<DTVaccinationPlan> getSelectedVaccinationPlans() {
    return selectedVaccinationPlans;
  }

  public void setSelectedVaccinationPlans(List<DTVaccinationPlan> selectedVaccinationPlans) {
    this.selectedVaccinationPlans = selectedVaccinationPlans;
  }

  public List<DTVaccinationPlan> getFilteredVaccinationPlans() {
    return filteredVaccinationPlans;
  }

  public void setFilteredVaccinationPlans(List<DTVaccinationPlan> filteredVaccinationPlans) {
    this.filteredVaccinationPlans = filteredVaccinationPlans;
  }

  public Filter<DTVaccinationPlan> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTVaccinationPlan> filter) {
    this.filter = filter;
  }

  public void save() throws IOException {
    String msg;
    if(startDate.before(endDate)){
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String startD = dateFormat.format(getStartDate());
      String finalD = dateFormat.format(getEndDate());
      DTVaccinationPlan d = new DTVaccinationPlan(getName(), startD, finalD, getVaccineQuantity(), getAuthority());
      d.setVaccine(selectedVaccine);

      List<DTVaccinationCenter> dtCenterList = iVaccinationCenterController.getCentersByPlan(getId());
      List<String> stringCenterList = new ArrayList<>();
      List<String> stringRestrictionList = new ArrayList<>();

      if (dtCenterList.size() > 0) {
        for (DTVaccinationCenter existingCenter : dtCenterList) {
          stringCenterList.add(existingCenter.getId().toString());
        }
      }

      if (selectedCenters.size() > 0) {
        for (DTVaccinationCenter dtc : selectedCenters) {
          stringCenterList.add(dtc.getId().toString());
        }
      }

      d.setVaccinationCenters(stringCenterList);


      if(selectedRestrictions != null){
        for(DTRestriction dtr : selectedRestrictions){
          stringRestrictionList.add(dtr.getId().toString());
        }
        d.setRestriction(stringRestrictionList);
      }



      if (getId() == null) {
        iVaccinationPlanController.saveVaccinationPlan(d);
        msg = "Plan de vacunación " + d.getName() + " creado";
      } else {
        d.setId(getId());
        iVaccinationPlanController.saveVaccinationPlan(d);
        msg = "Plan de vacunación " + d.getName() + " modificado";
      }
      addDetailMessage(msg);
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("vaccinationPlan.jsf");
    }else{
      msg = "La fecha de inicio debe ser previa a la fecha final";
      addDetailMessage(msg);
      Faces.getFlash().setKeepMessages(true);
    }
  }

  public boolean isNew() {
    Long i = getId();
    return dtVPlan == null || dtVPlan.getId() == null;
  }

  public void clear() {
    id = null;
    name = null;
    startDate = null;
    endDate = null;
    vaccineQuantity = 0;
    authority = null;
    selectedCenters = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iVaccinationPlanController.deleteVaccinationPlan(id);
      DTVaccinationPlan d = iVaccinationPlanController.getVaccinationPlanById(id);
      addDetailMessage("Plan de vacunación " + d.getName() + " eliminado");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("vaccinationPlan.jsf");
    }
  }

  private void catchData() {
    try {
      Long id = getId();
      listDtVaccinationPlans = iVaccinationPlanController.listVaccinationPlans();
      selectedCenters = iVaccinationPlanController.vaccinationCentersByVaccinationPlan(getId());
      vaccinesList = iVaccineController.listVaccines();
      vacCenterFullList = iVaccinationCenterController.listVaccinationCenters();
      restrictionList = iRestrictionController.getRestrictions();
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addVaccinationPlan(DTVaccinationPlan newVPlan) {
    try {
      iVaccinationPlanController.saveVaccinationPlan(newVPlan);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remVaccinationPlan() throws IOException {
    try {
      for (DTVaccinationPlan l : selectedVaccinationPlans) {
        iVaccinationPlanController.deleteVaccinationPlan(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedVaccinationPlans.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("vaccinationPlan.jsf");
    return "ok";
  }

  public String updVaccinationPlan(DTVaccinationPlan updVPlam) {
    try {
      iVaccinationPlanController.saveVaccinationPlan(updVPlam);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTVaccinationPlan> getVaccinationPlans() {
    return listDtVaccinationPlans;
  }

  public List<DTVaccinationCenter> getCentersToList() {
    return selectedCenters;
  }

  public DTVaccinationPlan getVaccinationPlanById(Long id) {
    try {
      return iVaccinationPlanController.getVaccinationPlanById(id);
    } catch (Exception e) {
      return new DTVaccinationPlan();
    }
  }
}
