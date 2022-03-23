package Controller;

import DTO.DTVaccinationPost;
import DTO.DTVaccinationPostNew;
import IController.IVaccinationPostController;
import IDAL.IVaccinationPostData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class VaccinationPostController implements IVaccinationPostController {

  @EJB
  IVaccinationPostData iVaccinationPostData;

  @Override
  public void saveVaccinationPost(DTVaccinationPost dtVaccinationPost) {
    iVaccinationPostData.saveVaccinationPost(dtVaccinationPost);
  }

  @Override
  public DTVaccinationPost getByIdVaccinationPost(Long id) {
    return iVaccinationPostData.getByIdVaccinationPost(id);
  }

  @Override
  public List<DTVaccinationPost> getVaccinationPosts() {
    return iVaccinationPostData.getVaccinationPosts();
  }

  @Override
  public void deleteVaccinationPost(Long id) {
    iVaccinationPostData.deleteVaccinationPost(id);
  }

  @Override
  public void createVaccinationPost(DTVaccinationPostNew dtVaccinationPostNew) {
    iVaccinationPostData.createVaccinationPost(dtVaccinationPostNew);
  }

  @Override
  public List<DTVaccinationPost> getByVaccinatorCenter(Long idVaccinationCenter) {
    List<DTVaccinationPost> vaccinationPostList = iVaccinationPostData.getByVaccinatorCenter(idVaccinationCenter);
    return vaccinationPostList;
  }

  public void setiVaccinationPostData(IVaccinationPostData iVaccinationPostData) {
    this.iVaccinationPostData = iVaccinationPostData;
  }

  @Override
  public DTVaccinationPost getVaccinationPostFromReservation (Long idReservation){
    return iVaccinationPostData.getVaccinationPostFromReservation(idReservation);
  }
}
