package IController;

import DTO.DTSchedule;
import DTO.DTScheduleView;

import javax.ejb.Local;
import java.text.ParseException;
import java.util.List;

@Local
public interface IScheduleController {
  List<DTSchedule> listSchedules();
  Long saveSchedule(DTSchedule dtSchedule);
  void deleteSchedule(Long id);
  DTSchedule getScheduleById(Long id);
  List<DTSchedule> SchedulesbyVCandVP(Long idVP, Long idVC);
  List<DTSchedule> getSchedulesByPlan(Long idVC);
  List<DTScheduleView> getNextSchedules();
  boolean unnasignSchedule(Long scheduleId) throws ParseException;
}
