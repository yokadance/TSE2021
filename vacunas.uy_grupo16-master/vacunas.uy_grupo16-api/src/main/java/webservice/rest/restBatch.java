package webservice.rest;

import DTO.DTBatch;
import IController.IBatchController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/batch")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Batch" })
public class restBatch {

  @EJB
  IBatchController iBatchController;

  @POST
  @Path("/saveBatch")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveBatch(DTBatch dtBatch) {
    iBatchController.saveBatch(dtBatch);
  }

  @GET
  @Path("/lBatch")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTBatch> lBatch() {
    return iBatchController.getBatches();
  }

  @GET
  @Path("/getBatch/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTBatch getBatch(@PathParam("id") Long id) {
    return iBatchController.getByIdBatch(id);
  }

  @PUT
  @Path("/deleteBatch/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteBatch(@PathParam("id") Long id) {
    iBatchController.deleteBatch(id);
  }
}
