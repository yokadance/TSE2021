package IController;


import DTO.DTVaccination;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationController {
    void newVaccination(DTVaccination dtVaccination);
    List<DTVaccination> listVaccination();
    void saveVaccination (DTVaccination dtVaccination);
    void deleteVaccination (Long id);
    public DTVaccination getVaccinationById (Long id);
}
