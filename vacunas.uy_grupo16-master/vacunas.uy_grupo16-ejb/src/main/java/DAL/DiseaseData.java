package DAL;

import DTO.DTDisease;
import DTO.DTVaccine;
import IDAL.IDiseaseData;
import IDAL.IVaccineData;
import entities.Disease;
import entities.Vaccine;
import org.modelmapper.ModelMapper;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class DiseaseData implements IDiseaseData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @EJB
  IVaccineData iVaccineData;

  @Override
  public Long saveDisease(DTDisease dtDisease) {
    Disease disease = modelMapper.map(dtDisease, Disease.class);
    if (disease.getId() == null) {
      disease.setCreateDate(new Date());
      data.persist(disease);
    } else {
      disease.setCreateDate(data.find(Disease.class, disease.getId()).getCreateDate());
      disease.setUpdateDate(new Date());
      data.merge(disease);
    }

    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return disease.getId();
    //

  }

  @Override
  public DTDisease getDiseaseById(Long id) {
    try {
      DTDisease dtDisease = data.find(Disease.class, id).getDTDisease();
      return dtDisease;
    } catch (NoResultException nre) {
      return null;
    }
  }

  @Override
  public void deleteDisease(Long id) {
    DTDisease dtDisease = getDiseaseById(id);

    Disease disease = modelMapper.map(dtDisease, Disease.class);

    if (disease.getId() != null) {
      disease.setDeleteDate(new Date());
      data.merge(disease);
    }
  }

  @Override
  public List<DTDisease> listDiseases() {
    List<Disease> diseasesList = data.createQuery("select v from Disease v where v.deleteDate is null").getResultList();
    List<DTDisease> dtDiseasesList = new ArrayList<DTDisease>();
    diseasesList.forEach(
      diseases -> {
        DTDisease dtDisease = diseases.getDTDisease();
        dtDiseasesList.add(dtDisease);
      }
    );
    return dtDiseasesList;
  }

  @Override
  public List<DTDisease> getDiseaseByName(String name) {
    name = '%' + name + '%';
    Query query = data
      .createQuery("select v from Disease v where v.deleteDate is null and LOWER(v.name) like LOWER(:name)")
      .setParameter("name", name);
    List<Disease> diseaseList = query.getResultList();

    List<DTDisease> listDtDis = new ArrayList<>();

    if (diseaseList != null) {
      for (Disease d : diseaseList) {
        listDtDis.add(d.getDTDisease());
      }
    }

    return listDtDis;
  }

  @Override
  public void addVaccineToDisease(Long diseaseId, DTVaccine dtVaccine) {
    Vaccine vaccine = modelMapper.map(dtVaccine, Vaccine.class);
    Disease disease = data.find(Disease.class, diseaseId);

    if (disease != null) {
      List<Vaccine> vaccineList = disease.getVaccine();
      vaccineList.add(vaccine);
      disease.setUpdateDate(new Date());
      disease.setVaccine(vaccineList);
      data.merge(disease);
    }
  }

  public Long getDiseaseIdByName(String name) {
    List<DTDisease> list = getDiseaseByName(name);
    Long id = null;
    for (DTDisease dt : list) {
      id = dt.getId();
    }
    return id;
  }

  @Override
  public boolean checkDiseaseNameAvailability(String disName){
    boolean res = true;
    List<Disease> disList = data.createQuery("select d from Disease d where LOWER(d.name) like LOWER(:disName)")
            .setParameter("disName", disName).getResultList();

    if(disList.size() != 0)
      res = false;

    return res;
  }


  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
