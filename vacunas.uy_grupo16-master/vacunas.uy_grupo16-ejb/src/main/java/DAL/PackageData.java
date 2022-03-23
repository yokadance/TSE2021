package DAL;

import DTO.*;
import IDAL.IPackageData;
import entities.Package;
import entities.*;
import enumerations.PackageState;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class PackageData implements IPackageData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public Long savePackage(DTPackage dtPackage, DTBatch dtBatch) {
    Package aPackage = modelMapper.map(dtPackage, Package.class);

    if (dtBatch != null) {
      Batch batch = modelMapper.map(dtBatch, Batch.class);
      aPackage.setBatch(batch);
    }

    if (dtPackage.getVaccinationCenter() != null) {
      VaccinationCenter vacCenter = data.find(VaccinationCenter.class, Long.parseLong(dtPackage.getVaccinationCenter()));
      aPackage.setVaccinationCenter(vacCenter);
    }

    if (dtPackage.getVaccinationPlan() != null) {
      VaccinationPlan vacPlan = data.find(VaccinationPlan.class, Long.parseLong(dtPackage.getVaccinationPlan()));
      aPackage.setVaccinationPlan(vacPlan);
    }

    if (aPackage.getId() == null) {
      data.persist(aPackage);
    } else {
      aPackage.setCreateDate(data.find(Package.class, aPackage.getId()).getCreateDate());
      data.merge(aPackage);
    }

    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return aPackage.getId();
    //

  }

  @Override
  public void deletePackage(Long id) {
    Package aPackage = data.find(Package.class, id);
    Date date = new Date();
    aPackage.setDeleteDate(date);
    data.persist(aPackage);
  }

  @Override
  public void unassignPackageFromCenterAndPlan(Long id) {
    Package aPackage = data.find(Package.class, id);
    aPackage.setVaccinationCenter(null);
    aPackage.setVaccinationPlan(null);
    Date date = new Date();
    aPackage.setUpdateDate(date);
    data.merge(aPackage);
  }

  @Override
  public DTPackage getByIdPackage(Long id) {
    //    return data.find(Package.class, id).getDTPackage();
    Package pack = data.find(Package.class, id);
    DTPackage dtPack = pack.getDTPackage();
    dtPack.setBatch(pack.getBatch().getId().toString());
    return dtPack;
  }

  @Override
  public List<DTPackage> getPackages() {
    List<Package> packageList = data.createQuery("select p from Package p").getResultList();
    List<DTPackage> dtPackageList = new ArrayList<DTPackage>();
    packageList.forEach(
      packages -> {
        DTPackage dtPackage = packages.getDTPackage();
        dtPackageList.add(dtPackage);
      }
    );
    return dtPackageList;
  }

  @Override
  public List<DTPackage> getPackagesAvailable() {
    List<Package> packageList = data.createQuery("select p from Package p where p.deleteDate is null ").getResultList();
    List<DTPackage> dtPackageList = new ArrayList<DTPackage>();
    packageList.forEach(
      packages -> {
        if (packages.getVaccinationCenter() == null && packages.getVaccinationPlan() == null) {
          DTPackage dtPackage = packages.getDTPackage();
          dtPackageList.add(dtPackage);
        }
      }
    );
    return dtPackageList;
  }

  @Override
  public List<DTPackage> getPackagesAvailableForPlan(Long planId) {
    List<Package> packageList = data.createQuery("select p from Package p where p.deleteDate is null ").getResultList();
    List<DTPackage> dtPackageList = new ArrayList<DTPackage>();
    VaccinationPlan vacPlan = data.find(VaccinationPlan.class, planId);
    Long vaccineId = vacPlan.getVaccine().getId();

    packageList.forEach(
      packages -> {
        if (
          packages.getVaccinationCenter() == null &&
          packages.getVaccinationPlan() == null &&
          packages.getPackageState() == PackageState.Pending && packages.getQuantity() > 0
        ) {
          //if (vaccineId == packages.getBatch().getVaccine().getId()) {
          if (vaccineId.equals( packages.getBatch().getVaccine().getId())) {
            DTPackage dtPackage = packages.getDTPackage();
            dtPackageList.add(dtPackage);
          }
        }
      }
    );
    return dtPackageList;
  }

  @Override
  public List<DTPackage> getPackagesByPlanAndCenter(Long planId, Long centerId) {
    List<Package> packageList = data
      .createQuery(
        "select p from Package p where p.vaccinationPlan.id=:planId and p.vaccinationCenter.id=:centerId and p.deleteDate is null"
      )
      .setParameter("planId", planId)
      .setParameter("centerId", centerId)
      .getResultList();
    List<DTPackage> dtPackageList = new ArrayList<DTPackage>();
    packageList.forEach(
      packages -> {
        DTPackage dtPackage = packages.getDTPackage();
        dtPackageList.add(dtPackage);
      }
    );
    return dtPackageList;
  }

  @Override
  public boolean addPackagetoVC(Long idPackage, DTVaccinationCenter dtVaccinationCenter) {
    try {
      Package aPackage = data.find(Package.class, idPackage);
      VaccinationCenter vaccinationCenter = modelMapper.map(dtVaccinationCenter, VaccinationCenter.class);
      aPackage.setVaccinationCenter(vaccinationCenter);
      data.merge(aPackage);
      return true;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  @Override
  public boolean addPackagetoVP(Long idPackage, DTVaccinationPlan dtVaccinationPlan) {
    try {
      Package aPackage = data.find(Package.class, idPackage);
      VaccinationPlan vaccinationPlan = modelMapper.map(dtVaccinationPlan, VaccinationPlan.class);
      aPackage.setVaccinationPlan(vaccinationPlan);
      data.merge(aPackage);
      return true;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  @Override
  public List<DTPackage> getPackagesByBatchId(Long batchId) {
    List<Package> packageList = data
      .createQuery("select p from Package p where p.batch.id=:batchId and p.deleteDate is null")
      .setParameter("batchId", batchId)
      .getResultList();

    List<DTPackage> dtPackageList = new ArrayList<DTPackage>();
    packageList.forEach(
      pack -> {
        DTPackage dtPackage = pack.getDTPackage();
        dtPackageList.add(dtPackage);
      }
    );

    return dtPackageList;
  }

  @Override
  public List<DTSendPendingPackage> getPendingPackages(Long id) {

    LogisticPartner logisticPartner = data.find(LogisticPartner.class, id);
    List <Package> lista = logisticPartner.getPackages();

    /*List<Package> listObject = data.createQuery("select p from Package as p where p.packageState= 0 and p.vaccinationCenter.id =:id ")
            .setParameter("id", id)
            .getResultList();*/

    /*List<Package> listObject = data.createQuery("select p from Package as p where p.packageState= 0 and p.vaccinationCenter.id =:id ")
            .setParameter("id", id)
            .getResultList(); */

    List<DTSendPendingPackage> sendList = new ArrayList<>();
    lista.forEach(
      ob -> {
        if (ob.getPackageState().getCode() == 0) {
          DTSendPendingPackage dtSendPendingPackage = new DTSendPendingPackage();
          dtSendPendingPackage.setPackageId(ob.getId());
          dtSendPendingPackage.setPackageStatus(ob.getPackageState());
          sendList.add(dtSendPendingPackage);
        }
      }
    );

    return sendList;
  }

  @Override
  public void packageToDelivered(Long idPackage) {
    Package aPackage = data.find(Package.class, idPackage);
    if (aPackage.getId() != null) {
      aPackage.setPackageState(PackageState.Delivered);
      data.merge(aPackage);
    }
  }

  @Override
  public void packageToInTransit(Long idPackage) {
    Package aPackage = data.find(Package.class, idPackage);
    if (aPackage.getId() != null) {
      aPackage.setPackageState(PackageState.InTransit);
      data.merge(aPackage);
    }
  }

  @Override
  public void discountPackageQuantity(Long idPackage) {
    Package aPackage = data.find(Package.class, idPackage);
    if (aPackage.getId() != null) {
      aPackage.setQuantity(aPackage.getQuantity() - 1);
      data.merge(aPackage);
    }
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
