package IController;

import DTO.DTAdministrator;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAdministratorController {

    void saveAdministrator(DTAdministrator dtAdministrator);
    DTAdministrator getByIdAdministrator(Long id);
    List<DTAdministrator> getAdministrators();
    void deleteAdministrator(Long id);

}
