package IDAL;

import DTO.DTDataSource;
import DTO.DTRestriction;

import java.util.List;

public interface IDataSourceData {
  Long saveDataSource(DTDataSource dtDataSource);

  DTDataSource getDataSourceById(Long id);

  void deleteDataSource(Long id);

  List<DTDataSource> listDataSources();

  void addRestrictionToDataSource(Long dataId, DTRestriction dtRestriction);


}
