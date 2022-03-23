package IController;

import DTO.DTPackage;
import DTO.DTSendPendingPackage;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPackageController {
  Long savePackage(DTPackage dtPackage);
  DTPackage getByIdPackage(Long id);
  List<DTPackage> getPackages();
  List<DTPackage> getPackagesAvailable();
  List<DTPackage> getPackagesAvailableForPlan(Long planId);
  List<DTPackage> getPackagesByPlanAndCenter(Long planId, Long centerId);
  void deletePackage(Long id);
  void unassignPackageFromCenterAndPlan(Long id);
  boolean addPackagetoVC(Long idPackage, Long idVaccinationCenter);
  boolean addPackagetoVP(Long idPackage, Long idVaccinationPlan);
  List<DTPackage> getPackagesByBatchId(Long batchId);
  public List<DTSendPendingPackage> getPendingPackages(Long id);
  public void packageToDelivered (Long idPackage);
  public void packageToInTransit (Long idPackage);
  public void discountPackageQuantity (Long idPackage);
}
