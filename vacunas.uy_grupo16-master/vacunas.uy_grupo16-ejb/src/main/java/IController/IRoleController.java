package IController;

import DTO.DTCitizen;
import DTO.DTRole;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface IRoleController {
  void saveRole(DTCitizen dtCitizen);
  List<DTRole> getRoles();
  void deleteRole(DTRole dtRole);
  void saveAnyRole(DTRole dtRole);
}
