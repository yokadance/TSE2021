package entities;

import DTO.DTIot;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;
@Entity
public class IOT extends Event {
    @ManyToOne(fetch = FetchType.EAGER)
    private LogisticPartner logisticPartner;
    private String message;

    public IOT() {
    }

    public IOT(Long id, Date createDate, Date updateDate, Date deleteDate, String user, Date date, LogisticPartner logisticPartner, String message) {
        super(id, createDate, updateDate, deleteDate, user, date);
        this.logisticPartner = logisticPartner;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogisticPartner getLogisticPartner() {
        return logisticPartner;
    }

    public void setLogisticPartner(LogisticPartner logisticPartner) {
        this.logisticPartner = logisticPartner;
    }
    public DTIot getDTIot(){
        ModelMapper modelMapper = new ModelMapper();
        DTIot dt = modelMapper.map(this, DTIot.class);
        return dt;
    }
}
