package DAL;

import DTO.DTVaccination;
import entities.Vaccination;
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
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationDataTest {

  private VaccinationData vaccinationData;

  private Vaccination vaccination;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  @Before
  public void setup() {
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    vaccination = mock(Vaccination.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    this.vaccinationData = new VaccinationData();
    this.vaccinationData.setData(data);
    this.vaccinationData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
  }

  @Test
  public void saveVaccinationTest() {
    DTVaccination dtv = new DTVaccination();
    when(modelMapper.map(dtv, Vaccination.class)).thenReturn(vaccination);

    vaccinationData.saveVaccination(dtv);
  }

  @Test
  public void getVaccinationByIdTest() {
    when(data.find(Vaccination.class, 1L)).thenReturn(vaccination);
    when(vaccination.getDTVaccination()).thenReturn(new DTVaccination());

    vaccinationData.getVaccinationById(1L);
  }

  @Test
  public void deleteVaccinationTest() {
    when(data.find(Vaccination.class, 1L)).thenReturn(vaccination);
    when(vaccination.getDTVaccination()).thenReturn(new DTVaccination());

    vaccinationData.deleteVaccination(1L);
  }

  @Test
  public void listVaccinationsTest() {
    List<Vaccination> lV = new ArrayList<>();
    lV.add(vaccination);
    when(data.createQuery("select v from Vaccination v")).thenReturn(query);
    when(query.getResultList()).thenReturn(lV);
    when(vaccination.getDTVaccination()).thenReturn(new DTVaccination());

    vaccinationData.listVaccinations();
  }
}
