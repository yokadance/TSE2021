package Controllers;

import Controller.BatchController;
import IDAL.IBatchData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BatchControllerTest {

  @Spy
  IBatchData iBatchData;

  private BatchController batchController;

  @Before
  public void setup() {
    this.iBatchData = mock(IBatchData.class);
    this.batchController = new BatchController();
    this.batchController.setiBatchData(iBatchData);
  }

  @After
  public void teardown() {
    Mockito.reset(iBatchData);
  }

  @Test
  public void saveBatchTest() {
    batchController.saveBatch(any());
    Mockito.verify(iBatchData, times(1)).saveBatch(any());
  }

  @Test
  public void deleteBatchTest() {
    batchController.deleteBatch(anyLong());
    Mockito.verify(iBatchData, times(1)).deleteBatch(anyLong());
  }

  @Test
  public void getByIdBatchTest() {
    batchController.getByIdBatch(anyLong());
    Mockito.verify(iBatchData, times(1)).getByIdBatch(anyLong());
  }

  @Test
  public void getBatchesTest() {
    batchController.getBatches();
    Mockito.verify(iBatchData, times(1)).getBatches();
  }

  @Test
  public void getBatchesByVaccineIdTest() {
    when(iBatchData.getBatchesByVaccineId(1L)).thenReturn(any());
    batchController.getBatchesByVaccineId(1L);
    Mockito.verify(iBatchData, times(1)).getBatchesByVaccineId(anyLong());
  }

  @Test
  public void addBatchToVaccineTest() {
    batchController.addBatchToVaccine(anyLong(), any());
    Mockito.verify(iBatchData, times(1)).addBatchToVaccine(anyLong(), any());
  }
}
