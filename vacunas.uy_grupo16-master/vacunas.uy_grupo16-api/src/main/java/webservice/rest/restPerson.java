package webservice.rest;

import DTO.DTNewPerson;
import DTO.DTPerson;
import IController.IPersonController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "person" })
public class restPerson {

  @EJB
  IPersonController iPersonController;

  @POST
  @Path("/savePerson")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void savePerson(DTPerson dtPerson) {
    /*  DTAdministrator dtr =new DTAdministrator();
        List<DTRole> dtRoleList = new ArrayList<DTRole>();
        dtPerson.setRoles(dtRoleList); */
    iPersonController.savePerson(dtPerson);
  }

  @POST
  @Path("/createPerson")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public int createPerson(DTNewPerson dtNewPerson) {
    return iPersonController.createPerson(dtNewPerson);
  }

  @GET
  @Path("/lPersons")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTPerson> lPersons() {
    return iPersonController.getPersons();
  }

  @GET
  @Path("/getPerson/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTPerson getPerson(@PathParam("id") Long id) {
    return iPersonController.getPerson(id);
  }

  @PUT
  @Path("/deletePerson/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deletePerson(@PathParam("id") Long id) {
    iPersonController.deletePerson(id);
  }

  @GET
  @Path("/getPersonByCi/{ci}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTPerson getPersonByCi(@PathParam("ci") String ci) throws Exception {
    return iPersonController.getPersonByCI(ci);
  }
}
