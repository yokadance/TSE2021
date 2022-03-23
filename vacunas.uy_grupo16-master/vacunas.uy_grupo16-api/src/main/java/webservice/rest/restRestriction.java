package webservice.rest;

import DTO.DTRestriction;
import IController.IRestrictionController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/restriction")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Restriction" })
public class restRestriction {

  @EJB
  IRestrictionController iRestrictionController;

  @POST
  @Path("/saveRestriction")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveRestriction(DTRestriction dtRestriction) {
    iRestrictionController.saveRestriction(dtRestriction);
  }

  @GET
  @Path("/lRestriction")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTRestriction> lRestriction() {
    return iRestrictionController.getRestrictions();
  }

  @GET
  @Path("/getRestriction/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTRestriction getRestriction(@PathParam("id") Long id) {
    return iRestrictionController.getByIdRestriction(id);
  }

  @GET
  @Path("/getRestrictionsByPlan/{idVaccinationPlan}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTRestriction> getRestrictionsByVaccinationPlan(@PathParam("idVaccinationPlan") Long idVaccinationPlan) {
    return iRestrictionController.getRestrictionsByVaccinationPlan(idVaccinationPlan);
  }

  @PUT
  @Path("/deleteRestriction/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteRestriction(@PathParam("id") Long id) {
    iRestrictionController.deleteRestriction(id);
  }

  @POST
  @Path("/ageRestriction/{ci}/{idVaccinationPlan}")
  @ApiOperation(value = "Return True, False or Service unavailable")
  @ApiResponses(
    {
      @ApiResponse(code = 200, message = "TRUE"),
      @ApiResponse(code = 406, message = "FALSE"),
      @ApiResponse(code = 503, message = "Service unavailable")
    }
  )
  public Response AgeRestriction(@PathParam("ci") int ci, @PathParam("idVaccinationPlan") Long idVaccinationPlan) {
    String response = iRestrictionController.callAgeRestrictionApi(ci, idVaccinationPlan);
    if ((response != null)) {
      return Response.status(Response.Status.ACCEPTED).entity(response).build();
    } else return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(response).build();
  }
}
