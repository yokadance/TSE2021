package webservice.rest;

import DTO.DTVaccine;
import IController.IVaccineController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/vaccie")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Vaccine" })
public class restVaccine {

  @EJB
  IVaccineController iVaccineController;

  @POST
  @Path("/saveVaccine")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveVaccine(DTVaccine dtVaccine) {
    iVaccineController.saveVaccine(dtVaccine);
  }

  @GET
  @Path("/listVaccines")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccine> listVaccines() {
    return iVaccineController.listVaccines();
  }

  @GET
  @Path("/getVaccine/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTVaccine getVaccineById(@PathParam("id") Long id) {
    return iVaccineController.getVaccineById(id);
  }

  @DELETE
  @Path("/deleteVaccine/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteVaccine(@PathParam("id") Long id) {
    iVaccineController.deleteVaccine(id);
  }
}
