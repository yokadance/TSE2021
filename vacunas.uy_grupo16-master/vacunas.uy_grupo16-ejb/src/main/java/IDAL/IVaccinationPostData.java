package IDAL;

import DTO.DTVaccinationPost;
import DTO.DTVaccinationPostNew;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IVaccinationPostData {
  void saveVaccinationPost(DTVaccinationPost dtVaccinationPost);
  DTVaccinationPost getByIdVaccinationPost(Long id);
  List<DTVaccinationPost> getVaccinationPosts();
  void deleteVaccinationPost(Long id);
  void createVaccinationPost(DTVaccinationPostNew dtVaccinationPostNew);
  List<DTVaccinationPost> getByVaccinatorCenter(Long idVaccinationCenter);
  public DTVaccinationPost getVaccinationPostFromReservation (Long idReservation);
}
