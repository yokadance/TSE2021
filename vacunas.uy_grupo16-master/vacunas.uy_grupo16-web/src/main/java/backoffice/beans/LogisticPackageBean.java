package backoffice.beans;

import DTO.DTBatch;
import DTO.DTLogisticPartner;
import DTO.DTPackage;
import IController.IBatchController;
import IController.ILogisticPartnerController;
import IController.IPackageController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import enumerations.PackageState;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

@Named("LogisticPackageBean")
@SessionScoped
public class LogisticPackageBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long partnerId;
  private Long id;
  private Long packageNumber;
  private Long quantity;
  private PackageState state;

  private String nameTitle;

  private DTPackage dtPack;

  private List<DTPackage> listDtAvailablePackages;

  private List<DTPackage> selectedAvailablePackages;

  private List<DTPackage> listDtPackages;

  private List<DTPackage> selectedPackages;

  private List<DTPackage> filteredPackages;



  Filter<DTPackage> filter = new Filter<>(new DTPackage());

  @EJB
  IPackageController iPackageController;

  @EJB
  ILogisticPartnerController iLogisticPartnerController;

  public LogisticPackageBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTPackage d = iPackageController.getByIdPackage(id);
      setPackageNumber(d.getPackageNumber());
      setQuantity(d.getQuantity());
      setState(d.getPackageState());
    } else {
      setPartnerId(partnerId);
      catchData();
      setState(null);
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

  public Long getPartnerId() {
    return partnerId;
  }

  public void setPartnerId(Long partnerId) {
    this.partnerId = partnerId;
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

  public PackageState getState() {
    return state;
  }

  public void setState(PackageState state) {
    this.state = state;
  }

  public String getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(String nameTitle) {
    this.nameTitle = nameTitle;
  }

  public String nameTitle(){
    return iLogisticPartnerController.getLogisticPartnerById(partnerId).getName();
  }

  public DTPackage getDtPack() {
    return dtPack;
  }

  public void setDtPack(DTPackage dtPack) {
    this.dtPack = dtPack;
  }

  public List<DTPackage> getListDtAvailablePackages() {
    return listDtAvailablePackages;
  }

  public void setListDtAvailablePackages(List<DTPackage> listDtAvailablePackages) {
    this.listDtAvailablePackages = listDtAvailablePackages;
  }

  public List<DTPackage> getSelectedAvailablePackages() {
    return selectedAvailablePackages;
  }

  public void setSelectedAvailablePackages(List<DTPackage> selectedAvailablePackages) {
    this.selectedAvailablePackages = selectedAvailablePackages;
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

  public String test(){
    Long pId = getPartnerId();
    clear();
    return "logisticPackage?faces-redirect=true?includeViewParams=true&partnerId="+pId;
  }

  public void save() throws IOException, ParseException {
    String msg;

    DTLogisticPartner dtPartner = iLogisticPartnerController.getLogisticPartnerById(partnerId);
    List<DTPackage> listToAdd = new ArrayList<>();
//    List<DTPackage> listToUpdate = new ArrayList<>();

    if(dtPartner.getPackages() != null){
      listToAdd = dtPartner.getPackages();
    }


    if(selectedAvailablePackages.size() > 0) {
      for(DTPackage dtp : selectedAvailablePackages) {
        listToAdd.add(dtp);
      }
    }
    dtPartner.setPackages(listToAdd);
    iLogisticPartnerController.saveLogisticPartner(dtPartner);
    msg = "Lista de paquetes del socio  " + dtPartner.getName() + " actualizada";

    clear();
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("logisticPackage.jsf?partnerId="+partnerId);
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
      iLogisticPartnerController.removePackageFromPartner(partnerId, id);

      DTPackage d = iPackageController.getByIdPackage(id);
      addDetailMessage("Paquete " + d.getPackageNumber() + " eliminado del socio");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("logisticPackage.jsf?partnerId=" + partnerId);
    }
  }



  private void catchData() {
    try {
      Long i = getPartnerId();
      listDtPackages = iLogisticPartnerController.getPackagesFromPartner(i);
      nameTitle = iLogisticPartnerController.getLogisticPartnerById(partnerId).getName();
      listDtAvailablePackages = iLogisticPartnerController.getAvailablePackagesToAdd();
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
            iLogisticPartnerController.removePackageFromPartner(partnerId, l.getId());
          }

      } catch (Exception e) {
        return e.getMessage();
      }

      selectedPackages.clear();

      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("logisticPackage.jsf?partnerId=" + partnerId);
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
