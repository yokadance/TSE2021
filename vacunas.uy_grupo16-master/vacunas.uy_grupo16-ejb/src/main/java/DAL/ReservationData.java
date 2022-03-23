package DAL;

import DTO.*;
import IDAL.IReservationData;
import entities.*;
import enumerations.ReservationState;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class ReservationData implements IReservationData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public void saveReservation(DTReservation dtReservation) {
    Reservation reservation = modelMapper.map(dtReservation, Reservation.class);
    if (reservation.getId() == null) {
      data.persist(reservation);
    } else {
      reservation.setCreateDate(data.find(Reservation.class, reservation.getId()).getCreateDate());
      data.merge(reservation);
    }
  }

  @Override
  public int createReservation(
    DTReservation dtReservation,
    DTSchedule dtSchedule,
    DTCitizen dtCitizen,
    DTVaccinationCenter dtVaccinationCenter,
    DTVaccinationPost dtVaccinationPost
  ) {
    Reservation reservation = modelMapper.map(dtReservation, Reservation.class);
    if (dtCitizen != null) {
      Citizen citizen = modelMapper.map(dtCitizen, Citizen.class);
      reservation.setCitizen(citizen);
    }
    if (dtVaccinationCenter != null) {
      VaccinationCenter vaccinationCenter = modelMapper.map(dtVaccinationCenter, VaccinationCenter.class);
      reservation.setVaccinationCenter(vaccinationCenter);
    }
    if (dtSchedule != null) {
      Schedule schedule = modelMapper.map(dtSchedule, Schedule.class);
      schedule.addReservation(reservation);
    }
    if (dtVaccinationPost != null) {
      VaccinationPost vacPost = modelMapper.map(dtVaccinationPost, VaccinationPost.class);
      reservation.setVaccinationPost(vacPost);
    }
    try {
      data.merge(reservation).getId();
      return reservation.getId().intValue();
    } catch (Exception e) {
      System.out.println(e);
      return 0;
    }
  }

  @Override
  public List<DTReservation> getReservations() {
    List<Reservation> reservationList = data.createQuery("select r from Reservation r").getResultList();
    List<DTReservation> dtReservationList = new ArrayList<DTReservation>();
    reservationList.forEach(
      reservation -> {
        DTReservation dtReservation = reservation.getDTReservation();
        dtReservationList.add(dtReservation);
      }
    );
    return dtReservationList;
  }

  @Override
  public DTReservation getByIdReservation(Long id) {
    return data.find(Reservation.class, id).getDTReservation();
  }

  @Override
  public void deleteReservation(Long id) {
    Reservation reservation = data.find(Reservation.class, id);
    Date date = new Date();
    reservation.setDeleteDate(date);
    data.persist(reservation);
  }

  @Override
  public List<DTReservation> getReservationsBySchedule(Long id) {
    List<Reservation> reservationList = data
      .createQuery("select r from Reservation r where r.schedule.id=:id", Reservation.class)
      .setParameter("id", id)
      .getResultList();
    List<DTReservation> dtReservationList = new ArrayList<DTReservation>();
    reservationList.forEach(
      reservation -> {
        DTReservation dtReservation = reservation.getDTReservation();
        dtReservationList.add(dtReservation);
      }
    );
    return dtReservationList;
  }

  @Override
  public boolean availableReservation(String reservationTime, String reservationDate, int fraction, Long schId, int postCount) {
    List<Reservation> reservationList = data
      .createQuery(
        "select r from Reservation r where r.schedule.id=:schId and r.date=:reservationDate and r.time=:reservationTime",
        Reservation.class
      )
      .setParameter("reservationDate", reservationDate)
      .setParameter("reservationTime", reservationTime)
      .setParameter("schId", schId)
      .getResultList();
    System.out.println(reservationList.size());
    int quotas = fraction * 2 * postCount;
    if (quotas > reservationList.size() || reservationList.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public DTVaccinationPost assignReservationToBestPost(Long centerId) {
    VaccinationCenter vacCenter = data.find(VaccinationCenter.class, centerId);
    List<VaccinationPost> vacPosts = vacCenter.getVaccinationPosts();
    DTVaccinationPost dtVacPost = new DTVaccinationPost();

    int min = 999;
    int resCount = 0;

    for (VaccinationPost vPost : vacPosts) {
      resCount = vPost.getReservations().size();
      if (resCount < min) {
        min = resCount;
        dtVacPost = vPost.getDTVaccinationPost();
      }
    }
    return dtVacPost;
  }

  @Override
  public List<DTavailableDate> unavailableDate(DTSchedule dtSchedule) {
    List<Object[]> listObject = data
      .createQuery(
        "select count(r) as count, r.date as date from Reservation r where r.schedule.id = :idshedule AND r.date BETWEEN :reservationStartDate and :reservationEndDate group by r.date"
      )
      .setParameter("reservationEndDate", dtSchedule.getEndDate())
      .setParameter("reservationStartDate", dtSchedule.getStartDate())
      .setParameter("idshedule", dtSchedule.getId())
      .getResultList();
    LocalTime startt = LocalTime.parse((String) dtSchedule.getStartTimeDaily());
    LocalTime endt = LocalTime.parse((String) dtSchedule.getEndTimeDaily());
    int quotas = (int) Duration.between(startt, endt).toMinutes() * 2;
    List<DTavailableDate> dTavailableDateList = new ArrayList<DTavailableDate>();
    for (Object[] objects : listObject) {
      int quotasrThisDay = Math.toIntExact((Long) objects[0]);
      String date = (String) objects[1];
      if (quotasrThisDay >= quotas) {
        DTavailableDate ds = new DTavailableDate();
        ds.setCount(quotasrThisDay);
        ds.setDate(date);
        boolean add = dTavailableDateList.add(ds);
      }
    }
    return dTavailableDateList;
  }

  @Override
  public List<DTavailableDate> unavailableTime(String reservationDate, long idShcedule, int quotasDaily) {
    List<Object[]> listObject = data
      .createQuery(
        "select count(r) as count, r.time as time from Reservation r where r.date = :reservationDate and r.schedule.id=:idShcedule  group by r.time"
      )
      .setParameter("reservationDate", reservationDate)
      .setParameter("idShcedule", idShcedule)
      .getResultList();

    List<DTavailableDate> dTavailableTimeList = new ArrayList<DTavailableDate>();

    for (Object[] objects : listObject) {
      int quotasrThisTime = Math.toIntExact((Long) objects[0]);
      String time = (String) objects[1];
      if (quotasrThisTime >= quotasDaily) {
        DTavailableDate ds = new DTavailableDate();
        ds.setCount(quotasrThisTime);
        ds.setDate(time);
        dTavailableTimeList.add(ds);
      }
    }
    return dTavailableTimeList;
  }

  public List<DTReservationSend> getReservationsSend(Long idShcedule) {
    List<Reservation> listObject = data
      .createQuery(
        "select r.schedule.id, " +
        "r.id, " +
        "r.vaccinationPost.id, " +
        "r.citizen.id, " +
        "r.citizen.person.ci, " +
        "r.date, " +
        "r.time, " +
        "r.reservationState " +
        "from Reservation as r where r.schedule.id=:idShcedule"
      )
      /*     .createQuery("select r from Reservation as r where r.schedule.id= :idShcedle", )*/
      .setParameter("idShcedule", idShcedule)
      .getResultList();
    List<DTReservationSend> sendList = new ArrayList<>();
    listObject.forEach(
      ob -> {
        DTReservationSend dtReservationSend = new DTReservationSend();
        dtReservationSend.setId(ob.getId());
        dtReservationSend.setCreateDate(ob.getCreateDate());
        dtReservationSend.setUpdateDate(ob.getUpdateDate());
        dtReservationSend.setDeleteDate(ob.getDeleteDate());
        dtReservationSend.setUserid(ob.getUserid());
        dtReservationSend.setScheduleId(ob.getSchedule().getId());
        dtReservationSend.setVaccinationPostId(ob.getVaccinationPost().getId());
        //dtReservationSend.setCitizenId(ob.getCitizen().getId());
        dtReservationSend.setPersonId(ob.getCitizen().getPerson().getCi());
        dtReservationSend.setReservationDate(ob.getDate());
        dtReservationSend.setReservationTime(ob.getTime());
        dtReservationSend.setReservationState(ob.getReservationState());
        sendList.add(dtReservationSend);
      }
    );

    return sendList;
  }

  @Override
  public List<DTReservationView> getReservationData(String ci) {
    List<Reservation> listObject = data
      .createQuery("select r from Reservation as r where r.citizen.person.ci=:ci order by r.id desc, r.reservationState asc")
      .setParameter("ci", ci)
      .getResultList();
    System.out.println("Tamaño de la consulta" + listObject.size());
    List<DTReservationView> sendList = new ArrayList<>();
    try {
      listObject.forEach(
        ob -> {
          DTReservationView dtReservationView = new DTReservationView();
          dtReservationView.setReservationId(ob.getId());
          dtReservationView.setVaccinationPlanId(ob.getSchedule().getVaccinationPlan().getId());
          dtReservationView.setVaccinationPlanName(ob.getSchedule().getVaccinationPlan().getName());
          dtReservationView.setReservationCenterId(ob.getVaccinationCenter().getId());
          dtReservationView.setReservationCenterName(ob.getVaccinationCenter().getName());
          dtReservationView.setReservationDate(ob.getDate());
          dtReservationView.setReservationTime(ob.getTime());
          dtReservationView.setDoses(ob.getDoses());
          dtReservationView.setReservationState(ob.getReservationState());
          dtReservationView.setVaccinationPostId(ob.getVaccinationPost().getId());
          dtReservationView.setVaccinationPostName(ob.getVaccinationPost().getName());
          dtReservationView.setVaccineName(ob.getSchedule().getVaccinationPlan().getVaccine().getName());
          sendList.add(dtReservationView);
        }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }

    return sendList;
  }

  @Override
  public boolean reservationChangeState(Long idReservation, ReservationState reservationState) {
    try {
      Reservation reservation = data.find(Reservation.class, idReservation);
      reservation.setReservationState(reservationState);
      return true;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  //.createQuery("select r from Reservation as r where  r.schedule.id=:idShcedule")
  public List<DTReservationSend> getReservationsSend2(Long idShcedule) {
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    System.out.println(today);
    System.out.println("BUSQUEDA");
    List<Reservation> listObject = data
      .createQuery("select r from Reservation as r where  r.vaccinationCenter.id=:idShcedule and r.date=:today")
      /*     .createQuery("select r from Reservation as r where r.schedule.id= :idShcedle", )*/
      .setParameter("idShcedule", idShcedule)
      .setParameter("today", today)
      .getResultList();

    List<DTReservationSend> sendList = new ArrayList<>();
    listObject.forEach(
      ob -> {
        DTReservationSend dtReservationSend = new DTReservationSend();
        dtReservationSend.setId(ob.getId());
        dtReservationSend.setCreateDate(ob.getCreateDate());
        dtReservationSend.setUpdateDate(ob.getUpdateDate());
        dtReservationSend.setDeleteDate(ob.getDeleteDate());
        dtReservationSend.setUserid(ob.getUserid());
        dtReservationSend.setScheduleId(ob.getSchedule().getId());
        dtReservationSend.setScheduleId(ob.getSchedule().getId());
        Long vpid = 1L;
        //dtReservationSend.setVaccinationPostId(vpid);
        dtReservationSend.setVaccinationPostId(ob.getVaccinationPost().getId());
        //dtReservationSend.setCitizenId(vpid);
        // dtReservationSend.setCitizenId(ob.getCitizen().getId());
        dtReservationSend.setCitizenName(ob.getCitizen().getPerson().getName() + " " + ob.getCitizen().getPerson().getLastname());
        dtReservationSend.setPersonId(ob.getCitizen().getPerson().getCi());
        dtReservationSend.setReservationDate(ob.getDate());
        dtReservationSend.setReservationTime(ob.getTime());
        dtReservationSend.setReservationState(ob.getReservationState());
        dtReservationSend.setVaccinatorCenterId(ob.getVaccinationCenter().getId());
        sendList.add(dtReservationSend);
      }
    );

    return sendList;
  }

  public void reservationToConfirmed(Long idReservation) {
    Reservation reservation = data.find(Reservation.class, idReservation);
    if (reservation.getId() != null) {
      reservation.setReservationState(ReservationState.confirmed);
      System.out.println(reservation.getReservationState());
      data.merge(reservation);
    }
  }

  public DTCitizen dtCitizenFromReservation(Long id) {
    Reservation reservation = data.find(Reservation.class, id);

    DTCitizen dtCitizen = new DTCitizen();
    dtCitizen.setId(reservation.getCitizen().getId());
    return dtCitizen;
  }

  public DTVaccinationPost dtVaccinationPostFromReservation(Long id) {
    Reservation reservation = data.find(Reservation.class, id);
    DTVaccinationPost dtVaccinationPost = new DTVaccinationPost();
    dtVaccinationPost.setId(reservation.getVaccinationPost().getId());
    return dtVaccinationPost;
  }

  public List<DTPackage> dtPackageFromReservation(Long id) {
    Reservation reservation = data.find(Reservation.class, id);

    List<entities.Package> aPackage = reservation.getSchedule().getVaccinationPlan().getaPackage();
    List<DTPackage> dtPackageList = new ArrayList<>();
    DTPackage dtPackage = new DTPackage();
    aPackage.forEach(
      ob -> {
        dtPackage.setId(ob.getId());
        dtPackage.setPackageState(ob.getPackageState());
        dtPackage.setQuantity(ob.getQuantity());
        dtPackageList.add(dtPackage);
      }
    );
    return dtPackageList;
  }

  @Override
  public List<DTCitizenToken> getCitizenTokenByReservation() {
    try {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate localDate = LocalDate.now();

      String datenow = dtf.format(localDate.plusDays(1L));
      System.out.println(datenow);
      List<DTCitizenToken> citizenTokenList = new ArrayList<>();
      List<Reservation> listObject = data
        .createQuery("select r from Reservation as r where r.date=:datenow")
        .setParameter("datenow", datenow)
        .getResultList();
      listObject.forEach(
        ob -> {
          DTCitizenToken dtCitizenToken = new DTCitizenToken();
          dtCitizenToken.setName(ob.getCitizen().getPerson().getName());
          dtCitizenToken.setToken(ob.getCitizen().getToken());
          dtCitizenToken.setVaccinationCenterName(ob.getVaccinationCenter().getName());
          dtCitizenToken.setVaccinationPostName(ob.getVaccinationPost().getName());
          dtCitizenToken.setVcaccinationCenterLocation(ob.getVaccinationCenter().getLocation());
          dtCitizenToken.setReservationDate(ob.getDate());
          dtCitizenToken.setReservationTime(ob.getTime());
          citizenTokenList.add(dtCitizenToken);
        }
      );
      return citizenTokenList;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public DtMonitorDate getCountWeek(LocalDate startDate, LocalDate endDate, Long idVPlan) {
    ReservationState reservationState = ReservationState.confirmed;
    List<Object[]> count = new ArrayList<>();
    count =
      data
        .createQuery(
          "select r.doses, count(r) from Reservation r where r.schedule.vaccinationPlan.id=:idVPlan and (r.date between :startDate and :endDate) and r.reservationState=:reservationState group by r.doses"
        )
        .setParameter("endDate", endDate.toString())
        .setParameter("startDate", startDate.toString())
        .setParameter("idVPlan", idVPlan)
        .setParameter("reservationState", reservationState)
        .getResultList();
    DtMonitorDate dtMonitorDate = new DtMonitorDate();
    count.forEach(
      ob -> {
        dtMonitorDate.setDoses((Integer) ob[0], (Long) ob[1]);
      }
    );
    dtMonitorDate.setDate(endDate.toString());

    return dtMonitorDate;
  }
}
