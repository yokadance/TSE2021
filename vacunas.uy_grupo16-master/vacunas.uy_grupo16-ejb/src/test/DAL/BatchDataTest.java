package DAL;

import DTO.DTBatch;
import DTO.DTVaccine;
import IController.IVaccineController;
import entities.Batch;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BatchDataTest {

  private BatchData batchData;

  private Batch batch;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private IVaccineController iVaccineController;

  private Vaccine vaccine;

  @Before
  public void setup() {
    vaccine = mock(Vaccine.class);
    batch = mock(Batch.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    iVaccineController = mock(IVaccineController.class);
    this.batchData = new BatchData();
    this.batchData.setData(data);
    this.batchData.setModelMapper(modelMapper);
    this.batchData.setiVaccineController(iVaccineController);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(vaccine);
    Mockito.reset(batch);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
  }

  @Test
  public void saveBatchTestWithIdNotNull() {
    DTBatch dtb = new DTBatch();
    dtb.setVaccine("1");
    DTVaccine dtv = new DTVaccine();
    when(modelMapper.map(dtb, Batch.class)).thenReturn(batch);
    when(iVaccineController.getVaccineById(1L)).thenReturn(dtv);
    when(modelMapper.map(dtv, Vaccine.class)).thenReturn(vaccine);
    when(batch.getId()).thenReturn(1L);
    when(data.find(Batch.class, 1L)).thenReturn(batch);
    when(batch.getCreateDate()).thenReturn(new Date());

    batchData.saveBatch(dtb);
  }

  @Test
  public void saveBatchTestWithIdNull() {
    DTBatch dtb = new DTBatch();
    dtb.setVaccine("1");
    DTVaccine dtv = new DTVaccine();
    when(modelMapper.map(dtb, Batch.class)).thenReturn(batch);
    when(iVaccineController.getVaccineById(1L)).thenReturn(dtv);
    when(modelMapper.map(dtv, Vaccine.class)).thenReturn(vaccine);
    when(batch.getId()).thenReturn(null);

    batchData.saveBatch(dtb);
  }

  @Test
  public void deleteBatchTest() {
    when(data.find(Batch.class, 1L)).thenReturn(batch);

    batchData.deleteBatch(1L);
  }

  @Test
  public void getByIdBatchTest() {
    when(data.find(Batch.class, 1L)).thenReturn(batch);
    when(batch.getVaccine()).thenReturn(vaccine);
    when(vaccine.getId()).thenReturn(1L);
    when(batch.getDTBatch()).thenReturn(new DTBatch());

    batchData.getByIdBatch(1L);
  }

  @Test
  public void getBatchesTest() {
    List<Batch> lB = new ArrayList<>();
    lB.add(batch);
    when(data.createQuery("select b from Batch b")).thenReturn(query);
    when(query.getResultList()).thenReturn(lB);
    when(batch.getDTBatch()).thenReturn(new DTBatch());

    batchData.getBatches();
  }

  @Test
  public void getBatchesByVaccineIdTest() {
    List<Batch> lB = new ArrayList<>();
    lB.add(batch);
    when(data.createQuery("select b from Batch b where b.vaccine.id=:vaccineId and b.deleteDate is null")).thenReturn(typedQuery);
    when(typedQuery.setParameter("vaccineId", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lB);
    when(batch.getDTBatch()).thenReturn(new DTBatch());

    batchData.getBatchesByVaccineId(1L);
  }

  @Test
  public void addBatchToVaccineTest() {
    List<Batch> lB = new ArrayList<>();
    lB.add(batch);
    DTBatch dtb = new DTBatch();
    dtb.setId(1L);
    when(modelMapper.map(dtb, Batch.class)).thenReturn(batch);
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);
    when(data.find(Batch.class, 1L)).thenReturn(batch);
    when(vaccine.getBatches()).thenReturn(lB);

    batchData.addBatchToVaccine(1L, dtb);
  }
}
