package com.logistics.prod.Entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.sql.rowset.serial.SerialArray;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement(name = "return") //, namespace = "http://schemas.xmlsoap.org/soap/envelope/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Partner implements Serializable {

    @Id
    private Long id;
    private int packageId;
    private String updateDate;
    private String packageStatus;

    @Override
    public String toString() {
        return  id + "-"+ packageId +"-"+ packageStatus;
    }

    public Partner(Long id, int packageId, String packageStatus, String updateDate) {
        this.id = id;
        this.packageId = packageId;
        this.packageStatus = packageStatus;
        this.updateDate = updateDate;
    }

    public Partner() {

    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }
}
