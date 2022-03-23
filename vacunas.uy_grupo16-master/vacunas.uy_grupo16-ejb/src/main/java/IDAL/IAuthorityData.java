package IDAL;

import DTO.DTAuthority;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAuthorityData {
  DTAuthority saveAuthority(DTAuthority dtAuthority);
  List<DTAuthority> getAuthority();
  DTAuthority getByIdAuthority(Long id);
  void deleteAuthority(Long id);
}
