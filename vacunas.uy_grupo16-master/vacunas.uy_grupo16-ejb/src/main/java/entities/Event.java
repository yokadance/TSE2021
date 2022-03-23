package entities;

import DTO.DTEvent;
import org.modelmapper.ModelMapper;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@MappedSuperclass
public abstract class Event extends BaseModel{
    @Temporal(TemporalType.DATE)
    private Date date;

    public Event() {
    }

    public Event(Long id, Date createDate, Date updateDate, Date deleteDate, String user, Date date) {
        super(id, createDate, updateDate, deleteDate, user);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DTEvent getDTEvent(){
        ModelMapper modelMapper = new ModelMapper();
        DTEvent dt = modelMapper.map(this, DTEvent.class);
        return dt;
    }
}
