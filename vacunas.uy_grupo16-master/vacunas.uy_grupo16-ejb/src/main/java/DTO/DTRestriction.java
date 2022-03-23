package DTO;

import enumerations.LogicOp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTRestriction extends DTBaseModel implements Serializable {

  private String elementName;
  private LogicOp logicOperator;
  private int value;
  private String description;
  private List<DTVaccine> vaccines;
  private List<DTVaccinationPlan> vaccinationPlans;
  private DTDataSource dataSource;

  public DTRestriction() {
    super();
  }

  public DTRestriction(String elementName, LogicOp logicOperator, int value, String description, DTDataSource dataSource) {
    this.elementName = elementName;
    this.logicOperator = logicOperator;
    this.value = value;
    this.description = description;
    this.dataSource = dataSource;
  }

  public DTRestriction(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String elementName,
    LogicOp logicOperator,
    int value,
    String description,
    List<DTVaccine> vaccines,
    List<DTVaccinationPlan> vaccinationPlans,
    DTDataSource dataSource
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

  public List<DTVaccine> getVaccines() {
    return vaccines;
  }

  public void setVaccines(List<DTVaccine> vaccines) {
    this.vaccines = vaccines;
  }

  public List<DTVaccinationPlan> getVaccinationPlans() {
    return vaccinationPlans;
  }

  public void setVaccinationPlans(List<DTVaccinationPlan> vaccinationPlans) {
    this.vaccinationPlans = vaccinationPlans;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DTDataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DTDataSource dataSource) {
    this.dataSource = dataSource;
  }
}
