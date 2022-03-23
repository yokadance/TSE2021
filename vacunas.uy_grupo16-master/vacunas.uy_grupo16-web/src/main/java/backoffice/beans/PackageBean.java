package backoffice.beans;

import DTO.DTBatch;
import DTO.DTPackage;
import IController.IBatchController;
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
import java.util.List;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

@Named("PackageBean")
@SessionScoped
public class PackageBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long batchId;
  private Long vaccineId;
  private Long id;
  private Long packageNumber;
  private Long quantity;
  private PackageState state;

  private Long nameTitle;

  private DTPackage dtPack;

  private List<DTPackage> listDtPackages;

  private List<DTPackage> selectedPackages;

  private List<DTPackage> filteredPackages;



  Filter<DTPackage> filter = new Filter<>(new DTPackage());

  @EJB
  IPackageController iPackageController;

  @EJB
  IBatchController iBatchController;

  public PackageBean() {}

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
      setBatchId(batchId);
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

  public Long getBatchId() {
    return batchId;
  }

  public void setBatchId(Long batchId) {
    this.batchId = batchId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getVaccineId() {
    return vaccineId;
  }

  public void setVaccineId(Long vaccineId) {
    this.vaccineId = vaccineId;
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

  public Long getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(Long nameTitle) {
    this.nameTitle = nameTitle;
  }

  public Long nameTitle(){
    return iBatchController.getByIdBatch(batchId).getNumber();
  }

  public DTPackage getDtPack() {
    return dtPack;
  }

  public void setDtPack(DTPackage dtPack) {
    this.dtPack = dtPack;
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
    Long bId = getBatchId();
    clear();
    return "package?faces-redirect=true?includeViewParams=true&batchId="+bId;
  }

  public String back(){
    Long vId = getVaccineId();
    clear();
    return "batch?faces-redirect=true?includeViewParams=true&vaccineId="+vId;
  }

  public void save() throws IOException, ParseException {
    String msg;

    DTBatch dtBatch = iBatchController.getByIdBatch(batchId);

    if(getQuantity() > 0) {
      if (dtBatch.getAvailable() >= getQuantity()) {
        dtBatch.setAvailable(dtBatch.getAvailable() - (getQuantity()).intValue());
        iBatchController.saveBatch(dtBatch);
        DTPackage d = new DTPackage(getPackageNumber(), getQuantity());
        d.setBatch(getBatchId().toString());
        d.setPackageState(PackageState.Pending);
        if (getId() == null) {
          iPackageController.savePackage(d);
          msg = "Paquete " + d.getPackageNumber() + " creado";
        } else {
          d.setId(getId());
          iPackageController.savePackage(d);
          msg = "Paquete " + d.getPackageNumber() + "  actualizado";
        }
        clear();
        addDetailMessage(msg);
        Faces.getFlash().setKeepMessages(true);
        Faces.redirect("package.jsf?batchId=" + batchId);
      }else {
        msg = "La cantidad de vacunas disponible en el Lote " + dtBatch.getNumber() + " es de: " + dtBatch.getAvailable();
      }
    }else {
      msg = "La cantidad de vacunas no puede ser cero";
    }
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);

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
      if(quantity != 0) {
        iPackageController.deletePackage(id);
        DTPackage d = iPackageController.getByIdPackage(id);
        addDetailMessage("Paquete " + d.getPackageNumber() + " eliminado");
        Faces.getFlash().setKeepMessages(true);
        Faces.redirect("package.jsf?batchId=" + batchId);
      }else {
        addDetailMessage("No se puede eliminar un paquete con vacunas");
        Faces.getFlash().setKeepMessages(true);
        Faces.redirect("package.jsf?batchId=" + batchId);
      }
    }
  }



  private void catchData() {
    try {
      Long i = getBatchId();
      listDtPackages = iPackageController.getPackagesByBatchId(i);
      nameTitle = iBatchController.getByIdBatch(batchId).getNumber();
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
          if(l.getQuantity() == 0) {
            iPackageController.deletePackage(l.getId());
          }else{
            addDetailMessage("Los paquetes con vacunas no fueron eliminados");

          }
        }
      } catch (Exception e) {
        return e.getMessage();
      }

      selectedPackages.clear();
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("package.jsf?batchId=" + batchId);
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
