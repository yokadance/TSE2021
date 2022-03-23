package DTO;

import java.io.Serializable;
import java.util.Date;

public class DTDataSource extends DTBaseModel implements Serializable {

  private String name;
  private String url;
  private DTRestriction restriction;

  public DTDataSource() {
    super();
  }

  public DTDataSource(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    String url,
    DTRestriction restriction
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.name = name;
    this.url = url;
    this.restriction = restriction;
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

  public DTRestriction getRestriction() {
    return restriction;
  }

  public void setRestriction(DTRestriction restriction) {
    this.restriction = restriction;
  }
}
