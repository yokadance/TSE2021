package IController;

import DTO.DTBatch;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IBatchController {
  Long saveBatch(DTBatch dtBatch);
  void deleteBatch(Long id);
  DTBatch getByIdBatch(Long id);
  List<DTBatch> getBatches();
  List<DTBatch> getBatchesByVaccineId(Long vaccineId);
  void addBatchToVaccine(Long vaccineId, DTBatch dtBatch);
}
