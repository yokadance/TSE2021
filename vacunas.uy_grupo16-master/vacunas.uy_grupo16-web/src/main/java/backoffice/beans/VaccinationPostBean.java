package backoffice.beans;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import DTO.DTVaccinationPost;
import DTO.DTVaccinationPostNew;
import IController.IVaccinationCenterController;
import IController.IVaccinationPostController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;

@Named("VaccinationPostBean")
@SessionScoped
public class VaccinationPostBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long centerId;
  private Long planId;
  private Long id;

  private String name;
  private String observation;
  private List<String> vaccinators;

  private String nameTitle;

  private DTVaccinationPost dtPost;

  private List<DTVaccinationPost> listDtPosts;

  private List<DTVaccinationPost> selectedPosts;

  private List<DTVaccinationPost> filteredPosts;

  Filter<DTVaccinationPost> filter = new Filter<>(new DTVaccinationPost());

  @EJB
  IVaccinationPostController iVaccinationPostController;

  @EJB
  IVaccinationCenterController iVaccinationCenterController;

  public VaccinationPostBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTVaccinationPost d = iVaccinationPostController.getByIdVaccinationPost(id);
      setName(d.getName());
      setObservation(d.getObservation());
      setVaccinators(d.getVaccinators());
    } else {
      setCenterId(centerId);
      catchData();
      DTVaccinationPost d = new DTVaccinationPost();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public List<String> getVaccinators() {
    return vaccinators;
  }

  public void setVaccinators(List<String> vaccinators) {
    this.vaccinators = vaccinators;
  }

  public String getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(String nameTitle) {
    this.nameTitle = nameTitle;
  }

  public DTVaccinationPost getDtPost() {
    return dtPost;
  }

  public void setDtPost(DTVaccinationPost dtPost) {
    this.dtPost = dtPost;
  }

  public List<DTVaccinationPost> getListDtPosts() {
    return listDtPosts;
  }

  public void setListDtPosts(List<DTVaccinationPost> listDtPosts) {
    this.listDtPosts = listDtPosts;
  }

  public List<DTVaccinationPost> getSelectedPosts() {
    return selectedPosts;
  }

  public void setSelectedPosts(List<DTVaccinationPost> selectedPosts) {
    this.selectedPosts = selectedPosts;
  }

  public List<DTVaccinationPost> getFilteredPosts() {
    return filteredPosts;
  }

  public void setFilteredPosts(List<DTVaccinationPost> filteredPosts) {
    this.filteredPosts = filteredPosts;
  }

  public Filter<DTVaccinationPost> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTVaccinationPost> filter) {
    this.filter = filter;
  }

  public String test() {
    Long cId = getCenterId();
    clear();
    return "vaccinationPost?faces-redirect=true?includeViewParams=true&centerId=" + cId;
  }
  public String back() {
    Long pId = getPlanId();
    clear();
    return "vaccinationCenter?faces-redirect=true?includeViewParams=true&planId=" + pId;
  }

  public void save() throws IOException, ParseException {
    String msg;

    DTVaccinationPostNew d = new DTVaccinationPostNew(getName(), getObservation(), centerId);
    //    d.setBatch(getBatchId().toString());
    if (getId() == null) {
      iVaccinationPostController.createVaccinationPost(d);
      msg = "Puesto " + d.getName() + " creado";
    } else {
      d.setId(getId());
      iVaccinationPostController.createVaccinationPost(d);
      msg = "Puesto " + d.getName() + "  actualizado";
    }
    clear();
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("vaccinationPost.jsf?centerId=" + centerId);
  }

  public boolean isNew() {
    Long i = getId();
    return dtPost == null || dtPost.getId() == null;
  }

  public void clear() {
    id = null;
    name = null;
    observation = null;
    vaccinators = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iVaccinationPostController.deleteVaccinationPost(id);
      DTVaccinationPost d = iVaccinationPostController.getByIdVaccinationPost(id);
      addDetailMessage("Puesto " + d.getName() + " eliminado");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("vaccinationPost.jsf?centerId=" + centerId);
    }
  }

  private void catchData() {
    try {
      Long i = getCenterId();
      listDtPosts = iVaccinationPostController.getByVaccinatorCenter(i);
      nameTitle = iVaccinationCenterController.getVaccinationCenterById(centerId).getName();
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addPost(DTVaccinationPost newPost) {
    try {
      iVaccinationPostController.saveVaccinationPost(newPost);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remPosts() throws IOException {
    try {
      for (DTVaccinationPost l : selectedPosts) {
        iVaccinationPostController.deleteVaccinationPost(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedPosts.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("vaccinationPost.jsf?centerId=" + centerId);
    return "ok";
  }

  public String updPost(DTVaccinationPost updPost) {
    try {
      iVaccinationPostController.saveVaccinationPost(updPost);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTVaccinationPost> getPosts() {
    return listDtPosts;
  }

  public DTVaccinationPost getPostById(Long id) {
    try {
      return iVaccinationPostController.getByIdVaccinationPost(id);
    } catch (Exception e) {
      return new DTVaccinationPost();
    }
  }
}
