package entities;

import DTO.DTAdministrator;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Administrator extends Role {

  public Administrator() {
    super();
  }

  /*
    public Administrator(DTAdministrator dtAdministrator) {
        Person p = Person(dtAdministrator.getDtPerson().getId(),);
        super(dtAdministrator.getId(), dtAdministrator.getCreateDate(), dtAdministrator.getUpdateDate(), dtAdministrator.getDeleteDate(), dtAdministrator.getUserid(), dtAdministrator.getName(), p);
    }*/

  public Administrator(Long id, Date createDate, Date updateDate, Date deleteDate, String userid, String name, Person people) {
    super(id, createDate, updateDate, deleteDate, userid, name, people);
  }

  public DTAdministrator getDTAdministrator() {
    DTAdministrator dtA = new DTAdministrator();
    dtA.setId(this.getId());
    dtA.setName(this.getName());
    dtA.setDeleteDate(this.getDeleteDate());
    return dtA;
    //        ModelMapper modelMapper = new ModelMapper();
    //        DTAdministrator dt = modelMapper.map(this, DTAdministrator.class);
    //        return dt;
  }
}
