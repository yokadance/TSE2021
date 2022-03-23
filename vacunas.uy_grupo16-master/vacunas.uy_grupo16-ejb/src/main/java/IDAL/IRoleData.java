package IDAL;

import DTO.DTCitizen;
import DTO.DTRole;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IRoleData {
  void saveRole(DTCitizen dtCitizen);
  List<DTRole> getRoles();
  void deleteRole(DTRole dtRole);
  void saveAnyRole(DTRole dtRole);
}
