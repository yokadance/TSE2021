package entities;

import DTO.DTDataSource;
import DTO.DTRestriction;
import DTO.DTVaccinationPlan;
import DTO.DTVaccine;
import enumerations.LogicOp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Restriction extends BaseModel {

  private String elementName;
  private LogicOp logicOperator;
  private int value;
  private String description;

  @ManyToMany(mappedBy = "restrictions", fetch = FetchType.LAZY)
  private List<Vaccine> vaccines;

  @ManyToMany(mappedBy = "restriction", fetch = FetchType.LAZY)
  private List<VaccinationPlan> vaccinationPlans;

  @OneToOne(fetch = FetchType.EAGER)
  private DataSource dataSource;

  public Restriction() {
    super();
  }

  public Restriction(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String elementName,
    LogicOp logicOperator,
    int value,
    String description,
    List<Vaccine> vaccines,
    List<VaccinationPlan> vaccinationPlans,
    DataSource dataSource
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.elementName = elementName;
    this.logicOperator = logicOperator;
    this.value = value;
    this.description = description;
    this.vaccines = vaccines;
    this.vaccinationPlans = vaccinationPlans;
    this.dataSource = dataSource;
  }

  //
  //  public Restriction(
  //    Long id,
  //    Date createDate,
  //    Date updateDate,
  //    Date deleteDate,
  //    String user,
  //    String elementName,
  //    LogicOp logicOperator,
  //    int value,
  //    List<Vaccine> vaccines,
  //    List<VaccinationPlan> vaccinationPlans,
  //    DataSource dataSource
  //  ) {
  //    super(id, createDate, updateDate, deleteDate, user);
  //    this.elementName = elementName;
  //    this.logicOperator = logicOperator;
  //    this.value = value;
  //    this.vaccines = vaccines;
  //    this.vaccinationPlans = vaccinationPlans;
  //    this.dataSource = dataSource;
  //  }

  public String getElementName() {
    return elementName;
  }

  public void setElementName(String elementName) {
    this.elementName = elementName;
  }

  public LogicOp getLogicOperator() {
    return logicOperator;
  }

  public void setLogicOperator(LogicOp logicOperator) {
    this.logicOperator = logicOperator;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public List<Vaccine> getVaccines() {
    return vaccines;
  }

  public void setVaccines(List<Vaccine> vaccines) {
    this.vaccines = vaccines;
  }

  public List<VaccinationPlan> getVaccinationPlans() {
    return vaccinationPlans;
  }

  public void setVaccinationPlans(List<VaccinationPlan> vaccinationPlans) {
    this.vaccinationPlans = vaccinationPlans;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DTRestriction getDTRestriction() {
    DTRestriction dt = new DTRestriction();
    dt.setId(this.getId());
    dt.setElementName(this.getElementName());
    dt.setLogicOperator(this.getLogicOperator());
    dt.setValue(this.getValue());
    dt.setDescription(this.description);

    List<DTVaccine> dtVaccines = new ArrayList<>();
    if (this.getVaccines() != null) {
      for (Vaccine vac : this.getVaccines()) {
        dtVaccines.add(vac.getDTVaccine());
      }
    }
    dt.setVaccines(dtVaccines);

    List<DTVaccinationPlan> dtVacPlans = new ArrayList<>();
    if (this.getVaccinationPlans() != null) {
      for (VaccinationPlan vPlan : this.getVaccinationPlans()) {
        dtVacPlans.add(vPlan.getDTVaccinationPlan());
      }
    }
    dt.setVaccinationPlans(dtVacPlans);

    DTDataSource dtS = new DTDataSource();
    if (this.getDataSource() != null) {
      dtS.setName(this.getDataSource().getName());
      dtS.setUrl(this.getDataSource().getUrl());
      dtS.setId(this.getDataSource().getId());
    }
    dt.setDataSource(dtS);

    return dt;
  }
}
