package webservice.rest;

import DTO.DTAdministrator;
import IController.IAdministratorController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/administrator")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Administrator" })
public class restAdministrator {

  @EJB
  IAdministratorController iAdministratorController;

  @POST
  @Path("/saveAdministrator")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveAdministrator(DTAdministrator dtAdministrator) {
    iAdministratorController.saveAdministrator(dtAdministrator);
  }

  @GET
  @Path("/lAdministrator")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTAdministrator> lAdministrator() {
    return iAdministratorController.getAdministrators();
  }

  @GET
  @Path("/getAdministrator/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTAdministrator getAdministrator(@PathParam("id") Long id) {
    return iAdministratorController.getByIdAdministrator(id);
  }

  @PUT
  @Path("/deleteAdministrator/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteAdministrator(@PathParam("id") Long id) {
    iAdministratorController.deleteAdministrator(id);
  }
}
