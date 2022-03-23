package entities;

import DTO.DTLogistic;
import enumerations.BatchState;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;
@Entity
public class Logistic extends Event {
    @ManyToOne(fetch = FetchType.EAGER)
    private LogisticPartner logisticPartner;
    private BatchState typeEvent;

    public Logistic() {
    }

    public Logistic(Long id, Date createDate, Date updateDate, Date deleteDate, String user, Date date, LogisticPartner logisticPartner, BatchState typeEvent) {
        super(id, createDate, updateDate, deleteDate, user, date);
        this.logisticPartner = logisticPartner;
        this.typeEvent = typeEvent;
    }

    public BatchState getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(BatchState typeEvent) {
        this.typeEvent = typeEvent;
    }

    public LogisticPartner getLogisticPartner() {
        return logisticPartner;
    }

    public void setLogisticPartner(LogisticPartner logisticPartner) {
        this.logisticPartner = logisticPartner;
    }

    public DTLogistic getDTLogistic(){
        ModelMapper modelMapper = new ModelMapper();
        DTLogistic dt = modelMapper.map(this, DTLogistic.class);
        return dt;
    }
}
