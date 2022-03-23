package Controller;

import DTO.DTSchedule;
import DTO.DTScheduleView;
import IController.IScheduleController;
import IDAL.IScheduleData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.util.List;

@Stateless
@LocalBean
public class ScheduleController implements IScheduleController {

  @EJB
  IScheduleData iScheduleData;

  @Override
  public List<DTSchedule> listSchedules() {
    return iScheduleData.listSchedules();
  }

  @Override
  public Long saveSchedule(DTSchedule dtSchedule) {
    return iScheduleData.saveSchedule(dtSchedule);
  }

  @Override
  public void deleteSchedule(Long id) {
    iScheduleData.deleteSchedule(id);
  }

  @Override
  public DTSchedule getScheduleById(Long id) {
    DTSchedule dt = iScheduleData.getScheduleById(id);
    return dt;
  }

  @Override
  public List<DTSchedule> SchedulesbyVCandVP(Long idVP, Long idVC) {
    return iScheduleData.SchedulesbyVCandVP(idVP, idVC);
  }

  @Override
  public List<DTSchedule> getSchedulesByPlan(Long idVC) {
    return iScheduleData.getSchedulesByPlan(idVC);
  }

  @Override
  public List<DTScheduleView> getNextSchedules() {
    return iScheduleData.getNextSchedules();
  }

  @Override
  public boolean unnasignSchedule(Long scheduleId) throws ParseException { return iScheduleData.unnasignSchedule(scheduleId);}

  public void setiScheduleData(IScheduleData iScheduleData) {
    this.iScheduleData = iScheduleData;
  }


}
