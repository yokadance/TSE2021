package IController;

import DTO.DTDataSource;
import DTO.DTRestriction;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IDataSourceController {
  Long saveDataSource(DTDataSource dtDataSource);

  DTDataSource getDataSourceById(Long id);

  void deleteDataSource(Long id);

  List<DTDataSource> listDataSources();

  void addRestrictionToDataSource(Long dataId, DTRestriction dtRestriction);
}
