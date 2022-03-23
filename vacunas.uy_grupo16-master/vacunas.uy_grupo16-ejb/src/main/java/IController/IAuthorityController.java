package IController;

import DTO.DTAuthority;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAuthorityController {
  void saveAuthority(DTAuthority dtAuthority);
  DTAuthority getByIdAuthority(Long id);
  List<DTAuthority> getAuthority();
  void deleteAuthority(Long id);
}
