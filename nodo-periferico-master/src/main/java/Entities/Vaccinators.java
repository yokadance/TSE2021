package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity
@XmlRootElement(name = "return")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vaccinators implements Serializable {

    @Id
    private int id;
    private String vPostName;
    private int documentNumber;
    private String name;
    private String vaccinationPostId;
    private String date;

    public Vaccinators() {
    }

    public Vaccinators(int id, String vPostName, int documentNumber, String name, String vaccinationPostId, String date) {
        this.id = id;
        this.vPostName = vPostName;
        this.documentNumber = documentNumber;
        this.name = name;
        this.vaccinationPostId = vaccinationPostId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getvPostName() {
        return vPostName;
    }

    public void setvPostName(String vPostName) {
        this.vPostName = vPostName;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
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
