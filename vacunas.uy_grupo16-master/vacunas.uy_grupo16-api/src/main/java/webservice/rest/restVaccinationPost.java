package webservice.rest;

import DTO.DTVaccinationPost;
import DTO.DTVaccinationPostNew;
import IController.IVaccinationPostController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/VaccinationPost")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "VaccinationPost" })
public class restVaccinationPost {

  @EJB
  IVaccinationPostController iVaccinationPostController;

  @POST
  @Path("/saveVaccinationPost")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void saveVaccinationPost(DTVaccinationPost dtVaccinationPost) {
    iVaccinationPostController.saveVaccinationPost(dtVaccinationPost);
  }

  @GET
  @Path("/lVaccinationPosts")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public List<DTVaccinationPost> lVaccinationPost() {
    return iVaccinationPostController.getVaccinationPosts();
  }

  @GET
  @Path("/getVaccinationPost/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public DTVaccinationPost getVaccinationPost(@PathParam("id") Long id) {
    return iVaccinationPostController.getByIdVaccinationPost(id);
  }

  @PUT
  @Path("/deleteVaccinationPost/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public void deleteVaccinationPost(@PathParam("id") Long id) {
    iVaccinationPostController.deleteVaccinationPost(id);
  }

  @POST
  @Path("/createVaccinationPost")
  @ApiResponses({ @ApiResponse(code = 201, message = "Success") })
  public Response saveVaccinationPost(DTVaccinationPostNew dtVaccinationPost) throws IOException {
    try {
      iVaccinationPostController.createVaccinationPost(dtVaccinationPost);
      return Response.status(Response.Status.CREATED).build();
    } catch (Exception e) {

      return Response.status(Response.Status.BAD_REQUEST).build();
    }

  }

  @GET
  @Path("/getVaccinationPostByVaccinationCenter/{id}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response getVaccinationPostByVaccinationCenter(@PathParam("id") Long idVaccinationCenter) throws IOException {
    try {
      iVaccinationPostController.getByVaccinatorCenter(idVaccinationCenter);
      return Response.status(Response.Status.OK).build();
    } catch (Exception e) {

      return Response.status(Response.Status.BAD_REQUEST).build();
    }

  }


}
