package webservice.rest;

import DTO.*;
import IController.*;
import entities.Person;
import enumerations.LogicOp;
import enumerations.PackageState;
import enumerations.ReservationState;
import enumerations.Sex;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/testdata")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Test Data" })
public class arestTestData {

  @RequestScoped
  @EJB
  IDiseaseController iDiseaseController;

  @EJB
  IVaccinationCenterController iVaccinationCenterController;

  @EJB
  IVaccinationPostController iVaccinationPostController;

  @EJB
  IVaccinationPlanController iVaccinationPlanController;

  @EJB
  IScheduleController iScheduleController;

  @EJB
  IReservationController iReservationController;

  @EJB
  IPersonController iPersonController;

  @EJB
  ILaboratoryController iLaboratoryController;

  @EJB
  IVaccineController iVaccineController;

  @EJB
  IBatchController iBatchController;

  @EJB
  IPackageController iPackageController;

  @EJB
  IRoleController iRoleController;

  @EJB
  IDataSourceController iDataSourceController;

  @EJB
  IRestrictionController iRestrictionController;

  @GET
  @ApiOperation(value = "Ingreso personas 1000")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void testData() throws Exception {
    for (int i = 0; i <= 250; i++) {
      int intSex = (int) (Math.random() * ((2 - 0) + 1)) + 0;

      Sex sex = Sex.values()[intSex];

      Integer a = (int) (Math.random() * ((60000000 - 10000000) + 1)) + 10000000;

      String ci = a.toString();

      Integer day = getRandomNumberInRange(1, 28);
      Integer month = getRandomNumberInRange(1, 12);
      Integer year = getRandomNumberInRange(1938, 2007);

      String birth = year.toString() + "-";

      if (month < 10) {
        birth += "0" + month.toString();
      } else {
        birth += month.toString();
      }

      birth += "-";

      if (day < 10) {
        birth += "0" + day.toString();
      } else {
        birth += day.toString();
      }

      Date dateBirth = new SimpleDateFormat("yyyy-MM-dd").parse(birth);

      //            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      //            LocalDate birthday = LocalDate.parse(birth, dtf);

      System.out.println("dateBirth ---------- " + dateBirth.toString());

      DTPerson dtNP = new DTPerson("", getName(intSex), getName(intSex), getLastname(), getLastname(), ci, sex, dateBirth, "mail@mail.com");

      iPersonController.savePerson(dtNP);

      DTCitizen dtCitizen1 = new DTCitizen();

      dtCitizen1.setDtPerson(iPersonController.getPersonByCI(ci));
      iRoleController.saveAnyRole(dtCitizen1);
    }

    // planes: 7 8 9
    // centros del plan 7 - 1,2
    // centros del plan 8 - 1,2
    // centros del plan 9 - 1

    //Reservas de las 250 personas para el plan 7 (Uruguay vs COVID-19)
    List<DTPerson> personList = iPersonController.getPersons();

    Integer a = 0;
    String strH = "";

    for (DTPerson dtp : personList) {
      DTReservation dtReservation = new DTReservation();
      DTSchedule dtSchedule = new DTSchedule();

      a = getRandomNumberInRange(1, 2);
      dtReservation.setVaccinationCenter(a.toString());

      if (a == 1) {
        dtReservation.setSchedule("9");
        dtSchedule = iScheduleController.getScheduleById(9L);
      }

      if (a == 2) {
        dtReservation.setSchedule("9");
        dtSchedule = iScheduleController.getScheduleById(9L);
      }

      dtReservation.setCitizen(dtp.getCi());

      a = getRandomNumberInRange(9, 20);

      if (a < 10) {
        strH = "0" + a.toString();
      } else {
        strH = a.toString();
      }

      strH += ':';

      a = getRandomNumberInRange(1, 4);

      switch (a) {
        case 1:
          strH += "00";
          break;
        case 2:
          strH += "15";
          break;
        case 3:
          strH += "30";
          break;
        case 4:
          strH += "45";
          break;
      }

      LocalDate reservationDate = LocalDate.parse("2021-05-01");

      a = getRandomNumberInRange(0, 70);

      reservationDate = reservationDate.plusDays(a);

      dtReservation.setDate(reservationDate.toString());

      System.out.println("a ---------- " + a.toString());
      System.out.println("ResDate ---------- " + reservationDate.toString());

      dtReservation.setTime(strH);

      dtReservation.setReservationState(ReservationState.pending);

      iReservationController.createReservation2(dtReservation);
    }

    //Reservas de las 250 personas para el plan 8 (Uruguay vs Sarampion)
    a = 0;
    strH = "";

    for (DTPerson dtp : personList) {
      DTReservation dtReservation = new DTReservation();
      DTSchedule dtSchedule = new DTSchedule();

      a = getRandomNumberInRange(1, 2);
      dtReservation.setVaccinationCenter(a.toString());

      if (a == 1) {
        dtReservation.setSchedule("11");
        dtSchedule = iScheduleController.getScheduleById(11L);
      }

      if (a == 2) {
        dtReservation.setSchedule("11");
        dtSchedule = iScheduleController.getScheduleById(11L);
      }

      dtReservation.setCitizen(dtp.getCi());

      a = getRandomNumberInRange(9, 21);

      if (a < 10) {
        strH = "0" + a.toString();
      } else {
        strH = a.toString();
      }

      strH += ':';

      a = getRandomNumberInRange(1, 4);

      switch (a) {
        case 1:
          strH += "00";
          break;
        case 2:
          strH += "15";
          break;
        case 3:
          strH += "30";
          break;
        case 4:
          strH += "45";
          break;
      }

      LocalDate reservationDate = LocalDate.parse("2021-05-01");

      a = getRandomNumberInRange(0, 70);

      reservationDate = reservationDate.plusDays(a);

      dtReservation.setDate(reservationDate.toString());

      System.out.println("a ---------- " + a.toString());
      System.out.println("ResDate ---------- " + reservationDate.toString());

      dtReservation.setTime(strH);

      dtReservation.setReservationState(ReservationState.pending);

      iReservationController.createReservation2(dtReservation);
    }

    //Reservas de las 250 personas para el plan 15 (Uruguay vs Gripe)
    a = 0;
    strH = "";

    for (DTPerson dtp : personList) {
      DTReservation dtReservation = new DTReservation();
      DTSchedule dtSchedule = new DTSchedule();

      a = 1;
      dtReservation.setVaccinationCenter(a.toString());

      if (a == 1) {
        dtReservation.setSchedule("19");
        dtSchedule = iScheduleController.getScheduleById(19L);
      }

      dtReservation.setCitizen(dtp.getCi());

      a = getRandomNumberInRange(10, 18);

      if (a < 10) {
        strH = "0" + a.toString();
      } else {
        strH = a.toString();
      }

      strH += ':';

      a = getRandomNumberInRange(1, 4);

      switch (a) {
        case 1:
          strH += "00";
          break;
        case 2:
          strH += "15";
          break;
        case 3:
          strH += "30";
          break;
        case 4:
          strH += "45";
          break;
      }

      LocalDate reservationDate = LocalDate.parse("2021-03-01");

      a = getRandomNumberInRange(0, 130);

      reservationDate = reservationDate.plusDays(a);

      dtReservation.setDate(reservationDate.toString());

      System.out.println("a ---------- " + a.toString());
      System.out.println("ResDate ---------- " + reservationDate.toString());

      dtReservation.setTime(strH);

      dtReservation.setReservationState(ReservationState.pending);

      iReservationController.createReservation2(dtReservation);
    }
    // centros

    //const reserv = {
    //        vaccinationCenter: valueVaccCenter,
    //        schedule: selectedSchulde,
    //        citizen: ci,
    //        date: valueDaySelect,
    //        time: strTime,
    //        reservationState: 0,
    //}

    //    //PERSON - CITIZEN
    //    DTPerson dtNP = new DTPerson(
    //      "uid-42002146-u",
    //      "Federico",
    //      "Javier",
    //      "Sierra",
    //      "Dodo",
    //      "42002416",
    //      Sex.other,
    //      new Date(),
    //      "fede@mail.com"
    //    );
    //    DTPerson dtNP2 = new DTPerson(
    //      "uid-43948063-u",
    //      "Gonzalo",
    //      "Raúl",
    //      "Santa María",
    //      "Silvera",
    //      "43948063",
    //      Sex.other,
    //      new Date(),
    //      "gonza@mail.com"
    //    );
    //    DTPerson dtNP3 = new DTPerson(
    //      "uid-41002203-u",
    //      "Otra",
    //      "Persona",
    //      "Con",
    //      "Apellido",
    //      "41002203",
    //      Sex.other,
    //      new Date(),
    //      "otro@mail.com"
    //    );
    //    DTPerson dtNP4 = new DTPerson(
    //      "uid-44005006-u",
    //      "Cuarta",
    //      "Persona",
    //      "Con",
    //      "Apellido",
    //      "44005006",
    //      Sex.other,
    //      new Date(),
    //      "cuarto@mail.com"
    //    );
    //    DTPerson dtNP5 = new DTPerson(
    //      "uid-45556667-u",
    //      "Quinta",
    //      "Persona",
    //      "Con",
    //      "Apellido",
    //      "45556667",
    //      Sex.other,
    //      new Date(),
    //      "quinto@mail.com"
    //    );
    //
    //    iPersonController.savePerson(dtNP);
    //    iPersonController.savePerson(dtNP2);
    //    iPersonController.savePerson(dtNP3);
    //    iPersonController.savePerson(dtNP4);
    //    iPersonController.savePerson(dtNP5);
    //
    //    DTCitizen dtCitizen1 = new DTCitizen();
    //
    //    dtCitizen1.setDtPerson(iPersonController.getPersonByCI("42002416"));
    //    iRoleController.saveAnyRole(dtCitizen1);
    //
    //    DTVaccinator dtVaccinator = new DTVaccinator();
    //    dtVaccinator.setDtPerson(iPersonController.getPersonByCI("42002416"));
    //    iRoleController.saveAnyRole(dtVaccinator);
    //
    //    DTCitizen dtCitizen2 = new DTCitizen();
    //    dtCitizen2.setDtPerson(iPersonController.getPersonByCI(dtNP2.getCi()));
    //    iRoleController.saveAnyRole(dtCitizen2);
    //    DTCitizen dtCitizen3 = new DTCitizen();
    //    dtCitizen3.setDtPerson(iPersonController.getPersonByCI(dtNP3.getCi()));
    //    iRoleController.saveAnyRole(dtCitizen3);
    //    DTCitizen dtCitizen4 = new DTCitizen();
    //    dtCitizen4.setDtPerson(iPersonController.getPersonByCI(dtNP4.getCi()));
    //    iRoleController.saveAnyRole(dtCitizen4);
    //    /*    DTVaccinator dtVaccinator = new DTVaccinator();
    //    dtVaccinator.setDtPerson(iPersonController.getPersonByCI(dtNP5.getCi()));
    //    iRoleController.saveAnyRole(dtVaccinator);*/
    //
    //    // VACCINATION CENTER
    //    DTVaccinationCenter dtVaccinationCenter = new DTVaccinationCenter(
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Vaccination center 1",
    //      "Location vacc center 1",
    //      "-56.1555485",
    //      "-34.8630582",
    //      "",
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>()
    //    );
    //    DTVaccinationCenter dtVaccinationCenter2 = new DTVaccinationCenter(
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Vaccination center 2",
    //      "Location vacc center 2",
    //      "-56.1588583",
    //      "-34.8634510",
    //      "",
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>()
    //    );
    //    DTVaccinationCenter dtVaccinationCenter3 = new DTVaccinationCenter(
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Vaccination center 3",
    //      "Location vacc center 3",
    //      "-56.1534358",
    //      "-34.8915404",
    //      "",
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>()
    //    );
    //    DTVaccinationCenter dtVaccinationCenter4 = new DTVaccinationCenter(
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Vaccination center 4",
    //      "Location vacc center 4",
    //      "-56.1512471",
    //      "-34.8915448",
    //      "",
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>()
    //    );
    //    DTVaccinationCenter dtVaccinationCenter5 = new DTVaccinationCenter(
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Vaccination center 5",
    //      "Location vacc center 5",
    //      "-56.1665404",
    //      "-34.8221939",
    //      "",
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>()
    //    );
    //
    //    Long newVacCen1 = iVaccinationCenterController.newVaccinationCenter(dtVaccinationCenter);
    //    System.out.println("newVacCen1: " + newVacCen1.toString());
    //
    //    Long newVacCen2 = iVaccinationCenterController.newVaccinationCenter(dtVaccinationCenter2);
    //    System.out.println("newVacCen2: " + newVacCen2.toString());
    //
    //    Long newVacCen3 = iVaccinationCenterController.newVaccinationCenter(dtVaccinationCenter3);
    //    System.out.println("newVacCen3: " + newVacCen3.toString());
    //
    //    Long newVacCen4 = iVaccinationCenterController.newVaccinationCenter(dtVaccinationCenter4);
    //    System.out.println("newVacCen4: " + newVacCen4.toString());
    //
    //    Long newVacCen5 = iVaccinationCenterController.newVaccinationCenter(dtVaccinationCenter5);
    //    System.out.println("newVacCen5: " + newVacCen5.toString());
    //
    //    // VACCINATION POST
    //    DTVaccinationPostNew DTVaccinationPostNew = new DTVaccinationPostNew("Vaccination post 1", "Observation 1", newVacCen1);
    //    DTVaccinationPostNew DTVaccinationPostNew2 = new DTVaccinationPostNew("Vaccination post 2", "Observation 2", newVacCen1);
    //
    //    DTVaccinationPostNew DTVaccinationPostNew3 = new DTVaccinationPostNew("Vaccination post 3", "Observation 3", newVacCen2);
    //    DTVaccinationPostNew DTVaccinationPostNew4 = new DTVaccinationPostNew("Vaccination post 4", "Observation 4", newVacCen2);
    //
    //    DTVaccinationPostNew DTVaccinationPostNew5 = new DTVaccinationPostNew("Vaccination post 5", "Observation 5", newVacCen3);
    //    DTVaccinationPostNew DTVaccinationPostNew6 = new DTVaccinationPostNew("Vaccination post 6", "Observation 6", newVacCen3);
    //
    //    DTVaccinationPostNew DTVaccinationPostNew7 = new DTVaccinationPostNew("Vaccination post 7", "Observation 7", newVacCen4);
    //    DTVaccinationPostNew DTVaccinationPostNew8 = new DTVaccinationPostNew("Vaccination post 8", "Observation 8", newVacCen4);
    //
    //    DTVaccinationPostNew DTVaccinationPostNew9 = new DTVaccinationPostNew("Vaccination post 9", "Observation 9", newVacCen5);
    //    DTVaccinationPostNew DTVaccinationPostNew10 = new DTVaccinationPostNew("Vaccination post 10", "Observation 10", newVacCen5);
    //
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew);
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew2);
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew3);
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew4);
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew5);
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew6);
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew7);
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew8);
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew9);
    //    iVaccinationPostController.createVaccinationPost(DTVaccinationPostNew10);
    //
    //    // LABORATORIES
    //    DTLaboratory dtLaboratory = new DTLaboratory(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Laboratorio 1",
    //      "Origen 1",
    //      "email1@email.com",
    //      "1111111111",
    //      new ArrayList<>()
    //    );
    //    DTLaboratory dtLaboratory2 = new DTLaboratory(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Laboratorio 2",
    //      "Origen 2",
    //      "email2@email.com",
    //      "1111111112",
    //      new ArrayList<>()
    //    );
    //    DTLaboratory dtLaboratory3 = new DTLaboratory(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Laboratorio 3",
    //      "Origen 3",
    //      "email3@email.com",
    //      "1111111113",
    //      new ArrayList<>()
    //    );
    //    DTLaboratory dtLaboratory4 = new DTLaboratory(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Laboratorio 4",
    //      "Origen 4",
    //      "email4@email.com",
    //      "1111111114",
    //      new ArrayList<>()
    //    );
    //    DTLaboratory dtLaboratory5 = new DTLaboratory(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Laboratorio 5",
    //      "Origen 5",
    //      "email5@email.com",
    //      "1111111115",
    //      new ArrayList<>()
    //    );
    //
    //    Long newLab = iLaboratoryController.newLaboratory(
    //      "Laboratorio 1",
    //      null,
    //      "Origin 1",
    //      "email1@mail.com",
    //      "1111111111",
    //      new ArrayList<>()
    //    );
    //    Long newLab2 = iLaboratoryController.newLaboratory(
    //      "Laboratorio 2",
    //      null,
    //      "Origin 2",
    //      "email2@mail.com",
    //      "1111111112",
    //      new ArrayList<>()
    //    );
    //    Long newLab3 = iLaboratoryController.newLaboratory(
    //      "Laboratorio 3",
    //      null,
    //      "Origin 3",
    //      "email3@mail.com",
    //      "1111111113",
    //      new ArrayList<>()
    //    );
    //    Long newLab4 = iLaboratoryController.newLaboratory(
    //      "Laboratorio 4",
    //      null,
    //      "Origin 4",
    //      "email4@mail.com",
    //      "1111111114",
    //      new ArrayList<>()
    //    );
    //    Long newLab5 = iLaboratoryController.newLaboratory(
    //      "Laboratorio 5",
    //      null,
    //      "Origin 5",
    //      "email5@mail.com",
    //      "1111111115",
    //      new ArrayList<>()
    //    );
    //
    //    // DISEASES
    //    DTDisease dtDisease = new DTDisease(null, new Date(), null, null, null, "Enfermedad 1", "Sintoma 1", new ArrayList<>());
    //    DTDisease dtDisease2 = new DTDisease(null, new Date(), null, null, null, "Enfermedad 2", "Sintoma 2", new ArrayList<>());
    //    DTDisease dtDisease3 = new DTDisease(null, new Date(), null, null, null, "Enfermedad 3", "Sintoma 3", new ArrayList<>());
    //    DTDisease dtDisease4 = new DTDisease(null, new Date(), null, null, null, "Enfermedad 4", "Sintoma 4", new ArrayList<>());
    //    DTDisease dtDisease5 = new DTDisease(null, new Date(), null, null, null, "Enfermedad 5", "Sintoma 5", new ArrayList<>());
    //
    //    Long newDis = iDiseaseController.saveDisease(dtDisease);
    //    Long newDis2 = iDiseaseController.saveDisease(dtDisease2);
    //    Long newDis3 = iDiseaseController.saveDisease(dtDisease3);
    //    Long newDis4 = iDiseaseController.saveDisease(dtDisease4);
    //    Long newDis5 = iDiseaseController.saveDisease(dtDisease5);
    //
    //    // VACCINES
    //    DTVaccine dtVaccine = new DTVaccine(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "NameVaccine 1",
    //      1,
    //      1.11F,
    //      0,
    //      11,
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      newLab.toString(),
    //      newDis.toString()
    //    );
    //
    //    DTVaccine dtVaccine2 = new DTVaccine(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "NameVaccine 2",
    //      2,
    //      2.22F,
    //      22,
    //      22,
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      newLab2.toString(),
    //      newDis2.toString()
    //    );
    //
    //    DTVaccine dtVaccine3 = new DTVaccine(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "NameVaccine 3",
    //      3,
    //      3.33F,
    //      33,
    //      33,
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      newLab3.toString(),
    //      newDis3.toString()
    //    );
    //
    //    DTVaccine dtVaccine4 = new DTVaccine(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "NameVaccine 4",
    //      2,
    //      4.44F,
    //      44,
    //      44,
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      newLab4.toString(),
    //      newDis4.toString()
    //    );
    //
    //    DTVaccine dtVaccine5 = new DTVaccine(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "NameVaccine 5",
    //      1,
    //      5.55F,
    //      55,
    //      55,
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      newLab5.toString(),
    //      newDis5.toString()
    //    );
    //
    //    Long newVacc = iVaccineController.saveVaccine(dtVaccine);
    //    dtVaccine.setId(newVacc);
    //    DTVaccinationPlan dtVaccPlan4 = new DTVaccinationPlan(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Plan de vacunación 5",
    //      "2021-01-01",
    //      "2021-12-31",
    //      999,
    //      "Authority 1",
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      dtVaccine
    //    );
    //    iVaccinationPlanController.saveVaccinationPlan(dtVaccPlan4);
    //    dtDisease.setId(newDis);
    //    iVaccineController.addDiseaseToVaccine(newVacc, dtDisease);
    //    dtLaboratory.setId(newLab);
    //    iVaccineController.addLaboratoryToVaccine(newVacc, dtLaboratory);
    //
    //    Long newVacc2 = iVaccineController.saveVaccine(dtVaccine2);
    //    dtVaccine2.setId(newVacc2);
    //    dtDisease2.setId(newDis2);
    //    iVaccineController.addDiseaseToVaccine(newVacc2, dtDisease2);
    //    dtLaboratory2.setId(newLab2);
    //    iVaccineController.addLaboratoryToVaccine(newVacc2, dtLaboratory2);
    //
    //    Long newVacc3 = iVaccineController.saveVaccine(dtVaccine3);
    //    dtVaccine3.setId(newVacc3);
    //    dtDisease3.setId(newDis3);
    //    iVaccineController.addDiseaseToVaccine(newVacc3, dtDisease3);
    //    dtLaboratory3.setId(newLab3);
    //    iVaccineController.addLaboratoryToVaccine(newVacc3, dtLaboratory3);
    //
    //    Long newVacc4 = iVaccineController.saveVaccine(dtVaccine4);
    //    dtVaccine4.setId(newVacc4);
    //    dtDisease4.setId(newDis4);
    //    iVaccineController.addDiseaseToVaccine(newVacc4, dtDisease4);
    //    dtLaboratory4.setId(newLab4);
    //    iVaccineController.addLaboratoryToVaccine(newVacc4, dtLaboratory4);
    //
    //    Long newVacc5 = iVaccineController.saveVaccine(dtVaccine5);
    //    dtVaccine5.setId(newVacc5);
    //    dtDisease5.setId(newDis5);
    //    iVaccineController.addDiseaseToVaccine(newVacc5, dtDisease5);
    //    dtLaboratory5.setId(newLab5);
    //    iVaccineController.addLaboratoryToVaccine(newVacc5, dtLaboratory5);
    //
    //    //VACCINATION PLAN
    //    DTVaccinationPlan dtVaccPlan = new DTVaccinationPlan(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Plan de vacunación 1",
    //      "2021-01-01",
    //      "2021-12-31",
    //      999,
    //      "Authority 1",
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      dtVaccine
    //    );
    //    List<String> listVaccCenter = new ArrayList<>();
    //    listVaccCenter.add(newVacCen1.toString());
    //    listVaccCenter.add(newVacCen2.toString());
    //    dtVaccPlan.setVaccinationCenters(listVaccCenter);
    //
    //    DTVaccinationPlan dtVaccPlan2 = new DTVaccinationPlan(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Plan de vacunación 2",
    //      "2021-01-01",
    //      "2021-12-31",
    //      999,
    //      "Authority 1",
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      dtVaccine2
    //    );
    //    List<String> listVaccCenter2 = new ArrayList<>();
    //    listVaccCenter2.add(newVacCen3.toString());
    //    listVaccCenter2.add(newVacCen4.toString());
    //    dtVaccPlan2.setVaccinationCenters(listVaccCenter2);
    //
    //    DTVaccinationPlan dtVaccPlan3 = new DTVaccinationPlan(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "Plan de vacunación 3",
    //      "2021-01-01",
    //      "2021-12-31",
    //      999,
    //      "Authority 1",
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      new ArrayList<>(),
    //      dtVaccine3
    //    );
    //    List<String> listVaccCenter3 = new ArrayList<>();
    //    listVaccCenter3.add(newVacCen5.toString());
    //    dtVaccPlan3.setVaccinationCenters(listVaccCenter3);
    //
    //    Long newVaccPlan1 = iVaccinationPlanController.saveVaccinationPlan(dtVaccPlan);
    //    Long newVaccPlan2 = iVaccinationPlanController.saveVaccinationPlan(dtVaccPlan2);
    //    Long newVaccPlan3 = iVaccinationPlanController.saveVaccinationPlan(dtVaccPlan3);
    //
    //    // SCHEDULE
    //    DTSchedule dtSch = new DTSchedule(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "2021-01-01",
    //      "2021-12-31",
    //      "08:00",
    //      "20:00",
    //      15,
    //      true,
    //      false,
    //      null,
    //      null,
    //      new ArrayList<>(),
    //      null
    //    );
    //    dtSch.setVaccinationPlan(iVaccinationPlanController.getVaccinationPlanById(newVaccPlan1));
    //    dtSch.setVaccinationCenter(iVaccinationCenterController.getVaccinationCenterById(newVacCen1));
    //
    //    DTSchedule dtSch2 = new DTSchedule(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "2021-01-01",
    //      "2021-12-31",
    //      "08:00",
    //      "20:00",
    //      15,
    //      true,
    //      false,
    //      null,
    //      null,
    //      new ArrayList<>(),
    //      null
    //    );
    //    dtSch2.setVaccinationPlan(iVaccinationPlanController.getVaccinationPlanById(newVaccPlan2));
    //    dtSch2.setVaccinationCenter(iVaccinationCenterController.getVaccinationCenterById(newVacCen2));
    //
    //    DTSchedule dtSch3 = new DTSchedule(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      "2021-01-01",
    //      "2021-12-31",
    //      "08:00",
    //      "20:00",
    //      15,
    //      true,
    //      false,
    //      null,
    //      null,
    //      new ArrayList<>(),
    //      null
    //    );
    //    dtSch3.setVaccinationPlan(iVaccinationPlanController.getVaccinationPlanById(newVaccPlan3));
    //    dtSch3.setVaccinationCenter(iVaccinationCenterController.getVaccinationCenterById(newVacCen3));
    //
    //    Long sch1 = iScheduleController.saveSchedule(dtSch);
    //    Long sch2 = iScheduleController.saveSchedule(dtSch2);
    //    Long sch3 = iScheduleController.saveSchedule(dtSch3);
    //
    //    // RESERVATION
    //    DTReservation dtRes = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "42002416",
    //      "2021-05-28",
    //      "09:15",
    //      ReservationState.pending
    //    );
    //    DTReservation dtRes1 = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "43948063",
    //      "2021-05-28",
    //      "09:15",
    //      ReservationState.pending
    //    );
    //    DTReservation dtRes2 = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "41002203",
    //      "2021-05-28",
    //      "09:15",
    //      ReservationState.pending
    //    );
    //    DTReservation dtRes3 = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "44005006",
    //      "2021-05-28",
    //      "09:15",
    //      ReservationState.pending
    //    );
    //    DTReservation dtRes4 = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "45556667",
    //      "2021-05-28",
    //      "09:15",
    //      ReservationState.pending
    //    );
    //
    //    DTReservation dtRes5 = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "42002416",
    //      "2021-05-28",
    //      "09:30",
    //      ReservationState.pending
    //    );
    //    DTReservation dtRes6 = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "43948063",
    //      "2021-05-28",
    //      "09:30",
    //      ReservationState.pending
    //    );
    //    DTReservation dtRes7 = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "41002203",
    //      "2021-05-28",
    //      "09:30",
    //      ReservationState.pending
    //    );
    //    DTReservation dtRes8 = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "44005006",
    //      "2021-05-28",
    //      "09:30",
    //      ReservationState.pending
    //    );
    //    DTReservation dtRes9 = new DTReservation(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      newVacCen1.toString(),
    //      sch1.toString(),
    //      "Vaccination post 1",
    //      "45556667",
    //      "2021-05-28",
    //      "09:30",
    //      ReservationState.pending
    //    );
    //
    //    for (int i = 1; i <= 7; i++) {
    //      iReservationController.createReservation2(dtRes);
    //      iReservationController.createReservation2(dtRes1);
    //      iReservationController.createReservation2(dtRes2);
    //      iReservationController.createReservation2(dtRes3);
    //      iReservationController.createReservation2(dtRes4);
    //
    //      iReservationController.createReservation2(dtRes5);
    //      iReservationController.createReservation2(dtRes6);
    //      iReservationController.createReservation2(dtRes7);
    //      iReservationController.createReservation2(dtRes8);
    //      iReservationController.createReservation2(dtRes9);
    //    }
    //
    //    //BATCH
    //    DTBatch dtBatch = new DTBatch(null, new Date(), null, null, null, 123456, 111111, new Date(), 111, 111111, "", newVacc.toString());
    //    DTBatch dtBatch2 = new DTBatch(null, new Date(), null, null, null, 234567, 222222, new Date(), 222, 222222, "", newVacc2.toString());
    //    DTBatch dtBatch3 = new DTBatch(null, new Date(), null, null, null, 345678, 333333, new Date(), 333, 333333, "", newVacc3.toString());
    //    DTBatch dtBatch4 = new DTBatch(null, new Date(), null, null, null, 456789, 444444, new Date(), 444, 444444, "", newVacc4.toString());
    //    DTBatch dtBatch5 = new DTBatch(null, new Date(), null, null, null, 567890, 555555, new Date(), 555, 555555, "", newVacc5.toString());
    //
    //    Long newBatch = iBatchController.saveBatch(dtBatch);
    //    Long newBatch2 = iBatchController.saveBatch(dtBatch2);
    //    Long newBatch3 = iBatchController.saveBatch(dtBatch3);
    //    Long newBatch4 = iBatchController.saveBatch(dtBatch4);
    //    Long newBatch5 = iBatchController.saveBatch(dtBatch5);
    //
    //    //PACKAGE
    //    DTPackage dtPackage = new DTPackage(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      123456L,
    //      123456L,
    //      PackageState.Pending,
    //      new ArrayList<>(),
    //      newBatch.toString(),
    //      new ArrayList<>(),
    //      newVaccPlan1.toString(),
    //      newVacCen1.toString()
    //    );
    //    DTPackage dtPackage2 = new DTPackage(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      234567L,
    //      234567L,
    //      PackageState.Pending,
    //      new ArrayList<>(),
    //      newBatch2.toString(),
    //      new ArrayList<>(),
    //      newVaccPlan2.toString(),
    //      newVacCen2.toString()
    //    );
    //    DTPackage dtPackage3 = new DTPackage(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      345678L,
    //      345678L,
    //      PackageState.Pending,
    //      new ArrayList<>(),
    //      newBatch3.toString(),
    //      new ArrayList<>(),
    //      newVaccPlan3.toString(),
    //      newVacCen3.toString()
    //    );
    //    DTPackage dtPackage4 = new DTPackage(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      456789L,
    //      456789L,
    //      PackageState.Pending,
    //      new ArrayList<>(),
    //      newBatch4.toString(),
    //      new ArrayList<>(),
    //      newVaccPlan1.toString(),
    //      newVacCen4.toString()
    //    );
    //    DTPackage dtPackage5 = new DTPackage(
    //      null,
    //      new Date(),
    //      null,
    //      null,
    //      null,
    //      567890L,
    //      567890L,
    //      PackageState.Pending,
    //      new ArrayList<>(),
    //      newBatch5.toString(),
    //      new ArrayList<>(),
    //      newVaccPlan2.toString(),
    //      newVacCen5.toString()
    //    );
    //
    //    Long newPack = iPackageController.savePackage(dtPackage);
    //    iPackageController.addPackagetoVC(newPack, newVacCen1);
    //    iPackageController.addPackagetoVP(newPack, newVaccPlan1);
    //
    //    Long newPack2 = iPackageController.savePackage(dtPackage2);
    //    iPackageController.addPackagetoVC(newPack2, newVacCen2);
    //    iPackageController.addPackagetoVP(newPack2, newVaccPlan2);
    //
    //    Long newPack3 = iPackageController.savePackage(dtPackage3);
    //    iPackageController.addPackagetoVC(newPack3, newVacCen3);
    //    iPackageController.addPackagetoVP(newPack3, newVaccPlan3);
    //
    //    Long newPack4 = iPackageController.savePackage(dtPackage4);
    //    iPackageController.addPackagetoVC(newPack4, newVacCen4);
    //    iPackageController.addPackagetoVP(newPack4, newVaccPlan1);
    //
    //    Long newPack5 = iPackageController.savePackage(dtPackage5);
    //    iPackageController.addPackagetoVC(newPack5, newVacCen5);
    //    iPackageController.addPackagetoVP(newPack5, newVaccPlan2);
    //
    //    DTDataSource dts1 = new DTDataSource();
    //    dts1.setName("AGESIC");
    //    dts1.setUrl("https://vacunatorio1.azurewebsites.net/medicalinfo-0.0.1-SNAPSHOT/restrictions");
    //
    //    DTDataSource dts2 = new DTDataSource();
    //    dts2.setName("Historia Clínica Electrónica");
    //    dts2.setUrl("https://vacunatorio1.azurewebsites.net/medicalinfo-0.0.1-SNAPSHOT/restrictions");
    //
    //    Long datasource1 = iDataSourceController.saveDataSource(dts1);
    //    Long datasource2 = iDataSourceController.saveDataSource(dts2);
    //
    //    DTRestriction dtr1 = new DTRestriction();
    //    dtr1.setElementName("edad");
    //    dtr1.setLogicOperator(LogicOp.greaterThan);
    //    dtr1.setValue(18);
    //    dtr1.setDescription("Mayor a 18 años");
    //    dtr1.setDataSource(iDataSourceController.getDataSourceById(datasource1));
    //
    //    DTRestriction dtr2 = new DTRestriction();
    //    dtr2.setElementName("DangerousAllergy");
    //    dtr2.setLogicOperator(LogicOp.equalTo);
    //    dtr2.setValue(1);
    //    dtr2.setDescription("Posee una alergia peligrosa");
    //    dtr2.setDataSource(iDataSourceController.getDataSourceById(datasource2));
    //
    //    Long restriction1 = iRestrictionController.saveRestriction(dtr1);
    //    Long restriction2 = iRestrictionController.saveRestriction(dtr2);
  }

  private String getName(int sexo) {
    //sexo: feminine(0), masculine(1), other(2);
    String ret = null;

    String[] nombres;

    //        String[] nombres = {"Andrea", "David", "Baldomero", "Balduino", "Baldwin", "Baltasar", "Barry", "Bartolo",
    //                "Bartolomé", "Baruc", "Baruj", "Candelaria", "Cándida", "Canela", "Caridad", "Carina", "Carisa",
    //                "Caritina", "Carlota", "Baltazar", "Martín", "Martina", "Diego", "Matías", "Micaela", "Verónica",
    //                "Serrana", "Manuela", "Francisca"};

    switch (sexo) {
      case 0: //feminine(0)
        nombres =
          new String[] {
            "Andrea",
            "Candelaria",
            "Cándida",
            "Canela",
            "Caridad",
            "Carina",
            "Carisa",
            "Caritina",
            "Carlota",
            "Martina",
            "Micaela",
            "Verónica",
            "Serrana",
            "Manuela",
            "Francisca"
          };
        break;
      case 1: //masculine(1)
        nombres =
          new String[] {
            "Andrea",
            "David",
            "Baldomero",
            "Balduino",
            "Baldwin",
            "Baltasar",
            "Barry",
            "Bartolo",
            "Bartolomé",
            "Baruc",
            "Baruj",
            "Baltazar",
            "Martín",
            "Diego",
            "Matías"
          };
        break;
      case 2: //other(2)
        nombres =
          new String[] {
            "Andrea",
            "David",
            "Baldomero",
            "Balduino",
            "Baldwin",
            "Baltasar",
            "Barry",
            "Bartolo",
            "Bartolomé",
            "Baruc",
            "Baruj",
            "Candelaria",
            "Cándida",
            "Canela",
            "Caridad",
            "Carina",
            "Carisa",
            "Caritina",
            "Carlota",
            "Baltazar",
            "Martín",
            "Martina",
            "Diego",
            "Matías",
            "Micaela",
            "Verónica",
            "Serrana",
            "Manuela",
            "Francisca"
          };
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + sexo);
    }

    return nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))];
  }

  private String getLastname() {
    String[] apellidos = {
      "Gomez",
      "Guerrero",
      "Cardenas",
      "Cardiel",
      "Cardona",
      "Cardoso",
      "Cariaga",
      "Carillo",
      "Carion",
      "Castiyo",
      "Castorena",
      "Castro",
      "Grande",
      "Grangenal",
      "Grano",
      "Grasia",
      "Griego",
      "Grigalva"
    };

    return apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
  }

  private static int getRandomNumberInRange(int min, int max) {
    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    return (int) (Math.random() * ((max - min) + 1)) + min;
  }
}
