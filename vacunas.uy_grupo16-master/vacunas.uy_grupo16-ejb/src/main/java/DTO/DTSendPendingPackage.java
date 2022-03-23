package DTO;

import enumerations.PackageState;

import java.io.Serializable;
import java.util.Date;

public class DTSendPendingPackage extends DTBaseModel implements Serializable {

    //private Long id;
    private Long packageId;
    private PackageState packageStatus;

    public DTSendPendingPackage(Date createDate, Date updateDate, Date deleteDate, String userid, Long packageId, PackageState packageStatus) {
        super(createDate, updateDate, deleteDate, userid);
       // this.id = id;
        this.packageId = packageId;
        this.packageStatus = packageStatus;
    }

    public DTSendPendingPackage() {

    }



    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public PackageState getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(PackageState packageStatus) {
        this.packageStatus = packageStatus;
    }
}
