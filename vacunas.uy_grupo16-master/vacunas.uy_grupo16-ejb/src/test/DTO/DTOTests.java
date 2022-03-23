package DTO;

import entities.AssignmentPK;
import enumerations.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DTOTests {

  private String fakeString = "Test";

  private Date fakeDate = new Date();

  private Long fakeLong = 1L;

  private int fakeInt = 1;

  private float fakeFloat;

  @Test
  public void DTIotTest() {
    DTIot dtIot = new DTIot();
    dtIot = new DTIot(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeDate);
    dtIot.setLogisticPartner(new DTLogisticPartner());
    dtIot.setMessage(fakeString);
    dtIot.getLogisticPartner();
    dtIot.getMessage();
  }

  @Test
  public void DTavailableDateTest() {
    DTavailableDate dtad = new DTavailableDate();
    dtad.getCount();
    dtad.getDate();
  }

  @Test
  public void DTAdministratorTest() {
    DTAdministrator dta = new DTAdministrator(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, new DTPerson());
  }

  @Test
  public void DTAssignmentTest() {
    DTAssignment dta = new DTAssignment();
    dta = new DTAssignment(fakeString, fakeString, fakeString, fakeString);
    dta.getStartDate();
    dta.setStartDate(fakeString);
    dta.getEndDate();
    dta.setEndDate(fakeString);
    dta.getStartTime();
    dta.setStartTime(fakeString);
    dta.getEndTime();
    dta.setEndTime(fakeString);
    dta.getVaccinationPost();
    dta.setVaccinationPost(new DTVaccinationPost());
    dta.getVaccinator();
    dta.setVaccinator(new DTVaccinator());
    dta.getSchedule();
    dta.setSchedule(new DTSchedule());
    dta.getPkId();
    dta.setPkId(new AssignmentPK());
  }

  @Test
  public void DTAuthorityTest() {
    List<DTVaccinationPlan> vaccinationPlans = new ArrayList<>();
    List<DTSchedule> schedules = new ArrayList<>();
    DTAuthority dta = new DTAuthority(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeString,
      new DTPerson(),
      vaccinationPlans,
      schedules
    );
    dta.getVaccinationPlans();
    dta.setVaccinationPlans(vaccinationPlans);
    dta.getSchedules();
    dta.setSchedules(schedules);
  }

  @Test
  public void DTBaseModelTest() {
    DTBaseModel dtbm = new DTBaseModel() {};
    dtbm.getCreateDate();
    dtbm.getUpdateDate();
    dtbm.getDeleteDate();
    dtbm.toString();
  }

  @Test
  public void DTBasicPersonTest() {
    DTBasicPerson dtbp = new DTBasicPerson();
    List<String> lS = new ArrayList<>();
    dtbp =
      new DTBasicPerson(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, fakeString, Sex.feminine, fakeDate, fakeString, lS);
    dtbp = new DTBasicPerson(fakeString, fakeString, Sex.feminine, fakeDate, fakeString, lS);
    dtbp.getIdUruguay();
    dtbp.setIdUruguay(fakeString);
    dtbp.getCi();
    dtbp.setCi(fakeString);
    dtbp.getSex();
    dtbp.setSex(Sex.feminine);
    dtbp.getBirthday();
    dtbp.setBirthday(fakeDate);
    dtbp.getEmail();
    dtbp.setEmail(fakeString);
    dtbp.getRoles();
    dtbp.setRoles(lS);
  }

  @Test
  public void DTBatchTest() {
    DTBatch dtb = new DTBatch(fakeLong, fakeInt, fakeDate, fakeInt, fakeInt);
    dtb =
      new DTBatch(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeLong,
        fakeInt,
        fakeDate,
        fakeInt,
        fakeInt,
        fakeString,
        fakeString
      );
    dtb.getNumber();
    dtb.setNumber(fakeLong);
    dtb.getQuantity();
    dtb.setQuantity(fakeInt);
    dtb.getCreationDate();
    dtb.setCreationDate(fakeDate);
    dtb.getExpirationDate();
    dtb.setExpirationDate(fakeInt);
    dtb.getAvailable();
    dtb.setAvailable(fakeInt);
    dtb.getaPackage();
    dtb.setaPackage(fakeString);
    dtb.getVaccine();
    dtb.setVaccine(fakeString);
  }

  @Test
  public void DTChatTest() {
    List<DTVaccinator> dtvaccinators = new ArrayList<>();
    DTChat dtc = new DTChat();
    LocalDateTime timestamp = null;
    dtc = new DTChat(fakeString, fakeString, fakeString, fakeLong, fakeString, fakeLong, true, fakeString);
    dtc.setMessage(fakeString);
    dtc.getId();
    dtc.setId(fakeString);
    dtc.getMessage();
    dtc.setMessage(fakeString);
    dtc.getTimestamp();
    dtc.setTimestamp(fakeString);
    dtc.getTransmitter_id();
    dtc.setTransmitter_id(fakeLong);
    dtc.getReceiver_id();
    dtc.setReceiver_id(fakeLong);
    dtc.getChecked();
    dtc.setChecked(true);
    dtc.getContactName();
    dtc.setContactName(fakeString);
    dtc.getConversationId();
    dtc.setConversationId(fakeString);
    dtc.toString();
  }

  @Test
  public void DTCitizenTest() {
    List<DTVaccinationAct> vaccinationActs = new ArrayList<>();
    List<DTReservation> reservations = new ArrayList<>();
    DTCitizen dtc = new DTCitizen(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeString,
      new DTPerson(),
      vaccinationActs,
      reservations,
      fakeString
    );
    dtc.getVaccinationActs();
    dtc.setVaccinationActs(vaccinationActs);
    dtc.getReservations();
    dtc.setReservations(reservations);
    dtc.getToken();
    dtc.toString();
  }

  @Test
  public void DTDataSourceTest() {
    DTDataSource dtds = new DTDataSource();
    dtds = new DTDataSource(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, fakeString, new DTRestriction());
    dtds.getName();
    dtds.setName(fakeString);
    dtds.getUrl();
    dtds.setUrl(fakeString);
    dtds.getRestriction();
    dtds.setRestriction(new DTRestriction());
  }

  @Test
  public void DTCitizenTokenTest() {
    DTCitizenToken dtct = new DTCitizenToken(fakeString, fakeString, fakeString, fakeString, fakeString, fakeString, fakeString);
    dtct.getToken();
    dtct.getName();
    dtct.getVaccinationCenterName();
    dtct.getVaccinationPostName();
    dtct.getReservationDate();
    dtct.getReservationTime();
    dtct.getVcaccinationCenterLocation();
  }

  @Test
  public void DTDiseaseTest() {
    List<DTVaccine> vaccine = new ArrayList<>();
    DTDisease dtd = new DTDisease(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, fakeString, vaccine);
    dtd = new DTDisease(fakeString, fakeString);
    dtd.getName();
    dtd.setName(fakeString);
    dtd.getSymptons();
    dtd.setSymptons(fakeString);
    dtd.getVaccine();
    dtd.setVaccine(vaccine);
  }

  @Test
  public void DTEventTest() {
    DTEvent dte = new DTEvent();
    dte.getDate();
    dte.setDate(fakeDate);
  }

  @Test
  public void DTLogisticTest() {
    DTLogistic dtl = new DTLogistic();
    dtl = new DTLogistic(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeDate, new DTLogisticPartner(), BatchState.Arrived);
    dtl.getLogisticPartner();
    dtl.setLogisticPartner(new DTLogisticPartner());
    dtl.getTypeEvent();
    dtl.setTypeEvent(BatchState.Storaged);
  }

  @Test
  public void DTLogisticPartnerTest() {
    List<DTIot> iot = new ArrayList<>();
    List<DTLogistic> dtLogistics = new ArrayList<>();
    List<DTPackage> packages = new ArrayList<>();
    DTLogisticPartner dtl = new DTLogisticPartner(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      iot,
      dtLogistics
    );
    dtl =
      new DTLogisticPartner(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        iot,
        dtLogistics,
        packages
      );
    dtl.getName();
    dtl.setName(fakeString);
    dtl.getBusinessName();
    dtl.setBusinessName(fakeString);
    dtl.getRut();
    dtl.setRut(fakeString);
    dtl.getPhone();
    dtl.setPhone(fakeString);
    dtl.getEmail();
    dtl.setEmail(fakeString);
    dtl.getReference();
    dtl.setReference(fakeString);
    dtl.setIot(iot);
    dtl.getIot();
    dtl.getLogistics();
    dtl.setLogistics(dtLogistics);
    dtl.getPassword();
    dtl.setPassword(fakeString);
    dtl.getPackages();
  }

  @Test
  public void DTNewPersonTest() {
    DTNewPerson dtnp = new DTNewPerson(fakeString, fakeString, Sex.feminine, fakeDate, fakeString, fakeString);
    dtnp =
      new DTNewPerson(
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        Sex.feminine,
        fakeDate,
        fakeString,
        fakeString
      );
    dtnp.getName();
    dtnp.setName(fakeString);
    dtnp.getSurname();
    dtnp.setSurname(fakeString);
    dtnp.getLastname();
    dtnp.setLastname(fakeString);
    dtnp.getSecondlastname();
    dtnp.setSecondlastname(fakeString);
    dtnp.setSex(Sex.masculine);
  }

  @Test
  public void DTPackageTest() {
    DTPackage dtp = new DTPackage(fakeLong, fakeLong);
    dtp = new DTPackage(fakeLong, fakeLong, PackageState.InTransit);
    dtp =
      new DTPackage(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeLong,
        fakeLong,
        PackageState.Delivered,
        new ArrayList<>(),
        fakeString,
        new ArrayList<>(),
        fakeString,
        fakeString
      );
    dtp.getPackageNumber();
    dtp.getPackageState();
    dtp.setPackageNumber(fakeLong);
    dtp.getQuantity();
    dtp.getVaccinationActs();
    dtp.setVaccinationActs(new ArrayList<>());
    dtp.getVaccination();
    dtp.setVaccination(new ArrayList<>());
  }

  @Test
  public void DTPersonTest() {
    DTPerson dtp = new DTPerson(
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      Sex.feminine,
      fakeDate,
      fakeString,
      new ArrayList<>()
    );
    dtp = new DTPerson(fakeString, fakeString, fakeString, fakeString, Sex.feminine, fakeDate, fakeString);
    dtp = new DTPerson(fakeString, fakeString, fakeString, fakeString, fakeString, fakeString, Sex.feminine, fakeDate, fakeString);
    dtp.getSecondlastname();
    dtp.setSecondlastname(fakeString);
    dtp.getName();
    dtp.setName(fakeString);
    dtp.getSurname();
    dtp.setSurname(fakeString);
    dtp.getLastname();
    dtp.setLastname(fakeString);
    dtp.getIdUruguay();
    dtp.setIdUruguay(fakeString);
    dtp.getCi();
    dtp.setCi(fakeString);
    dtp.getSex();
    dtp.getEmail();
    dtp.getRoles();
    dtp.getBirthday();
    dtp.setRoles(new ArrayList<>());
  }

  @Test
  public void DTReservationTest() {
    DTReservation dtr = new DTReservation(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      ReservationState.canceled
    );
    dtr.getVaccinationPost();
    dtr.setVaccinationPost(fakeString);
    dtr.getReservationState();
    dtr.setReservationState(ReservationState.confirmed);
    dtr.toString();
  }

  @Test
  public void DTReservationSendTest() {
    DTReservationSend dtrs = new DTReservationSend(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeLong,
      fakeLong,
      fakeLong,
      fakeString,
      //fakeLong,
      //fakeLong,
      fakeString,
      fakeString,
      fakeString,
      ReservationState.rejected
    );
    dtrs.getVaccinatorCenterId();
    dtrs.getScheduleId();
    dtrs.getVaccinationPostId();
    //dtrs.getCitizenId();
    dtrs.getCitizenName();
    dtrs.getPersonId();
    dtrs.getReservationDate();
    dtrs.getReservationTime();
    dtrs.getReservationState();
  }

  @Test
  public void DTRestrictionTest() {
    DTRestriction dtr = new DTRestriction(fakeString, LogicOp.equalTo, fakeInt, fakeString, new DTDataSource());
    dtr =
      new DTRestriction(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        LogicOp.getGreaterThanOrEqual,
        fakeInt,
        fakeString,
        new ArrayList<>(),
        new ArrayList<>(),
        new DTDataSource()
      );
    dtr.getElementName();
    dtr.setElementName(fakeString);
    dtr.getLogicOperator();
    dtr.setLogicOperator(LogicOp.lesserThan);
    dtr.getValue();
    dtr.setValue(fakeInt);
    dtr.getVaccines();
    dtr.setVaccines(new ArrayList<>());
    dtr.getVaccinationPlans();
    dtr.setVaccinationPlans(new ArrayList<>());
    dtr.getDataSource();
    dtr.setDataSource(new DTDataSource());
    dtr.getDescription();
    dtr.setDescription(fakeString);
  }

  @Test
  public void DTReservationViewTest() {
    DTReservationView dtrv = new DTReservationView(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeLong,
      fakeString,
      fakeLong,
      fakeString,
      fakeString,
      fakeString,
      ReservationState.pending,
      fakeString,
      fakeLong,
      fakeString
    );
    dtrv.getReservationId();
    dtrv.getVaccinationPlanName();
    dtrv.getReservationCenterName();
    dtrv.getReservationDate();
    dtrv.getReservationTime();
    dtrv.getVaccineName();
    dtrv.getVaccinationPostId();
    dtrv.getVaccinationPostName();
    dtrv.getReservationState();
    dtrv.setVaccineName(fakeString);
    dtrv.getReservationCenterId();
    dtrv.setVaccinationPostId(fakeLong);
    dtrv.setVaccinationPostName(fakeString);
    dtrv.getVaccinationPlanId();
    dtrv.getDoses();
  }

  @Test
  public void DTRoleTest() {
    DTRole dtr = new DTRole(fakeDate, fakeDate, fakeDate, fakeString, fakeString, new DTPerson()) {};
    dtr.getName();
  }

  @Test
  public void DTScheduleTest() {
    DTSchedule dts = new DTSchedule(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeInt,
      true,
      true,
      new DTAuthority(),
      new DTVaccinationPlan(),
      new ArrayList<>(),
      new DTVaccinationCenter()
    );
    dts.setFraction(fakeInt);
    dts.isSaturday();
    dts.setSaturday(true);
    dts.isSunday();
    dts.setSunday(true);
    dts.getAuthority();
    dts.setAuthority(new DTAuthority());
    dts.getVaccinationPlan();
    dts.setVaccinationPlan(new DTVaccinationPlan());
    dts.getReservations();
    dts.setReservations(new ArrayList<>());
    dts.getVaccinationCenter();
    dts.setVaccinationCenter(new DTVaccinationCenter());
  }

  @Test
  public void DTScheduleViewTest() {
    DTScheduleView dtsv = new DTScheduleView(
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      true,
      true
    );
    dtsv.getStartDate();
    dtsv.getEndDate();
    dtsv.getStartTimeDaily();
    dtsv.getEndTimeDaily();
    dtsv.getVaccinationPlanId();
    dtsv.getVaccineName();
    dtsv.setVaccineName(fakeString);
    dtsv.getVaccinationPlan();
    dtsv.getVaccinationCenterName();
    dtsv.getSaturday();
    dtsv.getSunday();
  }

  @Test
  public void DTSendPendingPackageTest() {
    DTSendPendingPackage dtspp = new DTSendPendingPackage();
    dtspp = new DTSendPendingPackage(fakeDate, fakeDate, fakeDate, fakeString, fakeLong, PackageState.InTransit);
    dtspp.getPackageId();
    dtspp.getPackageStatus();
    dtspp.setPackageId(fakeLong);
    dtspp.setPackageStatus(PackageState.Pending);
  }

  @Test
  public void DTVaccinationTest() {
    DTVaccination dtv = new DTVaccination(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      VaccinationState.Used,
      new DTVaccinationCenter(),
      new DTPackage(),
      fakeDate
    );
    dtv.getState();
    dtv.setState(VaccinationState.Destroyed);
    dtv.getVaccinationCenter();
    dtv.setVaccinationCenter(new DTVaccinationCenter());
    dtv.getaPackage();
    dtv.setaPackage(new DTPackage());
  }

  @Test
  public void DTVaccinationActTest() {
    DTVaccinationAct dta = new DTVaccinationAct(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeString,
      new DTCitizen(),
      new DTVaccinationPost(),
      new DTPackage()
    );
    dta.getObservation();
    dta.setObservation(fakeString);
    dta.getCitizen();
    dta.setCitizen(new DTCitizen());
    dta.getVaccinationPost();
    dta.setVaccinationPost(new DTVaccinationPost());
    dta.getaPackage();
    dta.setaPackage(new DTPackage());
  }

  @Test
  public void DTVaccinationActViewTest() {
    DTVaccinationActView dtav = new DTVaccinationActView(
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString
    );
    dtav.getPersonName();
    dtav.getPersonSurname();
    dtav.getPersonLastName();
    dtav.getPersonCi();
    dtav.getPersonBirthday();
    dtav.getVaccinationActDate();
    dtav.getVaccine();
    dtav.getVaccinationCenter();
    dtav.toString();
    dtav.getDisease();
    dtav.setDisease(fakeString);
  }

  @Test
  public void DTVaccinationCenterTest() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter(fakeString, fakeString, fakeString, fakeString, fakeString);
    dtvc =
      new DTVaccinationCenter(
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>()
      );
    dtvc.getName();
    dtvc.setName(fakeString);
    dtvc.getLocation();
    dtvc.setLocation(fakeString);
    dtvc.getVaccinationPosts();
    dtvc.setVaccinationPosts(new ArrayList<>());
    dtvc.getVaccinations();
    dtvc.setVaccinations(new ArrayList<>());
    dtvc.getSchedules();
    dtvc.setSchedules(new ArrayList<>());
    dtvc.getVaccinationPlans();
    dtvc.setVaccinationPlans(new ArrayList<>());
    dtvc.getPackages();
    dtvc.setPackages(new ArrayList<>());
    dtvc.getLongitude();
    dtvc.setLongitude(fakeString);
    dtvc.getLatitude();
    dtvc.setLatitude(fakeString);
    dtvc.getPassword();
    dtvc.setPassword(fakeString);
  }

  @Test
  public void DTVaccinationPlanTest() {
    DTVaccinationPlan dtvp = new DTVaccinationPlan(fakeString, fakeString, fakeString, fakeInt, fakeString);
    dtvp =
      new DTVaccinationPlan(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeInt,
        fakeString,
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>(),
        new DTVaccine()
      );
    dtvp.getName();
    dtvp.setName(fakeString);
    dtvp.getStartDate();
    dtvp.setStartDate(fakeString);
    dtvp.getEndDate();
    dtvp.setEndDate(fakeString);
    dtvp.getVaccineQuantity();
    dtvp.setVaccineQuantity(fakeInt);
    dtvp.getAuthority();
    dtvp.setAuthority(fakeString);
    dtvp.getVaccinationCenters();
    dtvp.setVaccinationCenters(new ArrayList<>());
    dtvp.getSchedule();
    dtvp.setSchedule(new ArrayList<>());
    dtvp.getRestriction();
    dtvp.setRestriction(new ArrayList<>());
    dtvp.getaPackage();
    dtvp.setaPackage(new ArrayList<>());
    dtvp.getVaccine();
    dtvp.setVaccine(new DTVaccine());
  }

  @Test
  public void DTVaccinationPostTest() {
    DTVaccinationPost dtvp = new DTVaccinationPost(fakeString, fakeString);
    dtvp =
      new DTVaccinationPost(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        new ArrayList<>(),
        fakeString,
        new ArrayList<>(),
        new ArrayList<>()
      );
    dtvp.getName();
    dtvp.setName(fakeString);
    dtvp.getObservation();
    dtvp.setObservation(fakeString);
    dtvp.getVaccinators();
    dtvp.setVaccinators(new ArrayList<>());
    dtvp.getVaccinationCenter();
    dtvp.setVaccinationCenter(fakeString);
    dtvp.getReservations();
    dtvp.setReservations(new ArrayList<>());
    dtvp.getVaccinationActs();
    dtvp.setVaccinationActs(new ArrayList<>());
  }

  @Test
  public void DTVaccinationPostNewTest() {
    DTVaccinationPostNew dtvpn = new DTVaccinationPostNew(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeString,
      fakeString,
      fakeLong
    );
    dtvpn = new DTVaccinationPostNew(fakeString, fakeString, fakeLong);
    dtvpn.getName();
    dtvpn.setName(fakeString);
    dtvpn.getObservation();
    dtvpn.setObservation(fakeString);
  }

  @Test
  public void DTVaccinatorTest() {
    DTVaccinator dtv = new DTVaccinator(
      fakeLong,
      fakeDate,
      fakeDate,
      fakeDate,
      fakeString,
      fakeString,
      new DTPerson(),
      new DTVaccinationPost()
    );
    dtv.getDtvaccinationPost();
    dtv.setDtvaccinationPost(new DTVaccinationPost());
  }

  @Test
  public void DTVaccinatorViewTest() {
    DTVaccinatorView dtvv = new DTVaccinatorView(
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString
    );
    dtvv.getScheduleId();
    dtvv.getVaccinationPlanId();
    dtvv.getVaccinationPostName();
    dtvv.getVaccineName();
    dtvv.setVaccineName(fakeString);
    dtvv.getVaccinationCenterId();
    dtvv.getVaccinationPostId();
    dtvv.getVaccinationPostName();
    dtvv.getDateStart();
    dtvv.getDateEnd();
    dtvv.getTimeStart();
    dtvv.getTimeEnd();
    dtvv.getVaccinationCenterName();
    dtvv.getVaccinationPlanName();
  }

  @Test
  public void DTVaccineTest() {
    DTVaccine dtv = new DTVaccine(fakeString, fakeInt, fakeFloat, fakeInt, fakeInt);
    dtv = new DTVaccine(fakeString, fakeInt, fakeFloat, fakeInt, fakeInt, fakeString);
    dtv =
      new DTVaccine(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeInt,
        fakeFloat,
        fakeInt,
        fakeInt,
        new ArrayList<>(),
        new ArrayList<>(),
        fakeString,
        fakeString
      );
    dtv.getName();
    dtv.setName(fakeString);
    dtv.getDoseQuantity();
    dtv.setDoseQuantity(fakeInt);
    dtv.getTemperature();
    dtv.setTemperature(fakeFloat);
    dtv.getMonthQuantity();
    dtv.setMonthQuantity(fakeInt);
    dtv.getInmunity();
    dtv.setInmunity(fakeInt);
    dtv.getBatches();
    dtv.setBatches(new ArrayList<>());
    dtv.getRestrictions();
    dtv.setRestrictions(new ArrayList<>());
    dtv.getLaboratory();
    dtv.getDisease();
  }

  @Test
  public void DTRestrictionAPITest() {
    DTRestrictionAPI dtra = new DTRestrictionAPI();
    dtra = new DTRestrictionAPI(fakeString, fakeString, fakeInt);
    dtra.getElementName();
    dtra.setElementName(fakeString);
    dtra.getLogicOperator();
    dtra.setLogicOperator(fakeString);
    dtra.getValue();
    dtra.setValue(fakeInt);
  }

  @Test
  public void DtMonitorDateTest() {
    DtMonitorDate dtmdt = new DtMonitorDate();
    dtmdt = new DtMonitorDate(fakeString, fakeLong, fakeLong, fakeLong, fakeLong, fakeLong);
    dtmdt.getDate();
    dtmdt.setDate(fakeString);
    dtmdt.getDoses1();
    dtmdt.setDoses1(fakeLong);
    dtmdt.getDoses2();
    dtmdt.setDoses2(fakeLong);
    dtmdt.getDoses3();
    dtmdt.setDoses3(fakeLong);
    dtmdt.getDoses4();
    dtmdt.setDoses4(fakeLong);
    dtmdt.getDoses5();
    dtmdt.setDoses5(fakeLong);
    dtmdt.toString();
    dtmdt.setDoses(1, 1L);
    dtmdt.setDoses(2, 1L);
    dtmdt.setDoses(3, 1L);
    dtmdt.setDoses(4, 1L);
    dtmdt.setDoses(5, 1L);
  }

  @Test
  public void DTVaccineReportTest() {
    DTVaccineReport dtvr = new DTVaccineReport();
    dtvr =
      new DTVaccineReport(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeLong,
        fakeInt,
        fakeLong,
        fakeLong,
        fakeString
      );
    dtvr = new DTVaccineReport(fakeString, fakeString, fakeString, fakeString, fakeLong, fakeInt, fakeLong, fakeLong, fakeString);
    dtvr.getVaccineName();
    dtvr.setVaccineName(fakeString);
    dtvr.getPlanName();
    dtvr.setPlanName(fakeString);
    dtvr.getCenterName();
    dtvr.setCenterName(fakeString);
    dtvr.getDiseaseName();
    dtvr.setDiseaseName(fakeString);
    dtvr.getBatchNumber();
    dtvr.setBatchNumber(fakeLong);
    dtvr.getBatchAvailable();
    dtvr.setBatchAvailable(fakeInt);
    dtvr.getpQuantity();
    dtvr.setpQuantity(fakeLong);
    dtvr.getpNumber();
    dtvr.setpNumber(fakeLong);
    dtvr.getpState();
    dtvr.setpState(fakeString);
  }

  @Test
  public void DTVaccinationPlanMonitorTest() {
    DTVaccinationPlanMonitor dtvpm = new DTVaccinationPlanMonitor(
      fakeLong,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeString,
      fakeLong,
      fakeLong,
      fakeLong,
      fakeLong,
      fakeLong
    );
    dtvpm.getvPlanId();
    dtvpm.getvPlanName();
    dtvpm.setvPlanName(fakeString);
    dtvpm.getVaccineName();
    dtvpm.setVaccineName(fakeString);
    dtvpm.getDiseaseName();
    dtvpm.setDiseaseName(fakeString);
    dtvpm.getToday();
    dtvpm.getDoses1();
    dtvpm.getDoses2();
    dtvpm.getDoses3();
    dtvpm.getDoses4();
    dtvpm.getDoses5();
    dtvpm.getDates();
    dtvpm.getTotalDosesAsssigned();
    dtvpm.setTotalDosesAsssigned(fakeString);
    dtvpm.getDoseQuantity();
    dtvpm.setDoseQuantity(fakeInt);
    dtvpm.toString();
  }

  @Test
  public void DTVaccinatorsVcenterTest() {
    DTVaccinatorsVcenter dtvvc = new DTVaccinatorsVcenter();
    dtvvc = new DTVaccinatorsVcenter(fakeLong, fakeString, fakeString, fakeString, fakeString, fakeString);
    dtvvc.getId();
    dtvvc.getvPostName();
    dtvvc.getDocumentNumber();
    dtvvc.getName();
    dtvvc.getVaccinationPostId();
    dtvvc.getDate();
  }
}
