package DAL;

import DTO.DTBatch;
import DTO.DTVaccine;
import IController.IVaccineController;
import IDAL.IBatchData;
import entities.Batch;
import entities.Vaccine;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class BatchData implements IBatchData {

  @Inject
  IVaccineController iVaccineController;

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public Long saveBatch(DTBatch dtBatch) {
    Batch batch = modelMapper.map(dtBatch, Batch.class);
    DTVaccine dtVaccine = iVaccineController.getVaccineById(Long.parseLong(dtBatch.getVaccine()));
    if (dtVaccine != null) {
      Vaccine vaccine = modelMapper.map(dtVaccine, Vaccine.class);
      batch.setVaccine(vaccine);
    }
    if (batch.getId() == null) {
      data.persist(batch);
    } else {
      batch.setCreateDate(data.find(Batch.class, batch.getId()).getCreateDate());
      data.merge(batch);
    }

    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return batch.getId();
  }

  @Override
  public void deleteBatch(Long id) {
    Batch batch = data.find(Batch.class, id);
    Date date = new Date();
    batch.setDeleteDate(date);
    data.persist(batch);
  }

  @Override
  public DTBatch getByIdBatch(Long id) {
    try {
      Batch batch = data.find(Batch.class, id);
      DTBatch dtBatch = batch.getDTBatch();
      dtBatch.setVaccine(batch.getVaccine().getId().toString());
      return dtBatch;
//      return data.find(Batch.class, id).getDTBatch();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public List<DTBatch> getBatches() {
    List<Batch> batchList = data.createQuery("select b from Batch b").getResultList();
    List<DTBatch> dtBatchList = new ArrayList<DTBatch>();
    batchList.forEach(
      batch -> {
        DTBatch dtBatch = batch.getDTBatch();
        dtBatchList.add(dtBatch);
      }
    );
    return dtBatchList;
  }

  public List<DTBatch> getBatchesByVaccineId(Long vaccineId) {
    List<Batch> batchList = data
      .createQuery("select b from Batch b where b.vaccine.id=:vaccineId and b.deleteDate is null")
      .setParameter("vaccineId", vaccineId)
      .getResultList();

    List<DTBatch> dtBatchList = new ArrayList<DTBatch>();
    batchList.forEach(
      batch -> {
        DTBatch dtBatch = batch.getDTBatch();
        dtBatchList.add(dtBatch);
      }
    );

    return dtBatchList;
  }

  @Override
  public void addBatchToVaccine(Long vaccineId, DTBatch dtBatch) {
    Batch batch = modelMapper.map(dtBatch, Batch.class);
    Vaccine vaccine = data.find(Vaccine.class, vaccineId);
    List<Batch> batchList = new ArrayList<>();

    Batch batchToUpdate = data.find(Batch.class, dtBatch.getId());

    if (vaccine != null) {
      batchList = vaccine.getBatches();
      batchList.add(batch);
      vaccine.setBatches(batchList);
      vaccine.setUpdateDate(new Date());
      batchToUpdate.setVaccine(vaccine);
      data.merge(vaccine);
      data.merge(batchToUpdate);
    }
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public void setiVaccineController(IVaccineController iVaccineController) {
    this.iVaccineController = iVaccineController;
  }
}
