package Controller;

import DTO.DTLogisticPartner;
import DTO.DTPackage;
import IController.ILogisticPartnerController;
import IDAL.ILogisticPartnerData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class LogisticPartnerController implements ILogisticPartnerController {

  @EJB
  ILogisticPartnerData iLogisticPartnerData;

  @Override
  public Long saveLogisticPartner(DTLogisticPartner dtLPartner) {
    return iLogisticPartnerData.saveLogisticPartner(dtLPartner);
  }

  @Override
  public DTLogisticPartner getLogisticPartnerById(Long id) {
    return iLogisticPartnerData.getLogisticPartnerById(id);
  }

  @Override
  public void deleteLogisticPartner(Long id) {
    iLogisticPartnerData.deleteLogisticPartner(id);
  }

  @Override
  public List<DTLogisticPartner> listLogisticPartners() {
    return iLogisticPartnerData.listLogisticPartners();
  }

  @Override
  public List<DTLogisticPartner> getLogisticParterByName(String name) {
    return iLogisticPartnerData.getLogisticParterByName(name);
  }

  public void setiLogisticPartnerData(ILogisticPartnerData iLogisticPartnerData) {
    this.iLogisticPartnerData = iLogisticPartnerData;
  }

  @Override
  public void removePackageFromPartner(Long partnerId, Long packageId) {iLogisticPartnerData.removePackageFromPartner(partnerId, packageId);}

  @Override
  public List<DTPackage> getPackagesFromPartner(Long partnerId){ return iLogisticPartnerData.getPackagesFromPartner(partnerId);}

  @Override
  public List<DTPackage> getAvailablePackagesToAdd(){ return iLogisticPartnerData.getAvailablePackagesToAdd();}
}
