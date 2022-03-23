package webservice.rest;

import DTO.DTDisease;
import IController.IDiseaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/disease")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Disease" })
public class restDisease {

  @RequestScoped
  @EJB
  IDiseaseController iDiseaseController;

  @GET
  @Path("/listDiseaseById/{id}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTDisease getDiseaseById(@PathParam("id") Long id) {
    return iDiseaseController.getDiseaseById(id);
  }

  @GET
  @Path("/listDiseases")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTDisease> listDiseases() {
    return iDiseaseController.listDiseases();
  }

  @POST
  @Path("/saveDisease")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveDisease(DTDisease dtDisease) {
    iDiseaseController.saveDisease(dtDisease);
  }

  @DELETE
  @Path("/deleteDisease/{id}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteDisease(@PathParam("id") Long id) {
    iDiseaseController.deleteDisease(id);
  }
}
