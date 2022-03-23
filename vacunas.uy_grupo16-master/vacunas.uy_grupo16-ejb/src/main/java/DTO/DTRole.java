package DTO;

import java.io.Serializable;
import java.util.Date;

public abstract class DTRole extends DTBaseModel implements Serializable {
    private String name;
    private DTPerson dtPerson;

    public DTRole() {
        super();
    }

    public DTRole(Long id, Date createDate, Date updateDate, Date deleteDate, String userid, String name, DTPerson dtPerson) {
        super(id, createDate, updateDate, deleteDate, userid);
        this.name = name;
        this.dtPerson = dtPerson;
    }

    public DTRole(Date createDate, Date updateDate, Date deleteDate, String userid, String name, DTPerson dtPerson) {
        super(createDate, updateDate, deleteDate, userid);
        this.name = name;
        this.dtPerson = dtPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DTPerson getDtPerson() {
        return dtPerson;
    }

    public void setDtPerson(DTPerson dtPerson) {
        this.dtPerson = dtPerson;
    }
}
