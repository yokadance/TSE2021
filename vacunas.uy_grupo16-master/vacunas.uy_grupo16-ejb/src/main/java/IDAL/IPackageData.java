package IDAL;

import DTO.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPackageData {
  Long savePackage(DTPackage dtPackage, DTBatch dtBatch);
  void deletePackage(Long id);
  void unassignPackageFromCenterAndPlan(Long id);
  DTPackage getByIdPackage(Long id);
  List<DTPackage> getPackages();
  List<DTPackage> getPackagesAvailable();

  List<DTPackage> getPackagesAvailableForPlan(Long planId);

  List<DTPackage> getPackagesByPlanAndCenter(Long planId, Long centerId);

  boolean addPackagetoVC(Long idPackage, DTVaccinationCenter dtVaccinationCenter);

  boolean addPackagetoVP(Long idPackage, DTVaccinationPlan dtVaccinationPlan);

  List<DTPackage> getPackagesByBatchId(Long batchId);

  List<DTSendPendingPackage> getPendingPackages(Long id);

  public void packageToDelivered (Long idPackage);

  public void packageToInTransit (Long idPackage);

  public void discountPackageQuantity (Long idPackage);
}
