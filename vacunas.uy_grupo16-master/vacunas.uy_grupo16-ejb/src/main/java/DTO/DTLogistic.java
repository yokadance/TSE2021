package DTO;

import enumerations.BatchState;

import java.io.Serializable;
import java.util.Date;

public class DTLogistic extends DTEvent implements Serializable {

  private DTLogisticPartner logisticPartner;
  private BatchState typeEvent;

  public DTLogistic(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    Date date,
    DTLogisticPartner logisticPartner,
    BatchState typeEvent
  ) {
    super(id, createDate, updateDate, deleteDate, userid, date);
    this.logisticPartner = logisticPartner;
    this.typeEvent = typeEvent;
  }

  public DTLogistic() {
    super();
  }

  public DTLogisticPartner getLogisticPartner() {
    return logisticPartner;
  }

  public void setLogisticPartner(DTLogisticPartner logisticPartner) {
    this.logisticPartner = logisticPartner;
  }

  public BatchState getTypeEvent() {
    return typeEvent;
  }

  public void setTypeEvent(BatchState typeEvent) {
    this.typeEvent = typeEvent;
  }
}
