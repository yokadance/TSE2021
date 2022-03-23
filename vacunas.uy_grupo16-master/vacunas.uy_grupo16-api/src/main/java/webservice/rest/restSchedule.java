package webservice.rest;

import DTO.DTSchedule;
import DTO.DTScheduleView;
import IController.IScheduleController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/schedule")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "schedule" })
public class restSchedule {

  @RequestScoped
  @EJB
  IScheduleController iScheduleController;

  @GET
  @Path("/listScheduleById/{id}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTSchedule getScheduleById(@PathParam("id") Long id) {
    return iScheduleController.getScheduleById(id);
  }

  @GET
  @Path("/listSchedules")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTSchedule> getSchedules() {
    return iScheduleController.listSchedules();
  }

  @POST
  @Path("/saveSchedule")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveSchedule(DTSchedule dtSchedule) {
    iScheduleController.saveSchedule(dtSchedule);
  }

  @DELETE
  @Path("/deleteSchedule/{id}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteSchedule(@PathParam("id") Long id) {
    iScheduleController.deleteSchedule(id);
  }

  @GET
  @Path("/schedulesbyVCandVP/{idVP}/{idVC}")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTSchedule> schedulesbyVCandVP(@PathParam("idVP") Long idVP, @PathParam("idVC") Long idVC) {
    return iScheduleController.SchedulesbyVCandVP(idVP, idVC);
  }

  @GET
  @Path("/getNextScchedules")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTScheduleView> getNextSchedules() {
    return iScheduleController.getNextSchedules();
  }
}
