package Entities;

import Enumerations.ReservationState;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@Entity
//@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
//@XmlRootElement(name = "return")//, namespace = "http://schemas.xmlsoap.org/soap/envelope/") //, namespace = "http://schemas.xmlsoap.org/soap/envelope/")
@XmlRootElement(name = "schedules")
@XmlAccessorType(XmlAccessType.FIELD)

public class Schedule implements Serializable {

   @Id
    private int id;
    private String createDate;
    private String startDate;
    private String deleteDate;
    private String updateDate;
    private String endDate;
    private String startTimeDaily;
    private String endTimeDaily;
    private int fraction;
    private boolean saturday;
    private boolean sunday;

    public Schedule() {

    }

    public Schedule(int id, String createDate, String startDate, String deleteDate, String updateDate, String endDate, String startTimeDaily, String endTimeDaily, int fraction, boolean saturday, boolean sunday) {
        this.id = id;
        this.createDate = createDate;
        this.startDate = startDate;
        this.deleteDate = deleteDate;
        this.updateDate = updateDate;
        this.endDate = endDate;
        this.startTimeDaily = startTimeDaily;
        this.endTimeDaily = endTimeDaily;
        this.fraction = fraction;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    //@XmlElement
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    //@XmlElement
    public String getStartTimeDaily() {
        return startTimeDaily;
    }

    public void setStartTimeDaily(String startTimeDaily) {
        this.startTimeDaily = startTimeDaily;
    }
    //@XmlElement
    public String getEndTimeDaily() {
        return endTimeDaily;
    }
    //@XmlElement
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    //@XmlElement
    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }
    //@XmlElement
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setEndTimeDaily(String endTimeDaily) {
        this.endTimeDaily = endTimeDaily;
    }
    //@XmlElement
    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }
    //@XmlElement
    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }
    //@XmlElement
    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }
}
