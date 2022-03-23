package backoffice.beans;

import DTO.DTDataSource;
import DTO.DTDisease;
import IController.IDataSourceController;
import IController.IDiseaseController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

@Named("DataSourceBean")
@ViewScoped
public class DataSourceBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long id;

  private String name;

  private String url;

  private DTDisease dtDataS;

  private List<DTDataSource> listDtDataSources;

  private List<DTDataSource> selectedDataSources;

  private List<DTDataSource> filteredDataSources;

  private Long dataId;

  Filter<DTDataSource> filter = new Filter<>(new DTDataSource());

  @EJB
  IDataSourceController iDataSourceController;

  public DataSourceBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTDataSource d = iDataSourceController.getDataSourceById(id);
      setName(d.getName());
      setUrl(d.getUrl());
    } else {
      DTDataSource d = new DTDataSource();
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public DTDisease getDtDataS() {
    return dtDataS;
  }

  public void setDtDataS(DTDisease dtDataS) {
    this.dtDataS = dtDataS;
  }

  public List<DTDataSource> getListDtDataSources() {
    return listDtDataSources;
  }

  public void setListDtDataSources(List<DTDataSource> listDtDataSources) {
    this.listDtDataSources = listDtDataSources;
  }

  public List<DTDataSource> getSelectedDataSources() {
    return selectedDataSources;
  }

  public void setSelectedDataSources(List<DTDataSource> selectedDataSources) {
    this.selectedDataSources = selectedDataSources;
  }

  public List<DTDataSource> getFilteredDataSources() {
    return filteredDataSources;
  }

  public void setFilteredDataSources(List<DTDataSource> filteredDataSources) {
    this.filteredDataSources = filteredDataSources;
  }

  public Long getDataId() {
    return dataId;
  }

  public void setDataId(Long dataId) {
    this.dataId = dataId;
  }

  public Filter<DTDataSource> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTDataSource> filter) {
    this.filter = filter;
  }

  public void save() throws IOException {
    String msg;
    DTDataSource d = new DTDataSource();
    d.setName(getName());
    d.setUrl(getUrl());
    if (getId() == null) {
      iDataSourceController.saveDataSource(d);
      msg = "Fuente de datos " + d.getName() + " creada";
    } else {
      d.setId(getId());
      iDataSourceController.saveDataSource(d);
      msg = "Fuente de datos " + d.getName() + " creada" ;
    }
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("dataSource.jsf");
  }

  public boolean isNew() {
    Long i = getId();
    return dtDataS == null || dtDataS.getId() == null;
  }

  public void clear() {
    id = null;
    name = null;
    url = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iDataSourceController.deleteDataSource(id);
      DTDataSource d = iDataSourceController.getDataSourceById(id);
      addDetailMessage("Fuente de datos " + d.getName() + " eliminada");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("dataSource.jsf");
    }
  }

  private void catchData() {
    try {
      listDtDataSources = iDataSourceController.listDataSources();
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addDataSource(DTDataSource newData) {
    try {
      iDataSourceController.saveDataSource(newData);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué devolver
  }

  public String remDataSource() throws IOException {
    try {
      for (DTDataSource l : selectedDataSources) {
        iDataSourceController.deleteDataSource(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedDataSources.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("dataSource.jsf");
    return "ok";
  }

  public String updDataSource(DTDataSource updData) {
    try {
      iDataSourceController.saveDataSource(updData);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTDataSource> getDataSources() {
    return listDtDataSources;
  }

  public DTDataSource getDiseaseById(Long id) {
    try {
      return iDataSourceController.getDataSourceById(id);
    } catch (Exception e) {
      return new DTDataSource();
    }
  }


}
