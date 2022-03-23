package DAL;

import DTO.DTLogisticPartner;
import DTO.DTPackage;
import IController.IPackageController;
import IDAL.ILogisticPartnerData;
import entities.LogisticPartner;
import entities.Package;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.modelmapper.ModelMapper;

@Singleton
public class LogisticPartnerData implements ILogisticPartnerData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @EJB
  ILogisticPartnerData iLogisticPartnerData;

  @Inject
  IPackageController iPackageController;

  @Override
  public Long saveLogisticPartner(DTLogisticPartner dtLPartner) {
    LogisticPartner partner = modelMapper.map(dtLPartner, LogisticPartner.class);
    if (partner.getId() == null) {
      partner.setCreateDate(new Date());
      data.persist(partner);
    } else {
      partner.setCreateDate(data.find(LogisticPartner.class, partner.getId()).getCreateDate());
      partner.setUpdateDate(new Date());
      data.merge(partner);
    }

    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return partner.getId();
    //

  }

  @Override
  public DTLogisticPartner getLogisticPartnerById(Long id) {
    try {
      DTLogisticPartner dtLPartner = data.find(LogisticPartner.class, id).getDTLogisticPartner();
      return dtLPartner;
    } catch (NoResultException nre) {
      return null;
    }
  }

  @Override
  public void deleteLogisticPartner(Long id) {
    LogisticPartner logPartner = data.find(LogisticPartner.class, id);
    logPartner.setDeleteDate(new Date());
    data.merge(logPartner);
  }

  @Override
  public List<DTLogisticPartner> listLogisticPartners() {
    List<LogisticPartner> logisticPartnerList = data
      .createQuery("select l from LogisticPartner l where l.deleteDate is null")
      .getResultList();
    List<DTLogisticPartner> dtlogisticPartnerList = new ArrayList<>();
    logisticPartnerList.forEach(
      partners -> {
        DTLogisticPartner dtLogPartner = partners.getDTLogisticPartner();
        dtlogisticPartnerList.add(dtLogPartner);
      }
    );
    return dtlogisticPartnerList;
  }

  @Override
  public List<DTLogisticPartner> getLogisticParterByName(String name) {
    name = '%' + name + '%';
    Query query = data
      .createQuery("select l from LogisticPartner l where l.deleteDate is null and LOWER(l.name) like LOWER(:name)")
      .setParameter("name", name);
    List<LogisticPartner> partnerList = query.getResultList();

    List<DTLogisticPartner> listDtPartner = new ArrayList<>();

    if (partnerList != null) {
      for (LogisticPartner logP : partnerList) {
        listDtPartner.add(logP.getDTLogisticPartner());
      }
    }

    return listDtPartner;
  }

  @Override
  public void removePackageFromPartner(Long partnerId, Long packageId) {
    LogisticPartner logParner = data.find(LogisticPartner.class, partnerId);
    DTLogisticPartner dtLogParner = logParner.getDTLogisticPartner();

    List<DTPackage> dtPackageList = new ArrayList<>();

    if (logParner.getPackages() != null) {
      List<Package> packageList = logParner.getPackages();
      for (Package pkg : packageList) {
        Long pkId = pkg.getId();
        // if(pkId != packageId)
        if (!pkId.equals(packageId)) dtPackageList.add(pkg.getDTPackage());
      }
    }
    dtLogParner.setPackages(dtPackageList);
    saveLogisticPartner(dtLogParner);
  }

  @Override
  public List<DTPackage> getPackagesFromPartner(Long partnerId) {
    LogisticPartner logPartner = data.find(LogisticPartner.class, partnerId);
    List<DTPackage> dtListPackages = new ArrayList<>();
    if (logPartner.getPackages() != null) {
      List<Package> listPackages = logPartner.getPackages();
      for (Package pkg : listPackages) {
        dtListPackages.add(pkg.getDTPackage());
      }
    }
    return dtListPackages;
  }

  @Override
  public List<DTPackage> getAvailablePackagesToAdd() {
    List<LogisticPartner> listPartners = data.createQuery("select l from LogisticPartner l where l.deleteDate is null").getResultList();
    List<DTPackage> listDtPackage = iPackageController.getPackages();
    HashMap<Long, DTPackage> mapPackages = new HashMap<Long, DTPackage>();

    for (DTPackage dtP : listDtPackage) {
      mapPackages.put(dtP.getId(), dtP);
    }

    for (LogisticPartner LP : listPartners) {
      if (LP.getPackages() != null) {
        List<Package> listP = LP.getPackages();
        for (Package pack : listP) {
          try {
            mapPackages.remove(pack.getId());
          } catch (Exception e) {}
        }
      }
    }
    ArrayList<DTPackage> resList = new ArrayList<DTPackage>(mapPackages.values());
    return resList;
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public void setiPackageController(IPackageController iPackageController) {
    this.iPackageController = iPackageController;
  }
}
