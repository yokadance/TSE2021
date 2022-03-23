package DAL;

import DTO.DTVaccination;
import IDAL.IVaccinationData;
import entities.Vaccination;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class VaccinationData implements IVaccinationData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public void saveVaccination(DTVaccination dtVaccination) {
    Vaccination vaccination = modelMapper.map(dtVaccination, Vaccination.class);
    data.persist(vaccination);
  }

  @Override
  public DTVaccination getVaccinationById(Long id) {
    DTVaccination dtVaccination = data.find(Vaccination.class, id).getDTVaccination();
    return dtVaccination;
  }

  @Override
  public void deleteVaccination(Long id) {
    DTVaccination vaccination = getVaccinationById(id);
    vaccination.setDeleteDate(new Date());
    data.persist(vaccination);
  }

  @Override
  public List<DTVaccination> listVaccinations() {
    List<Vaccination> vaccinationsList = data.createQuery("select v from Vaccination v").getResultList();
    List<DTVaccination> dtVaccinationsList = new ArrayList<DTVaccination>();
    vaccinationsList.forEach(
      vaccination -> {
        DTVaccination dtVaccination = vaccination.getDTVaccination();
        dtVaccinationsList.add(dtVaccination);
      }
    );
    return dtVaccinationsList;
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
