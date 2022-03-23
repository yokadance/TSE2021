package uy.vacunas.ui.VaccinationCenter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class VaccinationCenters {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createDate")
    @Expose
    private Long createDate;
    @SerializedName("updateDate")
    @Expose
    private Long updateDate;
    @SerializedName("deleteDate")
    @Expose
    private Object deleteDate;
    @SerializedName("userid")
    @Expose
    private Object userid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("vaccinationPosts")
    @Expose
    private List<String> vaccinationPosts = null;
    @SerializedName("vaccinations")
    @Expose
    private List<Object> vaccinations = null;
    @SerializedName("schedules")
    @Expose
    private List<String> schedules = null;
    @SerializedName("vaccinationPlans")
    @Expose
    private List<String> vaccinationPlans = null;
    @SerializedName("packages")
    @Expose
    private List<String> packages = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

}