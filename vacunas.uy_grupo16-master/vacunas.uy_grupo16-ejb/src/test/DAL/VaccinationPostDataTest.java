package DAL;

import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPost;
import DTO.DTVaccinationPostNew;
import entities.VaccinationCenter;
import entities.VaccinationPost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationPostDataTest {

  private VaccinationPostData vaccinationPostData;

  private VaccinationPost vaccinationPost;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private VaccinationCenter vaccinationCenter;

  @Before
  public void setup() {
    vaccinationCenter = mock(VaccinationCenter.class);
    vaccinationPost = mock(VaccinationPost.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    this.vaccinationPostData = new VaccinationPostData();
    this.vaccinationPostData.setData(data);
    this.vaccinationPostData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(vaccinationCenter);
    Mockito.reset(vaccinationPost);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
  }

  @Test
  public void saveVaccinationPostTestWithId() {
    DTVaccinationPost dtvp = new DTVaccinationPost();
    when(modelMapper.map(dtvp, VaccinationPost.class)).thenReturn(vaccinationPost);
    when(vaccinationPost.getId()).thenReturn(1L);
    when(vaccinationPost.getCreateDate()).thenReturn(new Date());
    when(data.find(VaccinationPost.class, 1L)).thenReturn(vaccinationPost);

    vaccinationPostData.saveVaccinationPost(dtvp);
  }

  @Test
  public void saveVaccinationPostTestWithoutId() {
    DTVaccinationPost dtvp = new DTVaccinationPost();
    when(modelMapper.map(dtvp, VaccinationPost.class)).thenReturn(vaccinationPost);
    when(vaccinationPost.getId()).thenReturn(null);

    vaccinationPostData.saveVaccinationPost(dtvp);
  }

  @Test
  public void getByIdVaccinationPostTest() {
    when(data.find(VaccinationPost.class, 1L)).thenReturn(vaccinationPost);

    vaccinationPostData.getByIdVaccinationPost(1L);
  }

  @Test
  public void getVaccinationPostsTest() {
    List<VaccinationPost> lVp = new ArrayList<>();
    lVp.add(vaccinationPost);
    DTVaccinationPost dtvp = new DTVaccinationPost();
    when(data.createQuery("select v from VaccinationPost v where v.deleteDate is null")).thenReturn(query);
    when(query.getResultList()).thenReturn(lVp);
    when(vaccinationPost.getDTVaccinationPost()).thenReturn(dtvp);

    vaccinationPostData.getVaccinationPosts();
  }

  @Test
  public void deleteVaccinationPostTest() {
    when(data.find(VaccinationPost.class, 1L)).thenReturn(vaccinationPost);

    vaccinationPostData.deleteVaccinationPost(1L);
  }

  @Test
  public void createVaccinationPostTest() {
    DTVaccinationPostNew dtvpn = new DTVaccinationPostNew();
    dtvpn.setIdVaccinatonCenter(1L);
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getDTVaccinationCenter()).thenReturn(new DTVaccinationCenter());
    when(modelMapper.map(dtvpn, VaccinationPost.class)).thenReturn(vaccinationPost);

    vaccinationPostData.createVaccinationPost(dtvpn);
  }

  @Test
  public void getByVaccinatorCenterTest() {
    List<VaccinationPost> lVp = new ArrayList<>();
    lVp.add(vaccinationPost);
    when(data.createQuery("select v from VaccinationPost as v where v.vaccinationCenter.id=:idVaccinationCenter and v.deleteDate is null"))
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("idVaccinationCenter", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lVp);
    when(vaccinationPost.getDTVaccinationPost()).thenReturn(new DTVaccinationPost());

    vaccinationPostData.getByVaccinatorCenter(1L);
  }
}
