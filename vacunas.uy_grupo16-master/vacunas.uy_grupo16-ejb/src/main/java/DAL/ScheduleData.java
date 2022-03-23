package DAL;

import DTO.DTSchedule;
import DTO.DTScheduleView;
import IDAL.IScheduleData;
import entities.Reservation;
import entities.Schedule;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class ScheduleData implements IScheduleData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public Long saveSchedule(DTSchedule dtSchedule) {
    Schedule schedule = modelMapper.map(dtSchedule, Schedule.class);
    if (schedule.getId() == null) {
      data.persist(schedule);
    } else {
      schedule.setCreateDate(data.find(Schedule.class, schedule.getId()).getCreateDate());
      data.merge(schedule);
    }

    // PARA RETORNAR ID AGREGO LAS SIGUIENTES DOS LINEAS
    data.flush();
    return schedule.getId();
    //

  }

  @Override
  public DTSchedule getScheduleById(Long id) {
    try {
      DTSchedule dtSchedule = data.find(Schedule.class, id).getDTSchedule();
      return dtSchedule;
    } catch (NoResultException nre) {
      return null;
    }
  }

  @Override
  public void deleteSchedule(Long id) {
    //    DTSchedule schedule = getScheduleById(id);
    Schedule schedule = data.find(Schedule.class, id);
    schedule.setDeleteDate(new Date());
    data.persist(schedule);
  }

  @Override
  public List<DTSchedule> listSchedules() {
    List<Schedule> scheduleList = data.createQuery("select v from Schedule v").getResultList();
    List<DTSchedule> dtSchedulesList = new ArrayList<DTSchedule>();
    scheduleList.forEach(
      schedules -> {
        DTSchedule dtSchedule = schedules.getDTSchedule();
        dtSchedulesList.add(dtSchedule);
      }
    );
    return dtSchedulesList;
  }

  @Override
  public List<DTSchedule> SchedulesbyVCandVP(Long idVP, Long idVC) {
    System.out.println(idVP);
    System.out.println(idVC);
    List<Schedule> scheduleList = data
      .createQuery("select v from Schedule v where v.vaccinationCenter.id= :idVC and v.vaccinationPlan.id= :idVP")
      .setParameter("idVC", idVC)
      .setParameter("idVP", idVP)
      .getResultList();
    List<DTSchedule> dtSchedulesList = new ArrayList<DTSchedule>();

    for(Schedule sch : scheduleList){
      DTSchedule dtSchedule = new DTSchedule();
      dtSchedule.setId(sch.getId());
      dtSchedule.setVaccinationCenter(sch.getVaccinationCenter().getDTVaccinationCenter());
      dtSchedule.setVaccinationPlan(sch.getVaccinationPlan().getDTVaccinationPlan());
      dtSchedule.setSunday(sch.isSunday());
      dtSchedule.setSaturday(sch.isSaturday());
      dtSchedule.setStartTimeDaily(sch.getStartTimeDaily());
      dtSchedule.setEndTimeDaily(sch.getEndTimeDaily());
      dtSchedule.setStartDate(sch.getStartDate());
      dtSchedule.setEndDate(sch.getEndDate());
      dtSchedule.setFraction(sch.getFraction());
      dtSchedulesList.add(dtSchedule);
    }

//    scheduleList.forEach(
//      schedules -> {
//        DTSchedule dtSchedule = schedules.getDTSchedule();
//        dtSchedulesList.add(dtSchedule);
//      }
//    );
    return dtSchedulesList;
  }

  @Override
  public List<DTSchedule> getSchedulesByPlan(Long idVP) {
    Query query = data
      .createQuery("select v from Schedule v where v.vaccinationPlan.id= :idVP and v.deleteDate is null")
      .setParameter("idVP", idVP);
    List<Schedule> scheduleList = query.getResultList();
    List<DTSchedule> dtScheduleList = new ArrayList<>();

    if (scheduleList.size() > 0) {
      for (Schedule sch : scheduleList) {
        dtScheduleList.add(sch.getDTSchedule());
      }
    }
    return dtScheduleList;
  }

  @Override
  public List<DTScheduleView> getNextSchedules() {
    List<DTScheduleView> dtScheduleViews = new ArrayList<>();
    LocalDate dateToday = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println(dateToday.format(formatter));
    String today = dateToday.format(formatter);
    try {
      List<Schedule> scheduleList = data
        .createQuery("select s from Schedule s where s.endDate >= :today and s.deleteDate is null order by s.endDate")
        .setParameter("today", today)
        .getResultList();
      scheduleList.forEach(
        schedule -> {
          DTScheduleView dtScheduleView = new DTScheduleView();
          dtScheduleView.setEndDate(schedule.getEndDate());
          dtScheduleView.setEndTimeDaily(schedule.getEndTimeDaily());
          dtScheduleView.setStartTimeDaily(schedule.getStartTimeDaily());
          dtScheduleView.setStartDate(schedule.getStartDate());
          dtScheduleView.setVaccinationPlan(schedule.getVaccinationPlan().getName());
          dtScheduleView.setVaccinationPlanId(schedule.getVaccinationPlan().getId().toString());
          dtScheduleView.setVaccinationCenterName(schedule.getVaccinationCenter().getName());
          dtScheduleView.setSaturday(schedule.isSaturday());
          dtScheduleView.setSunday(schedule.isSunday());
          dtScheduleView.setVaccineName(
            schedule.getVaccinationPlan().getaPackage().stream().findFirst().get().getBatch().getVaccine().getName()
          );
          dtScheduleViews.add(dtScheduleView);
        }
      );
      return dtScheduleViews;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public boolean unnasignSchedule(Long scheduleId) throws ParseException {

    Schedule schedule = data.find(Schedule.class, scheduleId);

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<Reservation> reservationList = schedule.getReservations();
    for(Reservation res : reservationList){
      if(dateFormat.parse(res.getDate()).compareTo(date) > 0)
        return false;
    }

    schedule.setReservations(null);
    schedule.setVaccinationPlan(null);
    schedule.setVaccinationCenter(null);
    schedule.setAssignment(null);

    schedule.setDeleteDate(date);
    data.merge(schedule);
    return true;
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
