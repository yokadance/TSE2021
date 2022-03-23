package DTO;

import java.io.Serializable;
import java.util.Date;

public class DTAdministrator extends DTRole implements Serializable {

  public DTAdministrator() {
    super();
  }

  public DTAdministrator(Long id, Date createDate, Date updateDate, Date deleteDate, String userid, String name, DTPerson dtPerson) {
    super(id, createDate, updateDate, deleteDate, userid, name, dtPerson);
  }
}
