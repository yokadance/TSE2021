package IDAL;

import DTO.DTAdministrator;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAdministratorData {
  DTAdministrator saveAdministrator(DTAdministrator dtad);
  List<DTAdministrator> getAdministrators();
  DTAdministrator getByIdAdministrator(Long id);
  void deleteAdministrator(Long id);
}
