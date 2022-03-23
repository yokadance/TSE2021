package DTO;

import java.io.Serializable;
import java.util.Date;

public class DTBatch extends DTBaseModel implements Serializable {

  private long number;
  private int quantity;
  private Date creationDate;
  private int expirationDate;
  private int available;
  private String aPackage;
  private String vaccine;

  public DTBatch() {
    super();
  }

  public DTBatch(
          long number,
          int quantity,
          Date creationDate,
          int expirationDate,
          int available
  ) {
    this.number = number;
    this.quantity = quantity;
    this.creationDate = creationDate;
    this.expirationDate = expirationDate;
    this.available = available;
  }

  public DTBatch(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    long number,
    int quantity,
    Date creationDate,
    int expirationDate,
    int available,
    String aPackage,
    String vaccine
  ) {
    super(id, createDate, updateDate, deleteDate, userid);
    this.number = number;
    this.quantity = quantity;
    this.creationDate = creationDate;
    this.expirationDate = expirationDate;
    this.available = available;
    this.aPackage = aPackage;
    this.vaccine = vaccine;
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

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
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

  public String getaPackage() {
    return aPackage;
  }

  public void setaPackage(String aPackage) {
    this.aPackage = aPackage;
  }

  public String getVaccine() {
    return vaccine;
  }

  public void setVaccine(String vaccine) {
    this.vaccine = vaccine;
  }
}
