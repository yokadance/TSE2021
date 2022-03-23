package webservice.rest;

import DTO.DTReservation;
import DTO.DTReservationSend;
import DTO.DTReservationView;
import DTO.DTavailableDate;
import IController.IReservationController;
import com.fasterxml.jackson.databind.ObjectMapper;
import enumerations.ReservationState;
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
@Path("/Reservation")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Reservation" })
public class restReservation {

  @EJB
  IReservationController iReservationController;

  private ObjectMapper mapper = new ObjectMapper();

  @POST
  @Path("/saveReservation")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveReservation(DTReservation dtReservation) {
    iReservationController.saveReservation(dtReservation);
  }

  @POST
  @Path("/createReservation")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void createReservation(DTReservation dtReservation) {
    iReservationController.createReservation(dtReservation);
  }

  @POST
  @Path("/createReservation2")
  @ApiOperation(value = "Fetches all to dos")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void createReservation2(DTReservation dtReservation) {
    iReservationController.createReservation2(dtReservation);
  }

  @GET
  @Path("/lReservation")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTReservation> lReservation() {
    return iReservationController.getReservations();
  }

  @GET
  @Path("/getReservation/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTReservation getReservation(@PathParam("id") Long id) {
    return iReservationController.getByIdReservation(id);
  }

  @PUT
  @Path("/deleteReservation/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteReservation(@PathParam("id") Long id) {
    iReservationController.deleteReservation(id);
  }

  @GET
  @Path("/getunavailableDate/{idShcedule}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTavailableDate> getunavailableDate(@PathParam("idShcedule") Long idShcedule) {
    return iReservationController.unavailableDate(idShcedule);
  }

  @GET
  @Path("/getunavailableTime/{reservationDate}/{idShcedule}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTavailableDate> getunavailableTime(@PathParam("reservationDate") String reservationDate, @PathParam("idShcedule") Long idShcedule) {
    return iReservationController.unavailableTime(reservationDate, idShcedule);
  }

  @GET
  @Path("/getReservationsSend/{idShcedule}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTReservationSend> getReservationsSend(@PathParam("idShcedule") Long idShcedule) {
    return iReservationController.getReservationsSend(idShcedule);
  }

  @GET
  @Path("/getReservationData/{ci}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTReservationView> getReservationData(@PathParam("ci") String ci) {
    return iReservationController.getReservationData(ci);
  }

  @POST
  @Path("/cancelReservation/{idReservation}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public String cancelReservation(@PathParam("idReservation") Long idReservation) {
    boolean bool = iReservationController.reservationChangeState(idReservation, ReservationState.canceled);
    String stringReturn = "{\"ok\": " + bool + "}";

    return stringReturn;
  }
}
