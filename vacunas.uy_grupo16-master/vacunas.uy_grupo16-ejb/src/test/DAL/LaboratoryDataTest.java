package DAL;

import DTO.DTLaboratory;
import DTO.DTVaccine;
import entities.Laboratory;
import entities.Vaccine;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LaboratoryDataTest {

  private LaboratoryData laboratoryData;

  private Laboratory laboratory;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private Vaccine vaccine;

  @Before
  public void setup() {
    vaccine = mock(Vaccine.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    laboratory = mock(Laboratory.class);
    this.laboratoryData = new LaboratoryData();
    this.laboratoryData.setData(data);
    this.laboratoryData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(vaccine);
    Mockito.reset(typedQuery);
    Mockito.reset(laboratory);
    Mockito.reset(query);
  }

  @Test
  public void saveLaboratoryTestOk() {
    DTLaboratory dtl = new DTLaboratory();
    when(modelMapper.map(dtl, Laboratory.class)).thenReturn(laboratory);
    when(laboratory.getId()).thenReturn(1L);
    when(data.find(Laboratory.class, 1L)).thenReturn(laboratory);
    when(laboratory.getCreateDate()).thenReturn(new Date());
    when(data.merge(laboratory)).thenReturn(laboratory);

    laboratoryData.saveLaboratory(dtl);
  }

  @Test
  public void saveLaboratoryTestWithIdCero() {
    DTLaboratory dtl = new DTLaboratory();
    when(modelMapper.map(dtl, Laboratory.class)).thenReturn(laboratory);
    when(laboratory.getId()).thenReturn(0L);
    when(data.find(Laboratory.class, 1L)).thenReturn(laboratory);
    when(laboratory.getCreateDate()).thenReturn(new Date());
    when(data.merge(laboratory)).thenReturn(laboratory);

    laboratoryData.saveLaboratory(dtl);
  }

  @Test
  public void getLaboratoryByIdTest() {
    DTLaboratory dtl = new DTLaboratory();
    when(data.find(Laboratory.class, 1L)).thenReturn(laboratory);
    when(laboratory.getDTLaboratory()).thenReturn(dtl);

    laboratoryData.getLaboratoryById(1L);
  }

  @Test
  public void listLaboratorysTest() {
    List<Laboratory> lL = new ArrayList<>();
    lL.add(laboratory);
    List<Vaccine> lV = new ArrayList<>();
    lV.add(vaccine);
    DTVaccine dtv = new DTVaccine();
    Date date = new Date();
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lL);
    when(laboratory.getVaccine()).thenReturn(lV);
    when(vaccine.getDTVaccine()).thenReturn(dtv);
    when(laboratory.getId()).thenReturn(1L);
    when(laboratory.getCreateDate()).thenReturn(date);
    when(laboratory.getUpdateDate()).thenReturn(date);
    when(laboratory.getDeleteDate()).thenReturn(date);
    when(laboratory.getUserid()).thenReturn("Test");
    when(laboratory.getName()).thenReturn("Test");
    when(laboratory.getOrigin()).thenReturn("Test");
    when(laboratory.getEmail()).thenReturn("Test");
    when(laboratory.getPhone()).thenReturn("Test");

    laboratoryData.listLaboratorys();
  }

  @Test
  public void deleteLaboratoryTest() {
    when(data.find(Laboratory.class, 1L)).thenReturn(laboratory);

    laboratoryData.deleteLaboratory(1L);
  }

  @Test
  public void getLaboratoryByNameTest() {
    DTLaboratory dtl = new DTLaboratory();
    List<Laboratory> lL = new ArrayList<>();
    lL.add(laboratory);
    when(data.createQuery("select v from Laboratory v where v.deleteDate is null and LOWER(v.name) like LOWER(:name)"))
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("name", "%Test%")).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lL);
    when(laboratory.getDTLaboratory()).thenReturn(dtl);

    laboratoryData.getLaboratoryByName("Test");
  }

  @Test
  public void getLaboratoryIdByNameTest() {
    List<DTLaboratory> lDtl = new ArrayList<>();
    DTLaboratory dtl = new DTLaboratory();
    dtl.setId(1L);
    lDtl.add(dtl);
    List<Laboratory> lL = new ArrayList<>();
    lL.add(laboratory);
    when(data.createQuery("select v from Laboratory v where v.deleteDate is null and LOWER(v.name) like LOWER(:name)"))
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("name", "%Test%")).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lL);
    when(laboratory.getDTLaboratory()).thenReturn(dtl);

    laboratoryData.getLaboratoryIdByName("Test");
  }
}
