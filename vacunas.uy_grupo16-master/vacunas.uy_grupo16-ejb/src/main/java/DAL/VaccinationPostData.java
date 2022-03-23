package DAL;

import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPost;
import DTO.DTVaccinationPostNew;
import IDAL.IVaccinationPostData;
import entities.Reservation;
import entities.VaccinationCenter;
import entities.VaccinationPost;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class VaccinationPostData implements IVaccinationPostData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public void saveVaccinationPost(DTVaccinationPost dtVaccinationPost) {
    VaccinationPost vaccinationPost = modelMapper.map(dtVaccinationPost, VaccinationPost.class);
    if (vaccinationPost.getId() == null) {
      data.persist(vaccinationPost);
    } else {
      vaccinationPost.setCreateDate(data.find(VaccinationPost.class, vaccinationPost.getId()).getCreateDate());
      data.merge(vaccinationPost);
    }
  }

  @Override
  public DTVaccinationPost getByIdVaccinationPost(Long id) {
    return data.find(VaccinationPost.class, id).getDTVaccinationPost();
  }

  @Override
  public List<DTVaccinationPost> getVaccinationPosts() {
    List<VaccinationPost> vaccinationPostList = data
      .createQuery("select v from VaccinationPost v where v.deleteDate is null")
      .getResultList();
    List<DTVaccinationPost> dtVaccinationPosts = new ArrayList<DTVaccinationPost>();
    vaccinationPostList.forEach(
      vaccinationPost -> {
        DTVaccinationPost dtVaccinationPost = vaccinationPost.getDTVaccinationPost();
        dtVaccinationPosts.add(dtVaccinationPost);
      }
    );
    return dtVaccinationPosts;
  }

  @Override
  public void deleteVaccinationPost(Long id) {
    VaccinationPost vaccinationPost = data.find(VaccinationPost.class, id);
    Date date = new Date();
    vaccinationPost.setDeleteDate(date);
    data.persist(vaccinationPost);
  }

  @Override
  public void createVaccinationPost(DTVaccinationPostNew dtVaccinationPostNew) {
    VaccinationCenter vaccinationCenter = data.find(VaccinationCenter.class, dtVaccinationPostNew.getIdVaccinatonCenter());
    DTVaccinationCenter dtVaccinationCenter = vaccinationCenter.getDTVaccinationCenter();
    VaccinationPost vaccinationPost = modelMapper.map(dtVaccinationPostNew, VaccinationPost.class);
    vaccinationPost.setVaccinationCenter(vaccinationCenter);
    data.merge(vaccinationPost);
  }

  @Override
  public List<DTVaccinationPost> getByVaccinatorCenter(Long idVaccinationCenter) {
    List<VaccinationPost> listObject = data
      .createQuery("select v from VaccinationPost as v where v.vaccinationCenter.id=:idVaccinationCenter and v.deleteDate is null")
      /*     .createQuery("select r from Reservation as r where r.schedule.id= :idShcedle", )*/
      .setParameter("idVaccinationCenter", idVaccinationCenter)
      .getResultList();
    List<DTVaccinationPost> sendList = new ArrayList<>();
    listObject.forEach(
      ob -> {
        DTVaccinationPost dtVaccinationPost = new DTVaccinationPost();
        dtVaccinationPost = ob.getDTVaccinationPost();
        sendList.add(dtVaccinationPost);
      }
    );
    return sendList;
  }

  @Override
  public DTVaccinationPost getVaccinationPostFromReservation (Long idReservation){
    Reservation reservation = data.find(Reservation.class, idReservation);
    VaccinationPost VaccinationPost = reservation.getVaccinationPost();
    DTVaccinationPost dtVaccinationPost = VaccinationPost.getDTVaccinationPost();
    return dtVaccinationPost;
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
