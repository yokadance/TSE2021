package webservice.rest;

import DTO.DTCitizen;
import DTO.DTRole;
import IController.IRoleController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Role" })
public class restRole implements Serializable {

  @EJB
  IRoleController iRoleController;

  @POST
  @Path("/saveRole")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveRole(DTCitizen dtCitizen) {
    iRoleController.saveRole(dtCitizen);
  }

  @GET
  @Path("/lRole")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTRole> lPersons() {
    return iRoleController.getRoles();
  }
}
