package webservice.rest;

import DTO.DTVaccination;
import IController.IVaccinationController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/vaccionation")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "vaccionation" })
public class restVaccination {

  @RequestScoped
  @EJB
  IVaccinationController iVaccinationController;

  @GET
  @Path("/listVaccinationById/{id}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTVaccination getVaccinationById(@PathParam("id") Long id) {
    return iVaccinationController.getVaccinationById(id);
  }

  @GET
  @Path("/listVaccinations")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccination> getVaccinationsList() {
    return iVaccinationController.listVaccination();
  }

  @DELETE
  @Path("/deleteVaccination")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteVaccination(Long id) {
    iVaccinationController.deleteVaccination(id);
  }

  @POST
  @Path("/addVaccination")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void addVaccination(DTVaccination dtVaccination) {
    iVaccinationController.newVaccination(dtVaccination);
  }

  @PUT
  @Path("/saveVaccination")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveVaccination(DTVaccination dtVaccination) {
    iVaccinationController.saveVaccination(dtVaccination);
  }
}
