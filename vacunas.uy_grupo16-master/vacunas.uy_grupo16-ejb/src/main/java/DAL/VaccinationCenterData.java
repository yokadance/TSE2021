package DAL;

import DTO.DTVaccinationCenter;
import IController.IPackageController;
import IController.IVaccinationPlanController;
import IDAL.IVaccinationCenterData;
import entities.VaccinationCenter;
import entities.VaccinationPlan;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.modelmapper.ModelMapper;

@Singleton
public class VaccinationCenterData implements IVaccinationCenterData {

  @Inject
  IPackageController iPackageController;

  @Inject
  IVaccinationPlanController iVaccinationPlanController;

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public Long newVaccinationCenter(DTVaccinationCenter dtVaccinationCenter) {
    VaccinationCenter vaccinationCenter = modelMapper.map(dtVaccinationCenter, VaccinationCenter.class);
    data.persist(vaccinationCenter);
    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return vaccinationCenter.getId();
    //
  }

  @Override
  public Long saveVaccinationCenter(DTVaccinationCenter dtVaccinationCenter) {
    VaccinationCenter vaccinationCenter = modelMapper.map(dtVaccinationCenter, VaccinationCenter.class);
    List<VaccinationPlan> listPlansToAdd = new ArrayList<>();

    if (dtVaccinationCenter.getVaccinationPlans() != null && dtVaccinationCenter.getVaccinationPlans().size() > 0) {
      List<String> planList = dtVaccinationCenter.getVaccinationPlans();
      for (String vP : planList) {
        //        iVaccinationPlanController.addCenterToPlan(Long.parseLong(vP), dtVaccinationCenter.getId());
        VaccinationPlan vp = data.find(VaccinationPlan.class, Long.parseLong(vP));
        listPlansToAdd.add(vp);
      }
    }
    vaccinationCenter.setVaccinationPlans(listPlansToAdd);

    if (vaccinationCenter.getId() == null) {
      data.persist(vaccinationCenter);
    } else {
      Date newDate = new Date();
      vaccinationCenter.setCreateDate(data.find(VaccinationCenter.class, vaccinationCenter.getId()).getCreateDate());
      vaccinationCenter.setUpdateDate(newDate);
      data.merge(vaccinationCenter);
    }
    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return vaccinationCenter.getId();
    //
  }

  @Override
  public DTVaccinationCenter getVaccinationCenterById(Long id) {
    try {
      DTVaccinationCenter dtVaccinationCenter = data.find(VaccinationCenter.class, id).getDTVaccinationCenter();
      return dtVaccinationCenter;
    } catch (NoResultException nre) {
      return null;
    }
  }

  @Override
  public void deleteVaccinationCenter(Long id) {
    VaccinationCenter v = data.find(VaccinationCenter.class, id);
    Date date = new Date();
    v.setDeleteDate(date);
    data.merge(v);
  }

  @Override
  public void unassignVaccinationCenter(Long centerId, Long planId) {
    VaccinationCenter vCenter = data.find(VaccinationCenter.class, centerId);
    List<VaccinationPlan> vPlansList = vCenter.getVaccinationPlans();
    VaccinationPlan vPlanToRemove = new VaccinationPlan();
    for (VaccinationPlan vPlan : vPlansList) {
      if (vPlan.getId().equals(planId)) //if(vPlan.getId() == planId)
      vPlanToRemove = vPlan;
    }
    vPlansList.remove(vPlanToRemove);
    vCenter.setVaccinationPlans(vPlansList);
    Date date = new Date();
    vCenter.setUpdateDate(date);
    iVaccinationPlanController.unassignCenterOfPlan(centerId, planId);
    data.merge(vCenter);
  }

  @Override
  public List<DTVaccinationCenter> listVaccinationsCenters() {
    List<VaccinationCenter> vaccinationsCentersList = data
      .createQuery("select v from VaccinationCenter v where v.deleteDate is null")
      .getResultList();
    List<DTVaccinationCenter> dtVaccinationsCentersList = new ArrayList<DTVaccinationCenter>();
    vaccinationsCentersList.forEach(
      vaccinationCenter -> {
        DTVaccinationCenter dtVaccinationCenter = vaccinationCenter.getDTVaccinationCenter();
        dtVaccinationsCentersList.add(dtVaccinationCenter);
      }
    );
    return dtVaccinationsCentersList;
  }

  @Override
  public List<DTVaccinationCenter> getCentersByPlan(Long idVP) {
    Query query = data.createQuery("select c from VaccinationCenter c where c.deleteDate is null");
    List<VaccinationCenter> vaccinationCenterList = query.getResultList();
    List<DTVaccinationCenter> dtVaccinationCenterList = new ArrayList<>();

    if (vaccinationCenterList.size() > 0) {
      for (VaccinationCenter vacCenter : vaccinationCenterList) {
        if (vacCenter.getVaccinationPlans().size() > 0) {
          List<VaccinationPlan> vacPlanList = vacCenter.getVaccinationPlans();
          for (VaccinationPlan vacPlan : vacPlanList) {
            // if (vacPlan.getId() == idVP) dtVaccinationCenterList.add(vacCenter.getDTVaccinationCenter());
            if (vacPlan.getId().equals(idVP)) dtVaccinationCenterList.add(vacCenter.getDTVaccinationCenter());
          }
        }
      }
    }
    return dtVaccinationCenterList;
  }

  @Override
  public List<DTVaccinationCenter> getCentersAvailablesByPlan(Long idVP) {
    Query query = data.createQuery("select c from VaccinationCenter c where c.deleteDate is null");
    List<VaccinationCenter> vaccinationCenterList = query.getResultList();
    //    List<DTVaccinationCenter> dtVaccinationCenterList = new ArrayList<>();
    Map<Long, DTVaccinationCenter> dtVacCenterMap = new HashMap();

    if (vaccinationCenterList.size() > 0) {
      for (VaccinationCenter vacCenter : vaccinationCenterList) {
        //SI NO TIENE PLAN LO AGREGO
        if (vacCenter.getVaccinationPlans() == null || vacCenter.getVaccinationPlans().size() == 0) dtVacCenterMap.putIfAbsent(
          vacCenter.getId(),
          vacCenter.getDTVaccinationCenter()
        );
        //SI TIENE PLANES AGREGO LOS Q NO ESTAN EN ESTE PLAN
        if (vacCenter.getVaccinationPlans().size() > 0) {
          List<VaccinationPlan> vacPlanList = vacCenter.getVaccinationPlans();
          for (VaccinationPlan vacPlan : vacPlanList) {
            Long vpId = vacPlan.getId();
            //vacPlan.getId() != idVP
            if (!vpId.equals(idVP)) dtVacCenterMap.putIfAbsent(vacCenter.getId(), vacCenter.getDTVaccinationCenter()); //              dtVaccinationCenterList.add(vacCenter.getDTVaccinationCenter());
          }
        }
      }
    }
    List<DTVaccinationCenter> vacCenterOfPlanList = getCentersByPlan(idVP);
    if (vacCenterOfPlanList.size() > 0) {
      for (DTVaccinationCenter vacCenterofPlan : vacCenterOfPlanList) {
        dtVacCenterMap.remove(vacCenterofPlan.getId());
      }
    }

    List<DTVaccinationCenter> dtVaccinationCenterList = new ArrayList<DTVaccinationCenter>(dtVacCenterMap.values());
    return dtVaccinationCenterList;
  }

  public String vaccinationCenterPassword() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);

    SecureRandom random2 = new SecureRandom();
    byte[] pass = new byte[16];
    random.nextBytes(salt);
    //String pass1 = pass.toString();
    String pass1 = Arrays.toString(pass);

    String generatedPassword = null;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(salt);
      byte[] bytes = md.digest(pass1.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }

  public boolean validatePassword(Long vId, String password2check) {
    DTVaccinationCenter DTVaccinationCenter = getVaccinationCenterById(vId);
    if (DTVaccinationCenter.getPassword().equals(password2check)) {
      return true;
    } else return false;
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public void setiVaccinationPlanController(IVaccinationPlanController iVaccinationPlanController) {
    this.iVaccinationPlanController = iVaccinationPlanController;
  }
}
