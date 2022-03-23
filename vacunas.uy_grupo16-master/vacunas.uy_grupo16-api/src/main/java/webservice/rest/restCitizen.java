package webservice.rest;

import DTO.DTCitizen;
import IController.ICitizenController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/citizen")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Citizen" })
public class restCitizen {

  @EJB
  ICitizenController iCitizenController;

  @POST
  @Path("/saveCitizen")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveCitizen(DTCitizen dtCitizen) {
    iCitizenController.saveCitizen(dtCitizen);
  }

  @GET
  @Path("/lCitizen")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTCitizen> lCitizen() {
    return iCitizenController.getCitizens();
  }

  @GET
  @Path("/getCitizen/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTCitizen getCitizen(@PathParam("id") Long id) {
    return iCitizenController.getByIdCitizen(id);
  }

  @PUT
  @Path("/deleteCitizen/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deletePerson(@PathParam("id") Long id) {
    iCitizenController.deleteCitizen(id);
  }

  @PUT
  @Path("/setToken/{ci}/{token}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response setToken(@PathParam("ci") String ci, @PathParam("token") String token) throws IOException {
    try {
      iCitizenController.setToken(ci, token);
      return Response.status(Response.Status.OK).build();
    } catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }
}
