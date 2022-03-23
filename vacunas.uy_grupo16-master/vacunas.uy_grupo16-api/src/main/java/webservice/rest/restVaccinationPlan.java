package webservice.rest;

import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPlan;
import DTO.DTVaccinationPlanMonitor;
import IController.IVaccinationPlanController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/vaccionationPlan")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "vaccionationPlan" })
public class restVaccinationPlan {

  @RequestScoped
  @EJB
  IVaccinationPlanController iVaccinationPlanController;

  @GET
  @Path("/listVaccinationPlanById/{id}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTVaccinationPlan getVaccinationPlanById(@PathParam("id") Long id) {
    return iVaccinationPlanController.getVaccinationPlanById(id);
  }

  @GET
  @Path("/listVaccinationsPlans")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccinationPlan> getVaccinationsPlans() {
    return iVaccinationPlanController.listVaccinationPlans();
  }

  @POST
  @Path("/addVaccinationPlan")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void addVaccinationPlan(DTVaccinationPlan dtVaccinationPlan) {
    iVaccinationPlanController.saveVaccinationPlan(dtVaccinationPlan);
  }

  @DELETE
  @Path("/deleteVaccinationPlan/{id}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteVaccination(@PathParam("id") Long id) {
    iVaccinationPlanController.deleteVaccinationPlan(id);
  }

  @GET
  @Path("/vaccinationCentersByVaccinationPlan/{id}")
  @ApiOperation(value = "VaccinationCenter list by VaccinationPlan")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccinationCenter> vaccinationCentersByVaccinationPlan(@PathParam("id") Long id) {
    return iVaccinationPlanController.vaccinationCentersByVaccinationPlan(id);
  }

  @GET
  @Path("/getDataMonVPlan/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTVaccinationPlanMonitor getDataMonVPlan(@PathParam("id") Long id) {
    return iVaccinationPlanController.getDataMonVPlan(id);
  }
}
