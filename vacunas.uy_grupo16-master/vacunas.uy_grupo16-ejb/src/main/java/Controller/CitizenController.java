package Controller;

import DTO.DTCitizen;
import IController.ICitizenController;
import IDAL.ICitizenData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class CitizenController implements ICitizenController {

  @EJB
  ICitizenData iCitizenData;

  @Override
  public void saveCitizen(DTCitizen dtCitizen) {
    iCitizenData.saveCitizen(dtCitizen);
  }

  @Override
  public DTCitizen getByIdCitizen(Long id) {
    return iCitizenData.getByIdCitizen(id);
  }

  @Override
  public List<DTCitizen> getCitizens() {
    return iCitizenData.getCitizens();
  }

  @Override
  public void deleteCitizen(Long id) {
    iCitizenData.deleteCitizen(id);
  }

  @Override
  public int getCitizenIdByCi(String ci) {
    return iCitizenData.getCitizenIdByCi(ci);
  }

  @Override
  public DTCitizen getCitizenByCi(String ci) {
    return iCitizenData.getCitizenByCi(ci);
  }

  @Override
  public boolean setToken(String ci, String token) {
    return iCitizenData.setToken(ci, token);
  }

  public void setiCitizenData(ICitizenData iCitizenData) {
    this.iCitizenData = iCitizenData;
  }
}
