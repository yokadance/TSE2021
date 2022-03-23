package Controller;

import DTO.DTDataSource;
import DTO.DTRestriction;
import IController.IDataSourceController;
import IDAL.IDataSourceData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class DataSourceController implements IDataSourceController {

  @EJB
  IDataSourceData iDataSourceData;

  @Override
  public Long saveDataSource(DTDataSource dtDataSource) {
    return iDataSourceData.saveDataSource(dtDataSource);
  }

  @Override
  public DTDataSource getDataSourceById(Long id) {
    return iDataSourceData.getDataSourceById(id);
  }

  @Override
  public void deleteDataSource(Long id) {
    iDataSourceData.deleteDataSource(id);
  }

  @Override
  public List<DTDataSource> listDataSources() {
    return iDataSourceData.listDataSources();
  }

  @Override
  public void addRestrictionToDataSource(Long dataId, DTRestriction dtRestriction) {
    iDataSourceData.addRestrictionToDataSource(dataId, dtRestriction);
  }

  public void setiDataSourceData(IDataSourceData iDataSourceData) {
    this.iDataSourceData = iDataSourceData;
  }
}
