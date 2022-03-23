package DTO;

import java.io.Serializable;
import java.util.Date;

public abstract class DTBaseModel implements Serializable {

  private Long id;
  private Date createDate;
  private Date updateDate;
  private Date deleteDate;
  private String userid;

  public DTBaseModel() {}

  public DTBaseModel(Long id, Date createDate, Date updateDate, Date deleteDate, String userid) {
    this.id = id;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.deleteDate = deleteDate;
    this.userid = userid;
  }

  public DTBaseModel(Date createDate, Date updateDate, Date deleteDate, String userid) {
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.deleteDate = deleteDate;
    this.userid = userid;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Date getDeleteDate() {
    return deleteDate;
  }

  public void setDeleteDate(Date deleteDate) {
    this.deleteDate = deleteDate;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  @Override
  public String toString() {
    return (
      "DTBaseModel{" +
      "id=" +
      id +
      ", createDate=" +
      createDate +
      ", updateDate=" +
      updateDate +
      ", deleteDate=" +
      deleteDate +
      ", userid='" +
      userid +
      '\'' +
      '}'
    );
  }
}
