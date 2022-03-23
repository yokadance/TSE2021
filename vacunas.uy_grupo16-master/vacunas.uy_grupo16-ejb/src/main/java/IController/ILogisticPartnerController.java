package IController;

import DTO.DTLogisticPartner;
import DTO.DTPackage;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ILogisticPartnerController {
  Long saveLogisticPartner(DTLogisticPartner dtLPartner);
  DTLogisticPartner getLogisticPartnerById(Long id);
  void deleteLogisticPartner(Long id);
  List<DTLogisticPartner> listLogisticPartners();
  List<DTLogisticPartner> getLogisticParterByName(String name);
  void removePackageFromPartner(Long partnerId, Long packageId);
  List<DTPackage> getPackagesFromPartner(Long partnerId);
  List<DTPackage> getAvailablePackagesToAdd();
}
