package webservice.rest;

import DTO.DTVaccinationAct;
import DTO.DTVaccinationActView;
import IController.IVaccinationActController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/VaccinationAct")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "VaccinationAct" })
public class restVaccinationAct {

  @EJB
  IVaccinationActController iVaccinationActController;

  @POST
  @Path("/saveVaccinationAct")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveVaccinationAct(DTVaccinationAct dtVaccinationAct) {
    iVaccinationActController.saveVaccinationAct(dtVaccinationAct);
  }

  @GET
  @Path("/listVaccinationAct")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccinationAct> listVaccinationActs() {
    return iVaccinationActController.listVaccinationActs();
  }

  @GET
  @Path("/getVaccinationActById/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTVaccinationAct getVaccinationActById(@PathParam("id") Long id) {
    return iVaccinationActController.getVaccinationActById(id);
  }

  @DELETE
  @Path("/deleteVaccinationAct/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteVaccinationAct(@PathParam("id") Long id) {
    iVaccinationActController.deleteVaccinationAct(id);
  }

  @GET
  @Path("/vaccinationActByCi/{ci}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccinationActView> vaccinationActByCi(@PathParam("ci") String ci) {
    return iVaccinationActController.vaccinationActByCi(ci);
  }
}
