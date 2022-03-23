package backoffice.beans;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import DTO.DTLogisticPartner;
import IController.ILogisticPartnerController;
import IController.IVaccinationCenterController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;

@Named("LogisticPartnerBean")
@ViewScoped
public class LogisticPartnerBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long id;

  private String name;
  private String businessName;
  private String rut;
  private String phone;
  private String email;
  private String reference;

  private DTLogisticPartner dtLP;

  private List<DTLogisticPartner> listDtPartners;

  private List<DTLogisticPartner> selectedPartners;

  private List<DTLogisticPartner> filteredPartners;

  Filter<DTLogisticPartner> filter = new Filter<>(new DTLogisticPartner());

  @EJB
  ILogisticPartnerController iLogisticPartnerController;

  @EJB
  IVaccinationCenterController iVaccinationCenterController;

  public LogisticPartnerBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTLogisticPartner d = iLogisticPartnerController.getLogisticPartnerById(id);
      setId(d.getId());
      setName(d.getName());
      setBusinessName(d.getBusinessName());
      setRut(d.getRut());
      setPhone(d.getPhone());
      setEmail(d.getEmail());
      setReference(d.getReference());
    } else {
      DTLogisticPartner d = new DTLogisticPartner();
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getRut() {
    return rut;
  }

  public void setRut(String rut) {
    this.rut = rut;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public DTLogisticPartner getDtLP() {
    return dtLP;
  }

  public void setDtLP(DTLogisticPartner dtLP) {
    this.dtLP = dtLP;
  }

  public List<DTLogisticPartner> getListDtPartners() {
    return listDtPartners;
  }

  public void setListDtPartners(List<DTLogisticPartner> listDtPartners) {
    this.listDtPartners = listDtPartners;
  }

  public List<DTLogisticPartner> getSelectedPartners() {
    return selectedPartners;
  }

  public void setSelectedPartners(List<DTLogisticPartner> selectedPartners) {
    this.selectedPartners = selectedPartners;
  }

  public List<DTLogisticPartner> getFilteredPartners() {
    return filteredPartners;
  }

  public void setFilteredPartners(List<DTLogisticPartner> filteredPartners) {
    this.filteredPartners = filteredPartners;
  }

  public Filter<DTLogisticPartner> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTLogisticPartner> filter) {
    this.filter = filter;
  }

  public void save() throws IOException {
    String msg;

    DTLogisticPartner d = new DTLogisticPartner();
    d.setName(getName());
    d.setBusinessName(getBusinessName());
    d.setRut(getRut());
    d.setPhone(getPhone());
    d.setEmail(getEmail());
    d.setReference(getReference());
    String pass = iVaccinationCenterController.vaccinationCenterPassword();
    d.setPassword(pass);
    if (getId() == null) {
      iLogisticPartnerController.saveLogisticPartner(d);
      msg = "Socio " + d.getName() + " creado";
    } else {
      d.setId(getId());
      iLogisticPartnerController.saveLogisticPartner(d);
      msg = "Socio " + d.getName() + " actualizado";
    }
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("logisticPartner.jsf");
  }

  public boolean isNew() {
    Long i = getId();
    return dtLP == null || dtLP.getId() == null;
  }

  public void clear() {
    name = null;
    businessName = null;
    rut = null;
    phone = null;
    email = null;
    reference = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iLogisticPartnerController.deleteLogisticPartner(id);
      DTLogisticPartner d = iLogisticPartnerController.getLogisticPartnerById(id);
      addDetailMessage("Socio " + d.getName() + " eliminado");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("logisticPartner.jsf");
    }
  }

  private void catchData() {
    try {
      listDtPartners = iLogisticPartnerController.listLogisticPartners();
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addLogisticPartner(DTLogisticPartner newPartner) {
    try {
      iLogisticPartnerController.saveLogisticPartner(newPartner);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remLogisticPartner() throws IOException {
    try {
      for (DTLogisticPartner l : selectedPartners) {
        iLogisticPartnerController.deleteLogisticPartner(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedPartners.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("logisticPartner.jsf");
    return "ok";
  }

  public String updLogisticPartner(DTLogisticPartner updLogPart) {
    try {
      iLogisticPartnerController.saveLogisticPartner(updLogPart);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTLogisticPartner> getLogisticPartner() {
    return listDtPartners;
  }
}
