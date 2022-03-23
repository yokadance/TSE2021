package webservice.rest;

import DTO.DTVaccinationCenter;
import IController.IVaccinationCenterController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/vaccionationCenter")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "vaccionationCenter" })
public class restVaccinationCenter {

  @RequestScoped
  @EJB
  IVaccinationCenterController iVaccinationCenterController;

  @GET
  @Path("/listVaccinationCenterById/{id}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTVaccinationCenter getVaccinationCenterById(@PathParam("id") Long id) {
    return iVaccinationCenterController.getVaccinationCenterById(id);
  }

  @GET
  @Path("/listVaccinationsCenters")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccinationCenter> getVaccinationsCenters() {
    return iVaccinationCenterController.listVaccinationCenters();
  }

  @POST
  @Path("/addVaccinationsCenters")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void addVaccinationsCenters(DTVaccinationCenter dtVaccinationCenter) {
    iVaccinationCenterController.newVaccinationCenter(dtVaccinationCenter);
  }

  @DELETE
  @Path("/deleteVaccinationCenter/{id}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteVaccinationCenter(@PathParam("id") Long id) {
    iVaccinationCenterController.deleteVaccinationCenter(id);
  }

  @GET
  @Path("/password/{password}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public String getPassword() {
    return iVaccinationCenterController.vaccinationCenterPassword();
  }

  @POST
  @Path("/validatePassword/{vid}/{password}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public boolean validatePassword(@PathParam("vid")Long vid, @PathParam("password") String password) {
    return iVaccinationCenterController.validatePassword(vid,password);
  }

}
