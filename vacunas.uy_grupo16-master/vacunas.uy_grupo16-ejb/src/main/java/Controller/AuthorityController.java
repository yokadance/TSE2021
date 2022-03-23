package Controller;

import DTO.DTAuthority;
import IController.IAuthorityController;
import IDAL.IAuthorityData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class AuthorityController implements IAuthorityController {

  @EJB
  IAuthorityData iAuthorityData;

  @Override
  public void saveAuthority(DTAuthority dtAuthority) {
    iAuthorityData.saveAuthority(dtAuthority);
  }

  @Override
  public DTAuthority getByIdAuthority(Long id) {
    return iAuthorityData.getByIdAuthority(id);
  }

  @Override
  public List<DTAuthority> getAuthority() {
    return iAuthorityData.getAuthority();
  }

  @Override
  public void deleteAuthority(Long id) {
    iAuthorityData.deleteAuthority(id);
  }

  public void setiAuthorityData(IAuthorityData iAuthorityData) {
    this.iAuthorityData = iAuthorityData;
  }
}
