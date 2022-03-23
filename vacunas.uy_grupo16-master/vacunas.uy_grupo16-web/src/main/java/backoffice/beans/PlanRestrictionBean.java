package backoffice.beans;

import DTO.DTRestriction;
import DTO.DTVaccine;
import IController.IDataSourceController;
import IController.IRestrictionController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import enumerations.LogicOp;
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

@Named("PlanRestrictionBean")
@SessionScoped
public class PlanRestrictionBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long planId;

  private Long id;

  private String elementName;
  private String logicOperator;
  private int value;
  private List<DTVaccine> vaccines;
  private String dataSource;

  private DTRestriction dtRest;

  private String nameTitle;

  private Boolean hideDiv;


  private List<DTRestriction> listDTRestrictions;

  private List<DTRestriction> selectedRestrictions;

  private List<DTRestriction> filteredRestrictions;

  Filter<DTRestriction> filter = new Filter<>(new DTRestriction());



  @EJB
  IRestrictionController iRestrictionController;

  @EJB
  IDataSourceController iDataSourceController;

  public PlanRestrictionBean() {}

  public void init() throws ParseException {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTRestriction d = iRestrictionController.getByIdRestriction(id);
      setElementName(d.getElementName());

      if (d.getLogicOperator() == LogicOp.greaterThan) setLogicOperator("0");
      if (d.getLogicOperator() == LogicOp.lesserThan) setLogicOperator("1");
      if (d.getLogicOperator() == LogicOp.equalTo) setLogicOperator("2");
      if (d.getLogicOperator() == LogicOp.getGreaterThanOrEqual) setLogicOperator("3");
      if (d.getLogicOperator() == LogicOp.lesserThanOrEqual) setLogicOperator("4");
      if (d.getLogicOperator() == LogicOp.notEqualTo) setLogicOperator("5");

      setValue(d.getValue());
      setHideDiv(false);
    } else {
      DTRestriction d = new DTRestriction();
      setPlanId(planId);
      setHideDiv(true);
      catchData();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
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

  public String getElementName() {
    return elementName;
  }

  public void setElementName(String elementName) {
    this.elementName = elementName;
  }

  public String getLogicOperator() {
    return logicOperator;
  }

  public void setLogicOperator(String logicOperator) {
    this.logicOperator = logicOperator;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public List<DTVaccine> getVaccines() {
    return vaccines;
  }

  public void setVaccines(List<DTVaccine> vaccines) {
    this.vaccines = vaccines;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  public DTRestriction getDtRest() {
    return dtRest;
  }

  public void setDtRest(DTRestriction dtRest) {
    this.dtRest = dtRest;
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



  public List<DTRestriction> getListDTRestrictions() {
    return listDTRestrictions;
  }

  public void setListDTRestrictions(List<DTRestriction> listDTRestrictions) {
    this.listDTRestrictions = listDTRestrictions;
  }

  public List<DTRestriction> getSelectedRestrictions() {
    return selectedRestrictions;
  }

  public void setSelectedRestrictions(List<DTRestriction> selectedRestrictions) {
    this.selectedRestrictions = selectedRestrictions;
  }

  public List<DTRestriction> getFilteredRestrictions() {
    return filteredRestrictions;
  }

  public void setFilteredRestrictions(List<DTRestriction> filteredRestrictions) {
    this.filteredRestrictions = filteredRestrictions;
  }

  public Filter<DTRestriction> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTRestriction> filter) {
    this.filter = filter;
  }

  public String test() {
    Long pId = getPlanId();
    clear();
    return "planRestriction?faces-redirect=true?includeViewParams=true&planId=" + pId;
  }

  public void save() throws IOException, ParseException {


  }

  public boolean isNew() {
    Long i = getId();
    return dtRest == null || dtRest.getId() == null;
  }

  public void clear() {
    id = null;
    elementName = null;
    logicOperator = null;
    dataSource = null;
    value = 0;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iRestrictionController.deleteRestriction(id);
      DTRestriction d = iRestrictionController.getByIdRestriction(id);
      addDetailMessage("Restricción eliminada");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("planRestriction.jsf?planId=" + planId);
    }
  }

  private void catchData() {
    try {
      listDTRestrictions = iRestrictionController.getRestrictionsByPlan(planId);
      setNameTitle(iDataSourceController.getDataSourceById(planId).getName());
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addRestriction(DTRestriction newR) {
    try {
      iRestrictionController.saveRestriction(newR);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remRestriction() throws IOException {
    try {
      for (DTRestriction l : selectedRestrictions) {
        iRestrictionController.deleteRestriction(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedRestrictions.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("planRestriction.jsf?planId=" + planId);
    return "ok";
  }

  public String updRestriction(DTRestriction updR) {
    try {
      iRestrictionController.saveRestriction(updR);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTRestriction> getRestrictions() {
    return listDTRestrictions;
  }


}
