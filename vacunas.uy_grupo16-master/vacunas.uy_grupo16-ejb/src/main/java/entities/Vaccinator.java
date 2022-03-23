package entities;

import DTO.DTPerson;
import DTO.DTVaccinator;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Vaccinator extends Role {

  @OneToMany(mappedBy = "vaccinator")
  private List<Assignment> assignment;

  public Vaccinator() {}

  public Vaccinator(
    Long id,
    Date createDate,
    Date updateDate,
    Date deleteDate,
    String userid,
    String name,
    Person person,
    List<Assignment> assignment
  ) {
    super(id, createDate, updateDate, deleteDate, userid, name, person);
    this.assignment = assignment;
  }

  public List<Assignment> getAssignment() {
    return assignment;
  }

  public void setAssignment(List<Assignment> assignment) {
    this.assignment = assignment;
  }

  /*
      public Vaccinator(
        Long id,
        Date createDate,
        Date updateDate,
        Date deleteDate,
        String userid,
        String name,
        VaccinationPost vaccinationPost
      ) {
        //super(id, createDate, updateDate, deleteDate, userid, name, people);

        this.vaccinationPost = vaccinationPost;
      }

      public VaccinationPost getVaccinationPost() {
        return vaccinationPost;
      }

      public void setVaccinationPost(VaccinationPost vaccinationPost) {
        this.vaccinationPost = vaccinationPost;
      }
    */
  public DTVaccinator getDTVaccinator() {
    DTVaccinator dtV = new DTVaccinator();

    //CREO A MANO DT PERSONA POR Q ENTRA EN LOOP
    DTPerson dtP = new DTPerson();
    dtP.setId(this.getPerson().getId());
    dtP.setName(this.getPerson().getName());
    dtP.setLastname(this.getPerson().getLastname());
    // -----------------------------------------

    dtV.setDtPerson(dtP);
    dtV.setId(this.getId());
    dtV.setName(this.getName());
    return dtV;
    //    ModelMapper modelMapper = new ModelMapper();
    //    DTVaccinator dt = modelMapper.map(this, DTVaccinator.class);
    //    return dt;
  }
}
