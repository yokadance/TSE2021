package backoffice.beans;

import DTO.DTBatch;
import IController.IBatchController;
import IController.IVaccineController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

@Named("BatchBean")
@SessionScoped
public class BatchBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long vaccineId;

  private Long id;

  private long number;

  private int quantity;

  private String creationDate;

  private int expirationDate;

  private int available;

  private DTBatch dtBat;

  private String nameTitle;

  private Boolean hideDiv;

  private List<DTBatch> listDtBatches;

  private List<DTBatch> selectedBatches;

  private List<DTBatch> filteredBatches;



  Filter<DTBatch> filter = new Filter<>(new DTBatch());

  @EJB
  IBatchController iBatchController;

  @EJB
  IVaccineController iVaccineController;

  public BatchBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTBatch d = iBatchController.getByIdBatch(id);
      setNumber(d.getNumber());
      setQuantity(d.getQuantity());
      setCreationDate(d.getCreationDate().toString());
//      setExpirationDate(d.getExpirationDate());
      setAvailable(d.getAvailable());
      setHideDiv(false);
    } else {
      DTBatch d = new DTBatch();
      setVaccineId(vaccineId);
      setHideDiv(true);
      catchData();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }


  public Filter<DTBatch> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTBatch> filter) {
    this.filter = filter;
  }

  public Long getVaccineId() {
    return vaccineId;
  }

  public void setVaccineId(Long vaccineId) {
    this.vaccineId = vaccineId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public int getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(int expirationDate) {
    this.expirationDate = expirationDate;
  }

  public int getAvailable() {
    return available;
  }

  public void setAvailable(int available) {
    this.available = available;
  }

  public Boolean getHideDiv() {
    return hideDiv;
  }

  public void setHideDiv(Boolean hideDiv) {
    this.hideDiv = hideDiv;
  }

  public DTBatch getDtBat() {
    return dtBat;
  }

  public void setDtBat(DTBatch dtBat) {
    this.dtBat = dtBat;
  }

  public List<DTBatch> getListDtBatches() {
    return listDtBatches;
  }

  public void setListDtBatches(List<DTBatch> listDtBatches) {
    this.listDtBatches = listDtBatches;
  }

  public List<DTBatch> getSelectedBatches() {
    return selectedBatches;
  }

  public void setSelectedBatches(List<DTBatch> selectedBatches) {
    this.selectedBatches = selectedBatches;
  }

  public List<DTBatch> getFilteredBatches() {
    return filteredBatches;
  }

  public void setFilteredBatches(List<DTBatch> filteredBatches) {
    this.filteredBatches = filteredBatches;
  }

  //  public void setListDtBatchs(List<DTBatch> listDtBatchs) {
//    this.listDtBatchs = listDtBatchs;
//  }

  public String test(){
    Long vId = getVaccineId();
    clear();
    return "batch?faces-redirect=true?includeViewParams=true&vaccineId="+vId;
  }


  public void save() throws IOException, ParseException {
    String msg;
    Date creationDate = new Date();
    int expDate = 123;
    if(getId() == null){
      available = getQuantity();
    }


    DTBatch d = new DTBatch(getNumber(), getQuantity(), creationDate, expDate, getAvailable());
    d.setVaccine(getVaccineId().toString());
    if (getId() == null) {
      d.setAvailable(getQuantity());
      iBatchController.saveBatch(d);
      msg = "Lote " + d.getNumber() + " creado";
    } else {
      d.setId(getId());
      iBatchController.saveBatch(d);
      msg = "Lote " + d.getNumber() + "  actualizado";
    }
    clear();
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("batch.jsf?vaccineId="+vaccineId);
  }

  public boolean isNew() {
    Long i = getId();
    return dtBat == null || dtBat.getId() == null;
  }

  public void clear() {
    id = null;
    number = 0;
    quantity = 0;
    creationDate = null;
    expirationDate = 0;
    available = 0;

  }

  public void remove() throws IOException {
    if (has(id)) {
      iBatchController.deleteBatch(id);
      DTBatch d = iBatchController.getByIdBatch(id);
      addDetailMessage("Lote " + d.getNumber() + " eliminado");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("batch.jsf?vaccineId="+vaccineId);
    }
  }

  private void catchData() {
    try {
      Long i = getVaccineId();
      listDtBatches = iBatchController.getBatchesByVaccineId(i);
      setNameTitle(iVaccineController.getVaccineById(vaccineId).getName());
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addBatch(DTBatch newBat) {
    try {
      iBatchController.saveBatch(newBat);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remBatches() throws IOException {
    try {
      for (DTBatch l : selectedBatches) {
        iBatchController.deleteBatch(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedBatches.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("batch.jsf?vaccineId"+vaccineId);
    return "ok";
  }

  public String updBatch(DTBatch updBat) {
    try {
      iBatchController.saveBatch(updBat);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTBatch> getBatches() {
    return listDtBatches;
  }

  public DTBatch getBatchById(Long id) {
    try {
      return iBatchController.getByIdBatch(id);
    } catch (Exception e) {
      return new DTBatch();
    }
  }



  public String getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(String nameTitle) {
    this.nameTitle = nameTitle;
  }

  public String nameTitle(){
    return iVaccineController.getVaccineById(vaccineId).getName();
  }

}
