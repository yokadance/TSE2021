package entities;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date updateDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date deleteDate;

  private String userid;

  @PrePersist
  public void prePersist() {
    this.createDate = (createDate == null) ? new Date() : createDate;
    this.updateDate = (updateDate == null) ? createDate : new Date();
  }

  public BaseModel() {}

  public BaseModel(Long id, Date createDate, Date updateDate, Date deleteDate, String userid) {
    this.id = id;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.deleteDate = deleteDate;
    this.userid = userid;
  }

  public BaseModel(Long id, String userid) {
    this.id = id;
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

  public void setUserid(String user) {
    this.userid = user;
  }
}
