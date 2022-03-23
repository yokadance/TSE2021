package Entities;

import Enumerations.ReservationState;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement(name = "return") //, namespace = "http://schemas.xmlsoap.org/soap/envelope/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation implements Serializable {

    @Id
    private Long id; //ok
    private String createDate;
    private Long vaccinationPostId; //ok
    private String citizenId; //ok
    private String personId; //ok
    private String reservationDate; //ok
    private String reservationTime; //ok
    private String reservationState; //ok
    private int scheduleId; //ok
    private String observation;
    private int vaccinatorId;
    private boolean sended;



    public Reservation() {
    }

    public Reservation(Long id, String createDate, Long vaccinationPostId, String citizenId, String personId, String reservationDate, String reservationTime, String reservationState, int scheduleId, String observation, int vaccinatorId, boolean sended) {
        this.id = id;
        this.createDate = createDate;
        this.vaccinationPostId = vaccinationPostId;
        this.citizenId = citizenId;
        this.personId = personId;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.reservationState = reservationState;
        this.scheduleId = scheduleId;
        this.observation = observation;
        this.vaccinatorId = vaccinatorId;
        this.sended = sended;
    }

    public boolean isSended() {
        return sended;
    }

    public void setSended(boolean sended) {
        this.sended = sended;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public int getVaccinatorId() {
        return vaccinatorId;
    }

    public void setVaccinatorId(int vaccinatorId) {
        this.vaccinatorId = vaccinatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getVaccinationPostId() {
        return vaccinationPostId;
    }

    public void setVaccinationPostId(Long vaccinationPostId) {
        this.vaccinationPostId = vaccinationPostId;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getReservationState() {
        return reservationState;
    }

    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
