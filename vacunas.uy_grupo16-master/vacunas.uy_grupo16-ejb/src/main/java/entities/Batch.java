package entities;

import DTO.DTBatch;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Batch extends BaseModel {

  private long number;
  private int quantity;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  private int expirationDate;
  private int available;


  @OneToMany(mappedBy = "batch", fetch = FetchType.EAGER)
  private List<Package> aPackage;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "vaccine_Batch")
  private Vaccine vaccine;

  public Batch() {}

    public Batch(long number, int quantity, Date creationDate, int expirationDate, int available) {
        this.number = number;
        this.quantity = quantity;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.available = available;
    }

  public Batch(
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
    List<Package> aPackage,
    Vaccine vaccine
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

  public List<Package> getaPackage() {
    return aPackage;
  }

  public void setaPackage(List<Package> aPackage) {
    this.aPackage = aPackage;
  }

  public Vaccine getVaccine() {
    return vaccine;
  }

  public void setVaccine(Vaccine vaccine) {
    this.vaccine = vaccine;
  }

  public DTBatch getDTBatch() {
    ModelMapper modelMapper = new ModelMapper();
    DTBatch dt = modelMapper.map(this, DTBatch.class);
    return dt;
  }
}
