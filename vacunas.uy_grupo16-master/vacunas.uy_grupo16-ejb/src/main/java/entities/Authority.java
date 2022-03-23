package entities;

import DTO.DTAuthority;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Authority extends Role {

  @OneToMany(mappedBy = "authority")
  private List<VaccinationPlan> vaccinationPlans;

  @OneToMany(mappedBy = "authority")
  private List<Schedule> schedules;

  public Authority() {}

  public Authority(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    List<Person> people,
    List<VaccinationPlan> vaccinationPlans,
    List<Schedule> schedules
  ) {
    //super(id, createDate, updateDate, deleteDate, userid, name, people);
    this.vaccinationPlans = vaccinationPlans;
    this.schedules = schedules;
  }

  public List<VaccinationPlan> getVaccinationPlans() {
    return vaccinationPlans;
  }

  public void setVaccinationPlans(List<VaccinationPlan> vaccinationPlans) {
    this.vaccinationPlans = vaccinationPlans;
  }

  public List<Schedule> getSchedules() {
    return schedules;
  }

  public void setSchedules(List<Schedule> schedules) {
    this.schedules = schedules;
  }

  public DTAuthority getDTAuthority() {
    DTAuthority dtA = new DTAuthority();
    dtA.setId(this.getId());
    dtA.setName(this.getName());
    dtA.setDeleteDate(this.getDeleteDate());
    return dtA;
    //    ModelMapper modelMapper = new ModelMapper();
    //    DTAuthority dt = modelMapper.map(this, DTAuthority.class);
    //    return dt;
  }
}
