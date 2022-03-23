package uy.vacunas.ui.Certification;

import java.io.Serializable;

public class VaccineVo implements Serializable {

    private String vaccineName;
    private String vaccinePlan;
    private String date;
    private int imgId;
    private int imgDetail;

    public VaccineVo(){}

    public VaccineVo(String vaccineName, String vaccinePlan, String date, int imgId) {
        this.vaccineName = vaccineName;
        this.vaccinePlan = vaccinePlan;
        this.date = date;
        this.imgId = imgId;
    }



    public String getVaccineName() {
        return vaccineName;
    }

/*    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }*/

    public String getVaccinePlan() {
        return vaccinePlan;
    }

/*
    public void setVaccinePlan(String vaccinePlan) {
        this.vaccinePlan = vaccinePlan;
    }
*/

    public String getDate() {
        return date;
    }

/*
    public void setDate(String date) {
        this.date = date;
    }
*/

   /* public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getImgDetail() {
        return imgDetail;
    }

    public void setImgDetail(int imgDetail) {
        this.imgDetail = imgDetail;
    }*//* public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getImgDetail() {
        return imgDetail;
    }

    public void setImgDetail(int imgDetail) {
        this.imgDetail = imgDetail;
    }*/
}


