package DAL;

import DTO.DTLaboratory;
import DTO.DTVaccine;
import IDAL.ILaboratoryData;
import entities.Laboratory;
import entities.Vaccine;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class LaboratoryData implements ILaboratoryData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  public Long saveLaboratory(DTLaboratory dtLab) {
    Laboratory laboratory = modelMapper.map(dtLab, Laboratory.class);
    String str = String.valueOf(laboratory.getId());
    switch (str) {
      case "null":
      case "0":
        laboratory.setCreateDate(new Date());
        return data.merge(laboratory).getId();
      default:
        laboratory.setCreateDate(data.find(Laboratory.class, laboratory.getId()).getCreateDate());
        laboratory.setUpdateDate(new Date());
        return data.merge(laboratory).getId();
    }
  }

  public DTLaboratory getLaboratoryById(Long id) {
    try {
      DTLaboratory dtLab = data.find(Laboratory.class, id).getDTLaboratory();
      return dtLab;
    } catch (NoResultException nre) {
      return null;
    }
  }

  public List<DTLaboratory> listLaboratorys() {
    List<Laboratory> laboratories = data.createQuery("select l from Laboratory l where l.deleteDate is null").getResultList();
    List<DTLaboratory> dtLaboratories = new ArrayList<>();
    List<DTVaccine> vacDtList = new ArrayList<>();
    List<Vaccine> vacList = new ArrayList<>();

    for (Laboratory lab : laboratories) {
      vacList = lab.getVaccine();

      if (vacList != null) {
        for (Vaccine vac : vacList) {
          vacDtList.add(vac.getDTVaccine());
        }
      }
      DTLaboratory dtl = new DTLaboratory(
        lab.getId(),
        lab.getCreateDate(),
        lab.getUpdateDate(),
        lab.getDeleteDate(),
        lab.getUserid(),
        lab.getName(),
        lab.getOrigin(),
        lab.getEmail(),
        lab.getPhone(),
        vacDtList
      );
      dtLaboratories.add(dtl);
      //vacDtList.removeAll(vacDtList);
      vacDtList.clear();
    }

    return dtLaboratories;
  }

  public void deleteLaboratory(Long id) {
    Laboratory lab = data.find(Laboratory.class, id);
    Date date = new Date();
    lab.setDeleteDate(date);
    data.persist(lab);
  }

  public List<DTLaboratory> getLaboratoryByName(String name) {
    name = '%' + name + '%';
    Query query = data
      .createQuery("select v from Laboratory v where v.deleteDate is null and LOWER(v.name) like LOWER(:name)")
      .setParameter("name", name);
    List<Laboratory> labList = query.getResultList();

    List<DTLaboratory> listDtLab = new ArrayList<>();

    if (labList != null) {
      for (Laboratory d : labList) {
        listDtLab.add(d.getDTLaboratory());
      }
    }

    return listDtLab;
  }

  public Long getLaboratoryIdByName(String name) {
    List<DTLaboratory> list = getLaboratoryByName(name);
    Long id = null;
    for (DTLaboratory dt : list) {
      id = dt.getId();
    }
    return id;
  }

  @Override
  public boolean checkLaboratoryNameAvailability(String labName){
    boolean res = true;
    List<Laboratory> labList = data.createQuery("select l from Laboratory l where LOWER(l.name) like LOWER(:labName)")
            .setParameter("labName", labName).getResultList();

    if(labList.size() != 0)
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
