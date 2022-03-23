package webservice.rest;

import DTO.*;
import IController.*;
import enumerations.ReservationState;
import enumerations.Sex;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/util")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Util" })
public class restUtil {

  @EJB
  ILaboratoryController iLaboratoryController;

  @EJB
  IPersonController iPersonController;

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
  ICitizenController iCitizenController;

  @EJB
  IDiseaseController iDiseaseController;

  @EJB
  IVaccineController iVaccineController;

  @EJB
  IBatchController iBatchController;

  @EJB
  IPackageController iPackageController;

  @GET
  @Path("/crearDatos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void lPersons() {
    // ----> CREO PERSONAS CON ROLES <----

    Date date = new Date();
    DTNewPerson dtPersona1 = new DTNewPerson("idGubUy1", "43332221", Sex.feminine, date, "femenine@gmail.com", "citizen");
    //    DTNewPerson dtPersona2 = new DTNewPerson("idGubUy2", "65554443", Sex.masculine, date, "masculine@gmail.com", "vaccinator");
    //   DTNewPerson dtPersona3 = new DTNewPerson("idGubUy2", "54443332", Sex.masculine, date, "masculine@gmail.com", "administrator");
    //   DTNewPerson dtPersona4 = new DTNewPerson("idGubUy2", "12223334", Sex.masculine, date, "masculine@gmail.com", "authority");
    iPersonController.createPerson(dtPersona1);
    //  iPersonController.createPerson(dtPersona3);
    //  iPersonController.createPerson(dtPersona4);
    System.out.println("Id del ciudadanto : " + iCitizenController.getCitizenIdByCi("43332221"));
    System.out.println("Id del ciudadanto : " + iCitizenController.getCitizenIdByCi("43332231"));

    // ----> CREO VACUNATORIO <----

    DTVaccinationCenter dtVaccCenter = new DTVaccinationCenter();
    dtVaccCenter.setName("Vaccination Center I");
    dtVaccCenter.setLocation("Calle 1234");
    iVaccinationCenterController.newVaccinationCenter(dtVaccCenter);

    // ----> CREO PUESTOS DE VACUNACION <----

    DTVaccinationPost dtVaccPost1 = new DTVaccinationPost();
    dtVaccPost1.setName("Puesto I");
    dtVaccPost1.setObservation("Observacion I");
    dtVaccPost1.setVaccinationCenter("Vacination Center I");

    DTVaccinationPost dtVaccPost2 = new DTVaccinationPost();
    dtVaccPost2.setName("Puesto II");
    dtVaccPost2.setObservation("Observacion II");
    dtVaccPost2.setVaccinationCenter("Vacination Center I");

    DTVaccinationPost dtVaccPost3 = new DTVaccinationPost();
    dtVaccPost3.setName("Puesto III");
    dtVaccPost3.setObservation("Observacion III");
    dtVaccPost3.setVaccinationCenter("Vacination Center I");

    iVaccinationPostController.saveVaccinationPost(dtVaccPost1);
    iVaccinationPostController.saveVaccinationPost(dtVaccPost2);
    iVaccinationPostController.saveVaccinationPost(dtVaccPost3);

    // ----> CREO PLAN DE VACUNACION <----

    DTVaccinationPlan dtVaccPlan = new DTVaccinationPlan();
    dtVaccPlan.setName("Plan de Vacunacion I");
    dtVaccPlan.setStartDate("2021-05-15");
    dtVaccPlan.setEndDate("2021-05-15");
    dtVaccPlan.setVaccineQuantity(2);
    dtVaccPlan.setAuthority("Autoridad I");
    List<String> listVaccCenter = new ArrayList<>();
    listVaccCenter.add("1");
    dtVaccPlan.setVaccinationCenters(listVaccCenter);
    iVaccinationPlanController.saveVaccinationPlan(dtVaccPlan);

    // ----> CREO AGENDA <----

    DTSchedule dtSch = new DTSchedule();
    dtSch.setStartDate("2021-05-15");
    dtSch.setEndDate("2021-05-15");
    dtSch.setStartTimeDaily("08:00");
    dtSch.setEndTimeDaily("20:00");
    dtSch.setFraction(15);
    dtSch.setSaturday(true);
    dtSch.setSunday(false);
    //
    ////    DTAuthority dtAuth = iAuthorityController.getByIdAuthority(3L);
    ////    dtSch.setAuthority(dtAuth);
    //
    DTVaccinationPlan dtVaccPlanX = iVaccinationPlanController.getVaccinationPlanById(1L);
    dtSch.setVaccinationPlan(dtVaccPlanX);

    DTVaccinationCenter dtVaccCenterX = iVaccinationCenterController.getVaccinationCenterById(1L);
    dtSch.setVaccinationCenter(dtVaccCenterX);

    iScheduleController.saveSchedule(dtSch);

    // ----> CREO RESERVATION <----

    DTReservation dtRes = new DTReservation();
    dtRes.setVaccinationCenter("1");
    dtRes.setSchedule("1");
    dtRes.setVaccinationPost("Puesto I");
    dtRes.setCitizen("43332221");
    dtRes.setDate("2020-04-22");
    dtRes.setTime("09:15");
    dtRes.setReservationState(ReservationState.pending);

    iReservationController.createReservation2(dtRes);

    DTReservation dtRes1 = new DTReservation();
    dtRes1.setVaccinationCenter("1");
    dtRes1.setSchedule("1");
    dtRes1.setVaccinationPost("Puesto I");
    dtRes1.setCitizen("1");
    dtRes1.setDate("2020-04-22");
    dtRes1.setTime("09:15");
    dtRes1.setReservationState(ReservationState.pending);
    iReservationController.createReservation2(dtRes1);

    // ----> CREO ENFERMEDAD <----

    DTDisease dtDis = new DTDisease();
    dtDis.setName("Covid");
    dtDis.setSymptons("Dolor de cabeza");
    iDiseaseController.saveDisease(dtDis);

    // ----> CREO LABORATORIO <----

    List<DTVaccine> vList2 = new ArrayList<>();
    iLaboratoryController.newLaboratory("Laboratorio I", "user I", "Laboratorio I", "labo_frusia@gmail.com", "55 342 556", vList2);

    // --------> CREO VACUNA <------------

    DTVaccine dtVaccine = new DTVaccine();
    dtVaccine.setDisease("1");
    dtVaccine.setLaboratory("1");
    dtVaccine.setInmunity(1);
    dtVaccine.setMonthQuantity(2);
    dtVaccine.setName("Vaccine 1");
    dtVaccine.setTemperature(2);
    iVaccineController.saveVaccine(dtVaccine);

    // Creo Lote

    DTBatch dtBatch = new DTBatch();
    dtBatch.setAvailable(1);
    dtBatch.setNumber(1);
    dtBatch.setQuantity(2);
    dtBatch.setNumber(1L);
    dtBatch.setVaccine("1");
    iBatchController.saveBatch(dtBatch);

    // Creo Paquete

    DTPackage dtPackage = new DTPackage();
    dtPackage.setBatch("1");
    dtPackage.setPackageNumber(123L);
    iPackageController.savePackage(dtPackage);

    iPackageController.addPackagetoVC(1L, 1L);
    iPackageController.addPackagetoVP(1L, 1L);

    iReservationController.reservationChangeState(1L, ReservationState.rejected);
    // ----> CREO RESTRICCION <----

    //    DTRestriction dtRestriction = new DTRestriction();
    //    dtRestriction.setElementName("Restriccion I");
    //    dtRestriction.setLogicOperator(LogicOp.lesserThan);
    //    dtRestriction.setValue(100);
    //
    //    List<DTVaccine> vList3 = new ArrayList<>();
    //    dtRestriction.setVaccines(vList3);
    //
    //    List<DTVaccinationPlan> dtVaccinationPlanList = new ArrayList<>();
    //    dtVaccinationPlanList.add(iVaccinationPlanController.getVaccinationPlanById(1L));
    //
    //    iRestrictionController.saveRestriction(dtRestriction);
  }
}
