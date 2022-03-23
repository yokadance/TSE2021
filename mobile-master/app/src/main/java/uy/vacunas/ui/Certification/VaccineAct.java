package uy.vacunas.ui.Certification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VaccineAct {
    @SerializedName("personName")
    @Expose
    private String personName;
    @SerializedName("personSurname")
    @Expose
    private String personSurname;
    @SerializedName("personLastName")
    @Expose
    private String personLastName;
    @SerializedName("personCi")
    @Expose
    private String personCi;
    @SerializedName("personBirthday")
    @Expose
    private String personBirthday;
    @SerializedName("vaccinationActDate")
    @Expose
    private String vaccinationActDate;
    @SerializedName("vaccine")
    @Expose
    private String vaccine;
    @SerializedName("vaccinationCenter")
    @Expose
    private String vaccinationCenter;
    @SerializedName("disease")
    @Expose
    private Object disease;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonCi() {
        return personCi;
    }

    public void setPersonCi(String personCi) {
        this.personCi = personCi;
    }

    public String getPersonBirthday() {
        return personBirthday;
    }

    public void setPersonBirthday(String personBirthday) {
        this.personBirthday = personBirthday;
    }

    public String getVaccinationActDate() {
        return vaccinationActDate;
    }

    public void setVaccinationActDate(String vaccinationActDate) {
        this.vaccinationActDate = vaccinationActDate;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(String vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    public Object getDisease() {
        return disease;
    }

    public void setDisease(Object disease) {
        this.disease = disease;
    }

}