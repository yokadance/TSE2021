package IController;

import DTO.DTVaccinationPost;
import DTO.DTVaccinationPostNew;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationPostController {
  void saveVaccinationPost(DTVaccinationPost dtVaccinationPost);
  DTVaccinationPost getByIdVaccinationPost(Long id);
  List<DTVaccinationPost> getVaccinationPosts();
  void deleteVaccinationPost(Long id);
  void createVaccinationPost(DTVaccinationPostNew dtVaccinationPostNew);
  List<DTVaccinationPost> getByVaccinatorCenter(Long idVaccinationCenter);
  DTVaccinationPost getVaccinationPostFromReservation (Long idReservation);
}
