package DTO;

import java.io.Serializable;
import java.util.Date;

public class DTIot extends DTEvent implements Serializable {

  private DTLogisticPartner logisticPartner;
  private String message;

  public DTIot(Long id, Date createDate, Date updateDate, Date deleteDate, String userid, Date date) {
    super(id, createDate, updateDate, deleteDate, userid, date);
  }

  public DTIot() {
    super();
  }

  public DTLogisticPartner getLogisticPartner() {
    return logisticPartner;
  }

  public void setLogisticPartner(DTLogisticPartner logisticPartner) {
    this.logisticPartner = logisticPartner;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
