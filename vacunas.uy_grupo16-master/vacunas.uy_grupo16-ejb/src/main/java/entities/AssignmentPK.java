package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssignmentPK implements Serializable {

  @Column(name = "id_vpost")
  private Long id_vpost;

  @Column(name = "id_vaccinator")
  private Long id_vaccinator;

  @Column(name = "id_schedule")
  private Long id_schedule;

  public Long getId_vpost() {
    return id_vpost;
  }

  public void setId_vpost(Long id_vpost) {
    this.id_vpost = id_vpost;
  }

  public Long getId_vaccinator() {
    return id_vaccinator;
  }

  public void setId_vaccinator(Long id_vaccinator) {
    this.id_vaccinator = id_vaccinator;
  }

  public Long getId_schedule() {
    return id_schedule;
  }

  public void setId_schedule(Long id_schedule) {
    this.id_schedule = id_schedule;
  }
}
