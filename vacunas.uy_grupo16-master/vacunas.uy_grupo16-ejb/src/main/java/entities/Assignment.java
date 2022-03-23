package entities;

import javax.persistence.*;

@Entity
@Table(name = "assignment")
public class Assignment extends BaseModel {

  @Embedded
  private AssignmentPK pkId;

  private String startDate;
  private String endDate;
  private String startTime;
  private String endTime;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @MapsId("id_vpost")
  @JoinColumn(name = "id_vpost")
  private VaccinationPost vaccinationPost;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @MapsId("id_vaccinator")
  @JoinColumn(name = "id_vaccinator")
  private Vaccinator vaccinator;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @MapsId("id_schedule")
  @JoinColumn(name = "id_schedule")
  private Schedule schedule;

  public Assignment() {}

  public Assignment(String startDate, String endDate, String startTime, String endTime, String userid) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Assignment(
    String startDate,
    String endDate,
    String startTime,
    String endTime,
    VaccinationPost vaccinationPost,
    Vaccinator vaccinator,
    Schedule schedule
  ) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.vaccinationPost = vaccinationPost;
    this.vaccinator = vaccinator;
    this.schedule = schedule;
  }

  public AssignmentPK getPkId() {
    return pkId;
  }

  public void setPkId(AssignmentPK id) {
    this.pkId = id;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public VaccinationPost getVaccinationPost() {
    return vaccinationPost;
  }

  public void setVaccinationPost(VaccinationPost vaccinationPost) {
    this.vaccinationPost = vaccinationPost;
  }

  public Vaccinator getVaccinator() {
    return vaccinator;
  }

  public void setVaccinator(Vaccinator vaccinator) {
    this.vaccinator = vaccinator;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }
}
