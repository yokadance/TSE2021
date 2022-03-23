package backoffice.beans;

import DTO.DTSchedule;
import DTO.DTVaccinationCenter;
import IController.IVaccinationCenterController;
import IController.IVaccinationPlanController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omnifaces.util.Faces;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

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

@Named("CenterBean")
@SessionScoped
public class CenterBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

//  private Long planId;

  private Long id;

  private String name;
  private String location;
  private String longitude;
  private String latitude;
  private String password;

  private DTSchedule dtVacC;

  private String nameTitle;

  private Boolean hideDiv;

  private List<DTVaccinationCenter> listDTVaccinationCenters;

  private List<DTVaccinationCenter> selectedVaccinationCenters;

  private List<DTVaccinationCenter> filteredVaccinationCenters;

  Filter<DTVaccinationCenter> filter = new Filter<>(new DTVaccinationCenter());

  private MapModel emptyModel;
  private String title;
  private double lat;
  private double lng;

  @EJB
  IVaccinationCenterController iVaccinationCenterController;

  @EJB
  IVaccinationPlanController iVaccinationPlanController;

  public CenterBean() {}

  public void init() throws ParseException {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTVaccinationCenter d = iVaccinationCenterController.getVaccinationCenterById(id);
      setName(d.getName());
      setLocation(d.getLocation());
      setLongitude(d.getLongitude());
      setLatitude(d.getLatitude());
      setPassword(d.getPassword());
      setHideDiv(false);
    } else {
      emptyModel = new DefaultMapModel();
      clear();
      DTVaccinationCenter d = new DTVaccinationCenter();
//      setPlanId(planId);
      setHideDiv(true);
      catchData();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }

//  public Long getPlanId() {
//    return planId;
//  }
//
//  public void setPlanId(Long planId) {
//    this.planId = planId;
//  }

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

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public DTSchedule getDtVacC() {
    return dtVacC;
  }

  public void setDtVacC(DTSchedule dtVacC) {
    this.dtVacC = dtVacC;
  }

  public String getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(String nameTitle) {
    this.nameTitle = nameTitle;
  }

  public Boolean getHideDiv() {
    return hideDiv;
  }

  public void setHideDiv(Boolean hideDiv) {
    this.hideDiv = hideDiv;
  }

  public List<DTVaccinationCenter> getListDTVaccinationCenters() {
    return listDTVaccinationCenters;
  }

  public void setListDTVaccinationCenters(List<DTVaccinationCenter> listDTVaccinationCenters) {
    this.listDTVaccinationCenters = listDTVaccinationCenters;
  }

  public List<DTVaccinationCenter> getSelectedVaccinationCenters() {
    return selectedVaccinationCenters;
  }

  public void setSelectedVaccinationCenters(List<DTVaccinationCenter> selectedVaccinationCenters) {
    this.selectedVaccinationCenters = selectedVaccinationCenters;
  }

  public List<DTVaccinationCenter> getFilteredVaccinationCenters() {
    return filteredVaccinationCenters;
  }

  public void setFilteredVaccinationCenters(List<DTVaccinationCenter> filteredVaccinationCenters) {
    this.filteredVaccinationCenters = filteredVaccinationCenters;
  }

  public Filter<DTVaccinationCenter> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTVaccinationCenter> filter) {
    this.filter = filter;
  }

  public MapModel getEmptyModel() {
    return emptyModel;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public void addMarker() {
    Marker marker = new Marker(new LatLng(lat, lng), title);
    emptyModel.addOverlay(marker);}


    public String test() {
//    Long pId = getPlanId();
    clear();
    return "menuCenter?faces-redirect=true";
  }

  public void save() throws IOException, ParseException {
    String msg;
    Long newVCenterId = null;
    String newLongitude = String.valueOf(getLng());
    String newLatitude = String.valueOf(getLat());
    String pass = iVaccinationCenterController.vaccinationCenterPassword();
    DTVaccinationCenter d = new DTVaccinationCenter(getName(), getLocation(), newLongitude, newLatitude, pass);

//    List<String> planList = new ArrayList<>();
//    planList.add(planId.toString());
//    d.setVaccinationPlans(planList);


    if (getId() == null) {
      newVCenterId = iVaccinationCenterController.newVaccinationCenter(d);
//      iVaccinationPlanController.addCenterToPlan(planId, newVCenterId);
      msg = "Centro creado";
    } else {
      d.setId(getId());
      iVaccinationCenterController.saveVaccinationCenter(d);

      msg = "Centro actualizado";
    }
    clear();
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("menuCenter.jsf");
  }

  public boolean isNew() {
    Long i = getId();
    return dtVacC == null || dtVacC.getId() == null;
  }

  public void clear() {
    id = null;
    name = null;
    location = null;
    longitude = null;
    latitude = null;
    password = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iVaccinationCenterController.deleteVaccinationCenter(id);
      DTVaccinationCenter d = iVaccinationCenterController.getVaccinationCenterById(id);
      addDetailMessage("Centro eliminado");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("menuCenter.jsf");
    }
  }

  private void catchData() {
    try {
//      Long i = getPlanId();
      listDTVaccinationCenters = iVaccinationCenterController.listVaccinationCenters();
//      setNameTitle(iVaccinationPlanController.getVaccinationPlanById(planId).getName());
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addCenter(DTVaccinationCenter newVC) {
    try {
      iVaccinationCenterController.saveVaccinationCenter(newVC);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remCenters() throws IOException {
    try {
      for (DTVaccinationCenter l : selectedVaccinationCenters) {
        iVaccinationCenterController.deleteVaccinationCenter(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedVaccinationCenters.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("menuCenter.jsf");
    return "ok";
  }

  public String updCenter(DTVaccinationCenter updVC) {
    try {
      iVaccinationCenterController.saveVaccinationCenter(updVC);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTVaccinationCenter> getVaccinationCenters() {
    return listDTVaccinationCenters;
  }

  public DTVaccinationCenter getVaccinationCentersById(Long id) {
    try {
      return iVaccinationCenterController.getVaccinationCenterById(id);
    } catch (Exception e) {
      return new DTVaccinationCenter();
    }
  }
}
