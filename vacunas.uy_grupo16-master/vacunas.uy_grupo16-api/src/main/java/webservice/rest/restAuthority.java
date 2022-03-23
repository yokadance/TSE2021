package webservice.rest;

import DTO.DTAuthority;
import IController.IAuthorityController;
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
@Path("/authority")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Authority" })
public class restAuthority {

  @EJB
  IAuthorityController iAuthorityController;

  @POST
  @Path("/saveAauthority")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveAuthority(DTAuthority dtAuthority) {
    iAuthorityController.saveAuthority(dtAuthority);
  }

  @GET
  @Path("/lAuthority")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTAuthority> lAuthority() {
    return iAuthorityController.getAuthority();
  }

  @GET
  @Path("/getAuthority/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTAuthority getAuthority(@PathParam("id") Long id) {
    return iAuthorityController.getByIdAuthority(id);
  }

  @PUT
  @Path("/deleteAuthority/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteAuthority(@PathParam("id") Long id) {
    iAuthorityController.deleteAuthority(id);
  }
}
