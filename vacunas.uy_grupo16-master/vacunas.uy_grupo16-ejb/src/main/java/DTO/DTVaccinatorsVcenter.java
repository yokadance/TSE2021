package DTO;

import java.io.Serializable;

public class DTVaccinatorsVcenter implements Serializable {

    private Long id;
    private String vPostName;
    private String documentNumber;
    private String name;
    private String vaccinationPostId;
    private String date;

    public DTVaccinatorsVcenter() {
    }

    public DTVaccinatorsVcenter(Long id, String vPostName, String documentNumber, String name, String vaccinationPostId, String date) {
        this.id = id;
        this.vPostName = vPostName;
        this.documentNumber = documentNumber;
        this.name = name;
        this.vaccinationPostId = vaccinationPostId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getvPostName() {
        return vPostName;
    }

    public void setvPostName(String vPostName) {
        this.vPostName = vPostName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVaccinationPostId() {
        return vaccinationPostId;
    }

    public void setVaccinationPostId(String vaccinationPostId) {
        this.vaccinationPostId = vaccinationPostId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
