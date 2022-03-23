package Controller;

import DTO.DTBatch;
import IController.IBatchController;
import IDAL.IBatchData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class BatchController implements IBatchController {

  @EJB
  IBatchData iBatchData;

  @Override
  public Long saveBatch(DTBatch dtBatch) {
    return iBatchData.saveBatch(dtBatch);
  }

  @Override
  public void deleteBatch(Long id) {
    iBatchData.deleteBatch(id);
  }

  @Override
  public DTBatch getByIdBatch(Long id) {
    return iBatchData.getByIdBatch(id);
  }

  @Override
  public List<DTBatch> getBatches() {
    return iBatchData.getBatches();
  }

  @Override
  public List<DTBatch> getBatchesByVaccineId(Long vaccineId) {
    List<DTBatch> dtbache = iBatchData.getBatchesByVaccineId(vaccineId);
    //dtbache = iBatchData.getBatchesByVaccineId(vaccineId);
    return dtbache;
  }

  @Override
  public void addBatchToVaccine(Long vaccineId, DTBatch dtBatch) {
    iBatchData.addBatchToVaccine(vaccineId, dtBatch);
  }

  public void setiBatchData(IBatchData iBatchData) {
    this.iBatchData = iBatchData;
  }
}
