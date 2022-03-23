package DAL;

import DTO.*;
import IController.IPackageController;
import IDAL.IVaccineData;
import entities.*;
import entities.Package;
import enumerations.PackageState;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;

@Singleton
public class VaccineData implements IVaccineData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Inject
  IPackageController iPackageController;

  @Override
  public Long saveVaccine(DTVaccine dtVaccine) {
    Vaccine vaccine = modelMapper.map(dtVaccine, Vaccine.class);

    if (vaccine.getId() == null) {
      data.persist(vaccine);
    } else {
      vaccine.setCreateDate(data.find(Vaccine.class, vaccine.getId()).getCreateDate());
      data.merge(vaccine);
    }

    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return vaccine.getId();
    //

  }

  public void createVaccine(DTVaccine dtVaccine, DTDisease dtDisease, DTLaboratory dtLaboratory) {
    Vaccine vaccine = modelMapper.map(dtVaccine, Vaccine.class);
    if (dtDisease != null) {
      Disease disease = modelMapper.map(dtDisease, Disease.class);
      vaccine.setDisease(disease);
    }
    if (dtLaboratory == null) {
      Laboratory laboratory = modelMapper.map(dtLaboratory, Laboratory.class);
      vaccine.setLaboratory(laboratory);
    }
    if (vaccine.getId() == null) {
      data.persist(vaccine);
    } else {
      vaccine.setCreateDate(data.find(Vaccine.class, vaccine.getId()).getCreateDate());
      data.merge(vaccine);
    }
  }

  @Override
  public DTVaccine getVaccineById(Long id) {
    try {
      DTVaccine dtVaccine = data.find(Vaccine.class, id).getDTVaccine();
      return dtVaccine;
    } catch (NoResultException nre) {
      return null;
    }
  }

  @Override
  public void deleteVaccine(Long id) {
    DTVaccine dtVaccine = getVaccineById(id);

    Vaccine vaccine = modelMapper.map(dtVaccine, Vaccine.class);

    if (vaccine.getId() != null) {
      vaccine.setDeleteDate(new Date());
      data.merge(vaccine);
    }
  }

  @Override
  public List<DTVaccine> listVaccines() {
    List<Vaccine> vaccineList = data.createQuery("select v from Vaccine v where v.deleteDate is null").getResultList();
    List<DTVaccine> dtVaccineList = new ArrayList<DTVaccine>();

    for (Vaccine vac : vaccineList) {
      DTVaccine dtVaccine = vac.getDTVaccine();

      String labName = "";
      if (vac.getLaboratory() != null) labName = vac.getLaboratory().getName();
      dtVaccine.setLaboratory(labName);

      String disName = "";
      if (vac.getDisease() != null) disName = vac.getDisease().getName();
      dtVaccine.setDisease(disName);

      dtVaccineList.add(dtVaccine);
    }

    return dtVaccineList;
  }

  @Override
  public void addDiseaseToVaccine(Long vaccineId, DTDisease dtDisease) {
    Disease disease = modelMapper.map(dtDisease, Disease.class);
    Vaccine vaccine = data.find(Vaccine.class, vaccineId);

    if (vaccine != null) {
      vaccine.setDisease(disease);
      vaccine.setUpdateDate(new Date());
      data.merge(vaccine);
    }
  }

  @Override
  public void addLaboratoryToVaccine(Long vaccineId, DTLaboratory dtLaboratory) {
    Laboratory laboratory = modelMapper.map(dtLaboratory, Laboratory.class);
    Vaccine vaccine = data.find(Vaccine.class, vaccineId);

    if (vaccine != null) {
      vaccine.setLaboratory(laboratory);
      vaccine.setUpdateDate(new Date());
      data.merge(vaccine);
    }
  }

  @Override
  public List<DTVaccineReport> getVaccineReport(Long vaccineId) {
    List<DTPackage> dtPackageList = new ArrayList<>();

    Vaccine vaccine = data.find(Vaccine.class, vaccineId);

    List<DTVaccineReport> dtVaccineReports = new ArrayList<>();
    List<Batch> batchesList = vaccine.getBatches();

    if (batchesList != null) {
      for (Batch batch : batchesList) {
        dtPackageList = iPackageController.getPackagesByBatchId(batch.getId());
        if (dtPackageList != null) {
          for (DTPackage dtPack : dtPackageList) {
            DTVaccineReport dtVacRep = new DTVaccineReport();
            Package aPack = data.find(Package.class, dtPack.getId());
            dtVacRep.setVaccineName(vaccine.getName());
            dtVacRep.setDiseaseName(vaccine.getDisease().getName());
            dtVacRep.setBatchNumber(batch.getNumber());
            dtVacRep.setBatchAvailable(batch.getAvailable());

            if (aPack.getVaccinationPlan() != null) {
              dtVacRep.setPlanName(aPack.getVaccinationPlan().getName());
            } else {
              dtVacRep.setPlanName("Sin asignar");
            }
            if (aPack.getVaccinationCenter() != null) {
              dtVacRep.setCenterName(aPack.getVaccinationCenter().getName());
            } else {
              dtVacRep.setCenterName("Sin asignar");
            }
            dtVacRep.setpQuantity(aPack.getQuantity());
            dtVacRep.setpNumber(aPack.getPackageNumber());

            if (aPack.getPackageState() == PackageState.Delivered) dtVacRep.setpState("Entregado");
            if (aPack.getPackageState() == PackageState.Pending) dtVacRep.setpState("Pendiente");
            if (aPack.getPackageState() == PackageState.InTransit) dtVacRep.setpState("En transito");
            dtVaccineReports.add(dtVacRep);
            data.flush();
          }
        }
      }
    }
    return dtVaccineReports;
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public void setiPackageController(IPackageController iPackageController) {
    this.iPackageController = iPackageController;
  }
}
