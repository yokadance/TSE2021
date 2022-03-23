package DAL;

import DTO.DTDataSource;
import DTO.DTRestriction;
import IDAL.IDataSourceData;
import IDAL.IVaccineData;
import entities.DataSource;
import entities.Restriction;
import org.modelmapper.ModelMapper;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class DataSourceData implements IDataSourceData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @EJB
  IVaccineData iVaccineData;

  @Override
  public Long saveDataSource(DTDataSource dtDataSource) {
    DataSource datasource = modelMapper.map(dtDataSource, DataSource.class);
    if (datasource.getId() == null) {
      datasource.setCreateDate(new Date());
      data.persist(datasource);
    } else {
      datasource.setCreateDate(data.find(DataSource.class, datasource.getId()).getCreateDate());
      datasource.setUpdateDate(new Date());
      data.merge(datasource);
    }

    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return datasource.getId();
    //

  }

  @Override
  public DTDataSource getDataSourceById(Long id) {
    try {
      DTDataSource dtDataSource = data.find(DataSource.class, id).getDTDataSource();
      return dtDataSource;
    } catch (NoResultException nre) {
      return null;
    }
  }

  @Override
  public void deleteDataSource(Long id) {
    DTDataSource dtDataSource = getDataSourceById(id);

    DataSource dataSource = modelMapper.map(dtDataSource, DataSource.class);

    if (dataSource.getId() != null) {
      dataSource.setDeleteDate(new Date());
      data.merge(dataSource);
    }
  }

  @Override
  public List<DTDataSource> listDataSources() {
    List<DataSource> dataList = data.createQuery("select d from DataSource d where d.deleteDate is null").getResultList();
    List<DTDataSource> dtDataList = new ArrayList<>();
    dataList.forEach(
      dataSource -> {
        DTDataSource dtDataSource = dataSource.getDTDataSource();
        dtDataList.add(dtDataSource);
      }
    );
    return dtDataList;
  }


  @Override
  public void addRestrictionToDataSource(Long dataId, DTRestriction dtRestriction) {
    Restriction restriction = modelMapper.map(dtRestriction, Restriction.class);
    DataSource dataSource = data.find(DataSource.class, dataId);

    if (restriction != null) {
      dataSource.setUpdateDate(new Date());
      dataSource.setRestriction(restriction);
      data.merge(dataSource);
    }
  }


  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
