package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTAuthority extends DTRole implements Serializable {

  private List<DTVaccinationPlan> vaccinationPlans;
  private List<DTSchedule> schedules;

  public DTAuthority() {
    super();
  }

  public DTAuthority(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    DTPerson dtPerson,
    List<DTVaccinationPlan> vaccinationPlans,
    List<DTSchedule> schedules
  ) {
    super(id, createDate, updateDate, deleteDate, userid, name, dtPerson);
    this.vaccinationPlans = vaccinationPlans;
    this.schedules = schedules;
  }

  public List<DTVaccinationPlan> getVaccinationPlans() {
    return vaccinationPlans;
  }

  public void setVaccinationPlans(List<DTVaccinationPlan> vaccinationPlans) {
    this.vaccinationPlans = vaccinationPlans;
  }

  public List<DTSchedule> getSchedules() {
    return schedules;
  }

  public void setSchedules(List<DTSchedule> schedules) {
    this.schedules = schedules;
  }
}
