package DAL;

import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPlan;
import DTO.DTVaccinationPlanMonitor;
import IController.IPackageController;
import IController.IRestrictionController;
import IController.IVaccinationCenterController;
import IDAL.IVaccinationPlanData;
import entities.Restriction;
import entities.VaccinationCenter;
import entities.VaccinationPlan;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class VaccinationPlanData implements IVaccinationPlanData {

  @Inject
  IVaccinationCenterController iVaccinationCenterController;

  @Inject
  IPackageController iPackageController;

  @Inject
  IRestrictionController iRestrictionController;

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public Long saveVaccinationPlan(DTVaccinationPlan dtVaccinationPlan, List<DTVaccinationCenter> dtVaccinationCenterList) {
    VaccinationPlan vaccinationPlan = modelMapper.map(dtVaccinationPlan, VaccinationPlan.class);
    List<VaccinationCenter> vaccinationCenterList = new ArrayList<>();

    if (dtVaccinationCenterList.size() != 0) {
      dtVaccinationCenterList.forEach(
        dtvaccinationCenter -> {
          VaccinationCenter vaccinationCenter = modelMapper.map(dtvaccinationCenter, VaccinationCenter.class);
          vaccinationCenterList.add(vaccinationCenter);
        }
      );
      vaccinationPlan.setVaccinationCenters(vaccinationCenterList);
    } else {
      System.out.println("Cero");
    }

    List<Restriction> restrictionList = new ArrayList<>();
    if (vaccinationPlan.getId() != null) {
      restrictionList = data.find(VaccinationPlan.class, vaccinationPlan.getId()).getRestriction();
    }
    if (dtVaccinationPlan.getRestriction() != null) {
      for (String sRest : dtVaccinationPlan.getRestriction()) {
        restrictionList.add(data.find(Restriction.class, Long.parseLong(sRest)));
      }
    }
    vaccinationPlan.setRestriction(restrictionList);

    if (vaccinationPlan.getId() == null) {
      vaccinationPlan = data.merge(vaccinationPlan);
      //      data.persist(vaccinationPlan);
    } else {
      Date crtDate = data.find(VaccinationPlan.class, vaccinationPlan.getId()).getCreateDate();
      vaccinationPlan.setCreateDate(crtDate);
      data.merge(vaccinationPlan);
    }

    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return vaccinationPlan.getId();
    //

  }

  @Override
  public DTVaccinationPlan getVaccinationPlanById(Long id) {
    DTVaccinationPlan dtVaccinationPlan = data.find(VaccinationPlan.class, id).getDTVaccinationPlan();
    return dtVaccinationPlan;
  }

  @Override
  public void deleteVaccinationPlan(Long id) {
    VaccinationPlan vaccinationPlan = data.find(VaccinationPlan.class, id);
    Date date = new Date();
    vaccinationPlan.setDeleteDate(date);
    data.persist(vaccinationPlan);
  }

  @Override
  public void unassignCenterOfPlan(Long centerId, Long planId) {
    VaccinationPlan vPlan = data.find(VaccinationPlan.class, planId);
    List<VaccinationCenter> vCenterList = vPlan.getVaccinationCenters();
    VaccinationCenter vCenterToRemove = new VaccinationCenter();
    for (VaccinationCenter vCenter : vCenterList) {
     // if (vCenter.getId() == centerId) vCenterToRemove = vCenter;
      if (vCenter.getId().equals(centerId)) {
        vCenterToRemove = vCenter;
      };
    }
    vCenterList.remove(vCenterToRemove);
    Date date = new Date();
    vPlan.setVaccinationCenters(vCenterList);
    vPlan.setUpdateDate(date);
    data.merge(vPlan);
  }

  @Override
  public List<DTVaccinationPlan> listVaccinationsPlans() {
    List<VaccinationPlan> vaccinationPlanList = data
      .createQuery("select v from VaccinationPlan v where v.deleteDate is null order by v.id ")
      .getResultList();
    List<DTVaccinationPlan> dtVaccinationPlanList = new ArrayList<DTVaccinationPlan>();
    vaccinationPlanList.forEach(
      vaccinationPlan -> {
        DTVaccinationPlan dtVaccinationPlan = vaccinationPlan.getDTVaccinationPlan();
        dtVaccinationPlanList.add(dtVaccinationPlan);
      }
    );
    return dtVaccinationPlanList;
  }

  @Override
  public List<DTVaccinationCenter> vaccinationCentersByVaccinationPlan(Long id) {
    List<VaccinationCenter> vaccinationCenterList = data
      .createQuery("select v.vaccinationCenters from VaccinationPlan v where v.id=:id and v.deleteDate is null")
      .setParameter("id", id)
      .getResultList();
    List<DTVaccinationCenter> dtVaccinationCenterList = new ArrayList<DTVaccinationCenter>();
    vaccinationCenterList.forEach(
      vaccinationCenter -> {
        DTVaccinationCenter dtVaccinationCenter = vaccinationCenter.getDTVaccinationCenter();
        dtVaccinationCenterList.add(dtVaccinationCenter);
      }
    );
    return dtVaccinationCenterList;
  }

  @Override
  public void addCenterToPlan(Long planId, Long centerId) {
    VaccinationPlan vaccinationPlan = data.find(VaccinationPlan.class, planId);
    List<VaccinationCenter> centerListToUpdate = new ArrayList<>();

    if (vaccinationPlan.getVaccinationCenters().size() > 0) {
      centerListToUpdate = vaccinationPlan.getVaccinationCenters();
      VaccinationCenter vc = data.find(VaccinationCenter.class, centerId);
      centerListToUpdate.add(vc);
    } else {
      centerListToUpdate.add(data.find(VaccinationCenter.class, centerId));
    }
    vaccinationPlan.setVaccinationCenters(centerListToUpdate);
    data.merge(vaccinationPlan);
  }

  @Override
  public void addRestrictionToPlan(Long planId, Long restrictionId) {
    VaccinationPlan vaccinationPlan = data.find(VaccinationPlan.class, planId);
    List<Restriction> restrictionListToUpdate = new ArrayList<>();

    if (vaccinationPlan.getRestriction().size() > 0) {
      restrictionListToUpdate = vaccinationPlan.getRestriction();
      Restriction rest = data.find(Restriction.class, restrictionId);
      restrictionListToUpdate.add(rest);
    } else {
      restrictionListToUpdate.add(data.find(Restriction.class, restrictionId));
    }
    vaccinationPlan.setRestriction(restrictionListToUpdate);
    data.merge(vaccinationPlan);
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public DTVaccinationPlanMonitor getDataMonVPlan(Long id) {
    VaccinationPlan vaccinationPlan = data.find(VaccinationPlan.class, id);
    DTVaccinationPlanMonitor vaccinationPlanMonitor = new DTVaccinationPlanMonitor();
    vaccinationPlanMonitor.setvPlanId(vaccinationPlan.getId());
    vaccinationPlanMonitor.setvPlanName(vaccinationPlan.getName());
    vaccinationPlanMonitor.setVaccineName(vaccinationPlan.getVaccine().getName());
    vaccinationPlanMonitor.setDiseaseName(vaccinationPlan.getVaccine().getDisease().getName());
    vaccinationPlanMonitor.setEndDate(vaccinationPlan.getEndDate());
    vaccinationPlanMonitor.setDoseQuantity(vaccinationPlan.getVaccine().getDoseQuantity());
    vaccinationPlanMonitor.setTotalDosesAsssigned(vaccinationPlan.getVaccineQuantity().toString());
    vaccinationPlanMonitor.setStartDate(vaccinationPlan.getStartDate());
    return vaccinationPlanMonitor;
  }
}
