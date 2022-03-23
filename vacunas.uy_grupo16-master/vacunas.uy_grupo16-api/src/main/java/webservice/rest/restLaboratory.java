package webservice.rest;

import DTO.DTLaboratory;
import IController.ILaboratoryController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/laboratory")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "laboratory" })
public class restLaboratory {

  @Inject
  ILaboratoryController iLaboratoryController;

  //    @POST
  //    @Path("/addLaboratory")
  //    @Produces(MediaType.APPLICATION_JSON)
  //    public void addLaboratory(DTLaboratory dtLaboratory) {
  //       iLaboratoryController.newLaboratory(dtLaboratory.getName(), dtLaboratory.getUserid(), dtLaboratory.getOrigin(), dtLaboratory.getEmail(), dtLaboratory.getPhone(), dtLaboratory.getDTVaccines());
  //    }

  //    @GET
  //    @Path("/listLaboratory")
  //    @ApiOperation(value = "Add laboratory")
  //    @ApiResponses({
  //            @ApiResponse(code = 200, message = "VPI SUCCESS")})
  //    @Produces(MediaType.APPLICATION_JSON)

  @GET
  @Path("/listLaboratory")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTLaboratory listLaboratory() {
    return new DTLaboratory(0L, new Date(), null, null, "test_USER", "test_NAME", "test_ORIGIN", "test_EMAIL", "test_PHONE", null);
  }

  @GET
  @Path("/listLaboratories")
  @ApiOperation(value = "Return a List<DTLaboratory>")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTLaboratory> listLaboratories() {
    return iLaboratoryController.listLaboratories();

  }



}
