package DAL;

import DTO.DTVaccinationPost;
import DTO.DTVaccinator;
import DTO.DTVaccinatorView;
import IDAL.IVaccinatorData;
import entities.Person;
import entities.Role;
import entities.VaccinationPost;
import entities.Vaccinator;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class VaccinatorData implements IVaccinatorData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public DTVaccinator saveVaccinator(DTVaccinator dtVaccinator) {
    Query query = data
      .createQuery("select p from Person p where p.id = :id", Person.class)
      .setParameter("id", dtVaccinator.getDtPerson().getId());
    Person person = (Person) query.getSingleResult();
    List<Role> listDtRoles = person.getRoles();

    for (Role role : listDtRoles) {
      if ((role.getName().equalsIgnoreCase("vaccinator")) && (role.getDeleteDate() != null)) {
        Vaccinator vaccinatorDeleted = data.find(Vaccinator.class, role.getId());
        vaccinatorDeleted.setDeleteDate(null);
        vaccinatorDeleted.setUpdateDate(new Date());
        data.merge(vaccinatorDeleted);
        return vaccinatorDeleted.getDTVaccinator();
      } else {
        if (role.getName().equalsIgnoreCase("vaccinator")) {
          Vaccinator vaccinatorExist = data.find(Vaccinator.class, role.getId());
          return vaccinatorExist.getDTVaccinator();
        }
      }
    }
    Vaccinator vaccinatorNew = new Vaccinator();
    vaccinatorNew.setName("Vaccinator");
    vaccinatorNew.setPerson(person);
    data.persist(vaccinatorNew);
    return vaccinatorNew.getDTVaccinator();
  }

  @Override
  public List<DTVaccinator> getVaccinators() {
    List<Vaccinator> vaccinatorList = data.createQuery("select v from Vaccinator v").getResultList();
    List<DTVaccinator> dtVaccinatorList = new ArrayList<DTVaccinator>();
    vaccinatorList.forEach(
      vaccinator -> {
        DTVaccinator dtVaccinator = vaccinator.getDTVaccinator();
        dtVaccinatorList.add(dtVaccinator);
      }
    );
    return dtVaccinatorList;
  }

  @Override
  public DTVaccinator getByIdVaccinator(Long id) {
    Vaccinator vaccinator = data.find(Vaccinator.class, id);
    DTVaccinator dtVaccinator = modelMapper.map(vaccinator, DTVaccinator.class);
    return dtVaccinator;
  }

  @Override
  public void deleteVaccinator(Long id) {
    Vaccinator vaccinator = data.find(Vaccinator.class, id);
    Date date = new Date();
    vaccinator.setDeleteDate(date);
    data.persist(vaccinator);
  }

  @Override
  public void setVCtoV(Long idVaccinator, DTVaccinationPost dtVaccinationPost) {
    try {
      Vaccinator vaccinator = data.find(Vaccinator.class, idVaccinator);
      VaccinationPost vaccinationPost = modelMapper.map(dtVaccinationPost, VaccinationPost.class);
      data.merge(vaccinator);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Override
  public List<DTVaccinatorView> getVaccinatorDatabyCi(String ci) {
    try {
      Vaccinator vaccinator = data
        .createQuery("select v from Vaccinator v where v.person.ci= :ci and v.deleteDate is null", Vaccinator.class)
        .setParameter("ci", ci)
        .getSingleResult();
      List<DTVaccinatorView> dtVaccinatorViews = new ArrayList<>();
      vaccinator
        .getAssignment()
        .forEach(
          ob -> {
            DTVaccinatorView dtVaccinatorView = new DTVaccinatorView();
            dtVaccinatorView.setScheduleId(ob.getSchedule().getId().toString());
            dtVaccinatorView.setVaccinationPlanId(ob.getSchedule().getVaccinationPlan().getId().toString());
            dtVaccinatorView.setVaccinationPlanName(ob.getSchedule().getVaccinationPlan().getName());
            dtVaccinatorView.setVaccinationCenterId(ob.getVaccinationPost().getVaccinationCenter().getId().toString());
            dtVaccinatorView.setVaccinationCenterName(ob.getVaccinationPost().getVaccinationCenter().getName());
            dtVaccinatorView.setVaccinationPostId(ob.getVaccinationPost().getId().toString());
            dtVaccinatorView.setVaccinationPostName(ob.getVaccinationPost().getName());
            dtVaccinatorView.setDateStart(ob.getStartDate());
            dtVaccinatorView.setDateEnd(ob.getEndDate());
            dtVaccinatorView.setTimeEnd(ob.getEndTime());
            dtVaccinatorView.setTimeStart(ob.getStartTime());
            dtVaccinatorView.setVaccineName(
              ob.getSchedule().getVaccinationPlan().getaPackage().stream().findFirst().get().getBatch().getVaccine().getName()
            );
            dtVaccinatorViews.add(dtVaccinatorView);
          }
        );
      return dtVaccinatorViews;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  @Override
  public DTVaccinator getVaccinatorByCi(String ci) {
    try {
      Vaccinator vaccinator = data
        .createQuery("select v from Vaccinator v where v.person.ci= :ci and v.deleteDate is null", Vaccinator.class)
        .setParameter("ci", ci)
        .getSingleResult();
      return modelMapper.map(vaccinator, DTVaccinator.class);
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
