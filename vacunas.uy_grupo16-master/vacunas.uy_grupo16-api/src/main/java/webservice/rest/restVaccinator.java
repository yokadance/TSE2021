package webservice.rest;

import DTO.DTVaccinator;
import DTO.DTVaccinatorView;
import IController.IVaccinatorController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/vaccinator")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Vaccinator" })
public class restVaccinator {

  @EJB
  IVaccinatorController iVaccinatorController;

  @POST
  @Path("/saveVaccinator")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveVaccinator(DTVaccinator dtVaccinator) {
    iVaccinatorController.saveVaccinator(dtVaccinator);
  }

  @GET
  @Path("/lVaccinator")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccinator> lVaccinator() {
    return iVaccinatorController.getVaccinators();
  }

  @GET
  @Path("/getVaccinator/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTVaccinator getVaccinator(@PathParam("id") Long id) {
    return iVaccinatorController.getByIdVaccinator(id);
  }

  @GET
  @Path("/getVaccinatorIdByCi/{ci}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTVaccinator getVaccinatorIdByCi(@PathParam("ci") String ci) {
    return iVaccinatorController.getVaccinatorByCi(ci);
  }

  @PUT
  @Path("/deleteVaccinator/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteVaccinator(@PathParam("id") Long id) {
    iVaccinatorController.deleteVaccinator(id);
  }

  @PUT
  @Path("/setVCtoV/{idV}/{idVP}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteVaccinator(@PathParam("idV") Long idV, @PathParam("idVP") Long idVP) {
    iVaccinatorController.setVCtoV(idV, idVP);
  }

  @PUT
  @Path("/getVaccinatorDatabyCi/{ci}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccinatorView> getVaccinatorDatabyCi(@PathParam("ci") String ci) {
    return iVaccinatorController.getVaccinatorDatabyCi(ci);
  }
}
