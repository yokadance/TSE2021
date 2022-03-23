package IDAL;

import DTO.DTSchedule;
import DTO.DTScheduleView;

import javax.ejb.Local;
import java.text.ParseException;
import java.util.List;

@Local
public interface IScheduleData {
  Long saveSchedule(DTSchedule dtSchedule);
  DTSchedule getScheduleById(Long id);
  void deleteSchedule(Long id);
  List<DTSchedule> listSchedules();
  List<DTSchedule> SchedulesbyVCandVP(Long idVP, Long idVC);
  List<DTSchedule> getSchedulesByPlan(Long idVC);
  List<DTScheduleView> getNextSchedules();
  boolean unnasignSchedule(Long scheduleId) throws ParseException;
}
