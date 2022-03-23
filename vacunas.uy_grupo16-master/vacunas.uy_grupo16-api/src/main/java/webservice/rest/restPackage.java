package webservice.rest;

import DTO.DTPackage;
import IController.IPackageController;
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
@Path("/package")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Package" })
public class restPackage {

  @EJB
  IPackageController iPackageController;

  @POST
  @Path("/savePackage")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void savePackage(DTPackage dtPackage) {
    iPackageController.savePackage(dtPackage);
  }

  @GET
  @Path("/lPackage")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTPackage> lPackage() {
    return iPackageController.getPackages();
  }

  @GET
  @Path("/getPackage/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTPackage getPackage(@PathParam("id") Long id) {
    return iPackageController.getByIdPackage(id);
  }

  @PUT
  @Path("/deletePackage/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deletePackage(@PathParam("id") Long id) {
    iPackageController.deletePackage(id);
  }
}
