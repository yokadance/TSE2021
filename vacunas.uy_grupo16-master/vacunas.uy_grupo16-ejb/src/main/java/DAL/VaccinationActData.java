package DAL;

import DTO.DTVaccinationAct;
import DTO.DTVaccinationActView;
import IDAL.IVaccinationActData;
import entities.VaccinationAct;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class VaccinationActData implements IVaccinationActData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public void saveVaccinationAct(DTVaccinationAct dtVaccinationAct) {
    VaccinationAct vaccinationAct = modelMapper.map(dtVaccinationAct, VaccinationAct.class);
    if (vaccinationAct.getId() == null) {
      data.persist(vaccinationAct);
    } else {
      vaccinationAct.setCreateDate(data.find(VaccinationAct.class, vaccinationAct.getId()).getCreateDate());
      data.merge(vaccinationAct);
    }
  }

  @Override
  public DTVaccinationAct getVaccinationActById(Long id) {
    DTVaccinationAct dtVaccinationAct = data.find(VaccinationAct.class, id).getDTVaccinationAct();
    return dtVaccinationAct;
  }

  @Override
  public void deleteVaccinationAct(Long id) {
    DTVaccinationAct dtVaccinationAct = getVaccinationActById(id);
    dtVaccinationAct.setDeleteDate(new Date());
    data.persist(dtVaccinationAct);
  }

  @Override
  public List<DTVaccinationAct> listVaccinationAct() {
    List<VaccinationAct> vaccinationActsList = data.createQuery("select v from VaccinationAct v").getResultList();
    List<DTVaccinationAct> dtVaccinationActList = new ArrayList<DTVaccinationAct>();
    vaccinationActsList.forEach(
      vaccinationAct -> {
        DTVaccinationAct dtVaccinationAct = vaccinationAct.getDTVaccinationAct();
        dtVaccinationActList.add(dtVaccinationAct);
      }
    );
    return dtVaccinationActList;
  }

  @Override
  public List<DTVaccinationActView> vaccinationActByCi(String ci) {
    List<VaccinationAct> vaccinationActList = data
      .createQuery("select v from VaccinationAct v where v.citizen.person.ci=:ci")
      .setParameter("ci", ci)
      .getResultList();
    List<DTVaccinationActView> dtVaccinationActViews = new ArrayList<>();
    vaccinationActList.forEach(
      vaccinationact -> {
        DTVaccinationActView dtVaccinationActView = new DTVaccinationActView();
        dtVaccinationActView.setPersonName(vaccinationact.getCitizen().getPerson().getName());
        dtVaccinationActView.setPersonLastName(vaccinationact.getCitizen().getPerson().getLastname());
        dtVaccinationActView.setPersonSurname(vaccinationact.getCitizen().getPerson().getSurname());
        dtVaccinationActView.setVaccinationCenter(vaccinationact.getVaccinationPost().getVaccinationCenter().getName());
        dtVaccinationActView.setVaccine(vaccinationact.getaPackage().getBatch().getVaccine().getName());
        dtVaccinationActView.setPersonCi(vaccinationact.getCitizen().getPerson().getCi());
        dtVaccinationActView.setPersonBirthday(vaccinationact.getCitizen().getPerson().getBirthday().toString());
        dtVaccinationActView.setVaccinationActDate(vaccinationact.getCreateDate().toString());
        dtVaccinationActView.setDisease(vaccinationact.getaPackage().getBatch().getVaccine().getDisease().getName());
        dtVaccinationActViews.add(dtVaccinationActView);
      }
    );
    return dtVaccinationActViews;
  }

    @Override
    public List<DTVaccinationActView> getVaccinationActsView() {
        List<VaccinationAct> vaccinationActList = data
                .createQuery("select v from VaccinationAct v ").getResultList();
        List<DTVaccinationActView> dtVaccinationActViews = new ArrayList<>();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        vaccinationActList.forEach(
                vaccinationact -> {
                    DTVaccinationActView dtVaccinationActView = new DTVaccinationActView();
                    dtVaccinationActView.setPersonName(vaccinationact.getCitizen().getPerson().getName());
                    dtVaccinationActView.setPersonLastName(vaccinationact.getCitizen().getPerson().getLastname());
                    dtVaccinationActView.setPersonSurname(vaccinationact.getCitizen().getPerson().getSurname());
                    dtVaccinationActView.setVaccinationCenter(vaccinationact.getVaccinationPost().getVaccinationCenter().getName());
                    dtVaccinationActView.setVaccine(vaccinationact.getaPackage().getBatch().getVaccine().getName());
                    dtVaccinationActView.setPersonCi(vaccinationact.getCitizen().getPerson().getCi());

                    Date birth = vaccinationact.getCitizen().getPerson().getBirthday();
                    String stringBirth = DateFor.format(birth);
                    dtVaccinationActView.setPersonBirthday(stringBirth);
//                    dtVaccinationActView.setPersonBirthday(vaccinationact.getCitizen().getPerson().getBirthday().toString());

                    Date today = vaccinationact.getCreateDate();
                    String stringDate = DateFor.format(today);
                    dtVaccinationActView.setVaccinationActDate(stringDate);
//                    dtVaccinationActView.setVaccinationActDate(vaccinationact.getCreateDate().toString());
                    dtVaccinationActView.setDisease(vaccinationact.getaPackage().getBatch().getVaccine().getDisease().getName());
                    dtVaccinationActViews.add(dtVaccinationActView);
                }
        );
        return dtVaccinationActViews;
    }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
