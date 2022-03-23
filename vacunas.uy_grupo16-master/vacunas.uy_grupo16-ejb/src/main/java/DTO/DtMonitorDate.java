package DTO;

import java.io.Serializable;

public class DtMonitorDate implements Serializable {

  private String date;
  private Long doses1;
  private Long doses2;
  private Long doses3;
  private Long doses4;
  private Long doses5;

  public DtMonitorDate() {}

  public DtMonitorDate(String date, Long doses1, Long doses2, Long doses3, Long doses4, Long doses5) {
    this.date = date;
    this.doses1 = doses1;
    this.doses2 = doses2;
    this.doses3 = doses3;
    this.doses4 = doses4;
    this.doses5 = doses5;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Long getDoses1() {
    return doses1;
  }

  public void setDoses1(Long doses1) {
    this.doses1 = doses1;
  }

  public Long getDoses2() {
    return doses2;
  }

  public void setDoses2(Long doses2) {
    this.doses2 = doses2;
  }

  public Long getDoses3() {
    return doses3;
  }

  public void setDoses3(Long doses3) {
    this.doses3 = doses3;
  }

  public Long getDoses4() {
    return doses4;
  }

  public void setDoses4(Long doses4) {
    this.doses4 = doses4;
  }

  public Long getDoses5() {
    return doses5;
  }

  public void setDoses5(Long doses5) {
    this.doses5 = doses5;
  }

  @Override
  public String toString() {
    return (
      "DtMonitorDate{" +
      "date='" +
      date +
      '\'' +
      ", doses1=" +
      doses1 +
      ", doses2=" +
      doses2 +
      ", doses3=" +
      doses3 +
      ", doses4=" +
      doses4 +
      ", doses5=" +
      doses5 +
      '}'
    );
  }

  public void setDoses(Integer dose, Long quantity) {
    if (dose == 1) {
      this.setDoses1(quantity);
    }
    if (dose == 2) {
      this.setDoses2(quantity);
    }
    if (dose == 3) {
      this.setDoses3(quantity);
    }
    if (dose == 4) {
      this.setDoses4(quantity);
    }
    if (dose == 5) {
      this.setDoses5(quantity);
    }
  }
}
