package backoffice.beans;

import DTO.DTPackage;
import DTO.DTVaccinationCenter;
import IController.IBatchController;
import IController.IPackageController;
import IController.IVaccinationCenterController;
import IController.IVaccinationPlanController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Batch;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

@Named("PlanPackageBean")
@SessionScoped
public class PlanPackageBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long centerId;
  private Long planId;
  private Long id;
  private Long packageNumber;
  private Long quantity;

  private String nameTitle;

  private DTPackage dtPack;

  private List<DTPackage> packagesList;

  private List<DTPackage> selectedPackagesList;

  private List<DTPackage> listDtPackages;

  private List<DTPackage> selectedPackages;

  private List<DTPackage> filteredPackages;



  Filter<DTPackage> filter = new Filter<>(new DTPackage());

  @EJB
  IPackageController iPackageController;

  @EJB
  IVaccinationCenterController iVaccinationCenterController;

  @EJB
  IVaccinationPlanController iVaccinationPlanController;

  public PlanPackageBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTPackage d = iPackageController.getByIdPackage(id);
      setPackageNumber(d.getPackageNumber());
      setQuantity(d.getQuantity());
    } else {
      setCenterId(centerId);
      catchData();
      DTPackage d = new DTPackage();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }


  public Filter<DTPackage> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTPackage> filter) {
    this.filter = filter;
  }

  public Long getCenterId() {
    return centerId;
  }

  public void setCenterId(Long centerId) {
    this.centerId = centerId;
  }

  public Long getPlanId() {
    return planId;
  }

  public void setPlanId(Long planId) {
    this.planId = planId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPackageNumber() {
    return packageNumber;
  }

  public void setPackageNumber(Long packageNumber) {
    this.packageNumber = packageNumber;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public String getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(String nameTitle) {
    this.nameTitle = nameTitle;
  }

  public String nameTitle(){
    return iVaccinationCenterController.getVaccinationCenterById(centerId).getName();
  }

  public DTPackage getDtPack() {
    return dtPack;
  }

  public void setDtPack(DTPackage dtPack) {
    this.dtPack = dtPack;
  }

  public List<DTPackage> getPackagesList() {
    return packagesList;
  }

  public void setPackagesList(List<DTPackage> packagesList) {
    this.packagesList = packagesList;
  }

  public List<DTPackage> getSelectedPackagesList() {
    return selectedPackagesList;
  }

  public void setSelectedPackagesList(List<DTPackage> selectedPackagesList) {
    this.selectedPackagesList = selectedPackagesList;
  }

  public List<DTPackage> getListDtPackages() {
    return listDtPackages;
  }

  public void setListDtPackages(List<DTPackage> listDtPackages) {
    this.listDtPackages = listDtPackages;
  }

  public List<DTPackage> getSelectedPackages() {
    return selectedPackages;
  }

  public void setSelectedPackages(List<DTPackage> selectedPackages) {
    this.selectedPackages = selectedPackages;
  }

  public List<DTPackage> getFilteredPackages() {
    return filteredPackages;
  }

  public void setFilteredPackages(List<DTPackage> filteredPackages) {
    this.filteredPackages = filteredPackages;
  }

  //  public void setListDtBatchs(List<DTBatch> listDtBatchs) {
//    this.listDtBatchs = listDtBatchs;
//  }

  public String test(){
    Long cId = getCenterId();
    clear();
    return "planPackage?faces-redirect=true?includeViewParams=true&centerId="+cId;
  }
  public String back() {
    Long pId = getPlanId();
    clear();
    return "vaccinationCenter?faces-redirect=true?includeViewParams=true&planId=" + pId;
  }


  public void save() throws IOException, ParseException {
    String msg;

    DTPackage d = new DTPackage();

    if(selectedPackagesList.size() > 0) {
      for(DTPackage dtP : selectedPackagesList){
        d = iPackageController.getByIdPackage(dtP.getId());
        d.setVaccinationPlan(getPlanId().toString());
        d.setVaccinationCenter(getCenterId().toString());
        iPackageController.savePackage(d);

      }
    }
    msg = selectedPackagesList.size() + " Paquetes actualizados";
    clear();
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("planPackage.jsf?centerId="+centerId);
  }

  public boolean isNew() {
    Long i = getId();
    return dtPack == null || dtPack.getId() == null;
  }

  public void clear() {
    id = null;
    packageNumber = null;
    quantity = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iPackageController.unassignPackageFromCenterAndPlan(id);
      DTPackage d = iPackageController.getByIdPackage(id);
      addDetailMessage("Paquete " + d.getPackageNumber() + " eliminado");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("planPackage.jsf?centerId="+centerId);
    }
  }



  private void catchData() {
    try {
      Long i = getCenterId();
      setNameTitle(iVaccinationCenterController.getVaccinationCenterById(i).getName());
      packagesList = iPackageController.getPackagesAvailableForPlan(planId);

    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addPackage(DTPackage newPack) {
    try {
      iPackageController.savePackage(newPack);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remPackages() throws IOException {
    try {
      for (DTPackage l : selectedPackages) {
        iPackageController.unassignPackageFromCenterAndPlan(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedPackages.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("planPackage.jsf?centerId="+centerId);
    return "ok";
  }

  public String updPackage(DTPackage updPack) {
    try {
      iPackageController.savePackage(updPack);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTPackage> getPackages() {
    setListDtPackages(iPackageController.getPackagesByPlanAndCenter(planId, centerId));
    return listDtPackages;
  }



  public DTPackage getPackageById(Long id) {
    try {
      return iPackageController.getByIdPackage(id);
    } catch (Exception e) {
      return new DTPackage();
    }
  }


}
