package Controller;

import DTO.DTPackage;
import DTO.DTSendPendingPackage;
import IController.IBatchController;
import IController.IPackageController;
import IController.IVaccinationCenterController;
import IController.IVaccinationPlanController;
import IDAL.IPackageData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class PackageController implements IPackageController {

  @Inject
  IVaccinationCenterController iVaccinationCenterController;

  @Inject
  IVaccinationPlanController iVaccinationPlanController;

  @Inject
  IBatchController iBatchController;

  @EJB
  IPackageData iPackageData;

  public Long savePackage(DTPackage dtPackage) {
    return iPackageData.savePackage(dtPackage, iBatchController.getByIdBatch(Long.parseLong(dtPackage.getBatch())));
  }

  public DTPackage getByIdPackage(Long id) {
    return iPackageData.getByIdPackage(id);
  }

  public List<DTPackage> getPackages() {
    return iPackageData.getPackages();
  }

  public List<DTPackage> getPackagesAvailable(){ return iPackageData.getPackagesAvailable(); }

  public List<DTPackage> getPackagesAvailableForPlan(Long planId){ return iPackageData.getPackagesAvailableForPlan(planId); }

  public List<DTPackage> getPackagesByPlanAndCenter(Long planId, Long centerId){ return iPackageData.getPackagesByPlanAndCenter(planId, centerId); }

  public void deletePackage(Long id) {
    iPackageData.deletePackage(id);
  }

  public void unassignPackageFromCenterAndPlan(Long id){ iPackageData.unassignPackageFromCenterAndPlan(id);}

  @Override
  public boolean addPackagetoVC(Long idPackage, Long idVaccinationCenter) {
    return iPackageData.addPackagetoVC(idPackage, iVaccinationCenterController.getVaccinationCenterById(idVaccinationCenter));
  }

  @Override
  public boolean addPackagetoVP(Long idPackage, Long idVaccinationPlan) {
    return iPackageData.addPackagetoVP(idPackage, iVaccinationPlanController.getVaccinationPlanById(idVaccinationPlan));
  }

  @Override
  public List<DTPackage> getPackagesByBatchId(Long batchId) {
    return iPackageData.getPackagesByBatchId(batchId);
  }

  @Override
  public List<DTSendPendingPackage> getPendingPackages(Long id) {
    return iPackageData.getPendingPackages(id);
  }

  @Override
  public void packageToDelivered(Long idPackage) {
    iPackageData.packageToDelivered(idPackage);
  }

  public void packageToInTransit(Long idPackage) {
    iPackageData.packageToInTransit(idPackage);
  }

  public void discountPackageQuantity(Long idPackage) {
    iPackageData.discountPackageQuantity(idPackage);
  }

  public void setiVaccinationCenterController(IVaccinationCenterController iVaccinationCenterController) {
    this.iVaccinationCenterController = iVaccinationCenterController;
  }

  public void setiVaccinationPlanController(IVaccinationPlanController iVaccinationPlanController) {
    this.iVaccinationPlanController = iVaccinationPlanController;
  }

  public void setiBatchController(IBatchController iBatchController) {
    this.iBatchController = iBatchController;
  }

  public void setiPackageData(IPackageData iPackageData) {
    this.iPackageData = iPackageData;
  }
}
