package webservice.rest;

import DTO.DTAssignment;
import DTO.DTChat;
import IController.IChatController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "Chat" })
public class restChat {

  @EJB
  IChatController iChatController;

  @POST
  @Path("/save")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public String lPersons(DTChat dtChat) {
    return iChatController.saveChat(dtChat);
  }

  @GET
  @Path("/get/{contactId}/{myId}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTChat> getChat(@PathParam("contactId") Long contactId, @PathParam("myId") Long myId) {
    return iChatController.getChat(contactId, myId);
  }

  @GET
  @Path("/getlast/{receiver_ci}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTChat> getLastMessages(@PathParam("receiver_ci") String receiver_ci) {
    return iChatController.getLastMessages(receiver_ci);
  }
}
