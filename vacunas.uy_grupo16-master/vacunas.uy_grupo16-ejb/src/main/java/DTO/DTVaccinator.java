package DTO;

import java.io.Serializable;
import java.util.Date;

public class DTVaccinator extends DTRole implements Serializable {

  private DTVaccinationPost dtvaccinationPost;

  public DTVaccinator(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    DTPerson dtPerson,
    DTVaccinationPost dtvaccinationPost
  ) {
    super(id, createDate, updateDate, deleteDate, userid, name, dtPerson);
    this.dtvaccinationPost = dtvaccinationPost;
  }

  public DTVaccinator() {
    super();
  }

  public DTVaccinationPost getDtvaccinationPost() {
    return dtvaccinationPost;
  }

  public void setDtvaccinationPost(DTVaccinationPost dtvaccinationPost) {
    this.dtvaccinationPost = dtvaccinationPost;
  }
}
