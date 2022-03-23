package DTO;

import java.io.Serializable;
import java.util.Date;

public class DTEvent extends DTBaseModel implements Serializable {

    private Date date;

    public DTEvent() {
        super();
    }

    public DTEvent(Long id, Date createDate, Date updateDate, Date deleteDate, String userid, Date date) {
        super(id, createDate, updateDate, deleteDate, userid);

        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
