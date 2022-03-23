package webservice.rest;

import DTO.DTCitizen;
import DTO.DTNewPerson;
import DTO.DTPerson;
import IController.IPersonController;
import IController.IRoleController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import enumerations.Sex;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "login" })
public class restLogin {

  @EJB
  IPersonController iPersonController;

  @EJB
  IRoleController iRoleController;

  private ObjectMapper mapper = new ObjectMapper();

  @POST
  @Path("/loginGubUY/{data}/{id_token}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public String savePerson(@PathParam("data") String data, @PathParam("id_token") String id_token) throws Exception {
    String password;

    data = data.replace("+", ""); //el encode ta pirando

    JsonNode actualObj = mapper.readTree(data);

    String nombre_full =
      actualObj.get("primer_nombre").textValue() +
      " " +
      actualObj.get("segundo_nombre").textValue() +
      " " +
      actualObj.get("primer_apellido").textValue() +
      " " +
      actualObj.get("segundo_apellido").textValue();

    String email = actualObj.get("email").textValue();
    //String nombre_completo = actualObj.get("nombre_completo").textValue();
    String primer_nombre = actualObj.get("primer_nombre").textValue();
    String segundo_nombre = actualObj.get("segundo_nombre").textValue();
    String primer_apellido = actualObj.get("primer_apellido").textValue();
    String segundo_apellido = actualObj.get("segundo_apellido").textValue();
    String uid = actualObj.get("uid").textValue();
    String ci = uid.replace("uy-ci-", "");

    BufferedReader bf = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/hash.txt")));
    password = bf.readLine();
    System.out.println(password);
    SecretKey hashPassword = Keys.hmacShaKeyFor(password.getBytes(StandardCharsets.UTF_8));

    Boolean newUser = null;
    Boolean multiRole = false;
    String role = null;

    //chequeo si existe la persona por "ci"
    if (iPersonController.existPersonByCI(ci)) {
      //si existe, cargo rol (o roles)
      newUser = false;
      //obtengo roles
      List<String> listRoles = new ArrayList<>();
      listRoles = iPersonController.getUserRoles(ci);
      Boolean flgCitizen = false;
      Boolean flgVaccinator = false;

      if (listRoles != null) {
        for (String r : listRoles) {
          if (r.toLowerCase().equals("citizen")) {
            flgCitizen = true;
            role = "Citizen";
          }

          if (r.toLowerCase().equals("vaccinator")) {
            flgVaccinator = true;
            role = "Vaccinator";
          }
        }
      }

      //si el usuario es ciudadano y vacunador =>
      if (flgCitizen && flgVaccinator) {
        multiRole = true;
        role = "";
      } else if (!flgCitizen && !flgVaccinator) {
        //azigno rol ziudadano
        flgCitizen = true;
        role = "Citizen";
      }
    } else {
      //si no existe, la tengo que crear y asignar el rol ciudadano
      Date birthday = new Date(); //CAMBIAR
      DTPerson dtNP = new DTPerson(uid, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, ci, Sex.other, birthday, email);

      iPersonController.savePerson(dtNP);

      DTCitizen dtCit = new DTCitizen();
      dtCit.setDtPerson(iPersonController.getPersonByCI(ci));
      iRoleController.saveAnyRole(dtCit);

      role = "Citizen";
      newUser = true;
    }

    long timestamp = System.currentTimeMillis();
    String jwt = Jwts
      .builder()
      .setSubject("connectionData")
      .setIssuedAt(new Date(timestamp))
      .setExpiration(new Date(timestamp + 3600000)) // + 1 hora (en milisegundos)
      .claim("timestamp", timestamp)
      .claim("userName", nombre_full)
      .claim("firstName", primer_nombre)
      .claim("secondName", segundo_nombre)
      .claim("firstSurname", primer_apellido)
      .claim("secondSurname", segundo_apellido)
      .claim("ci", ci)
      .claim("role", role)
      .claim("newUser", newUser)
      .claim("multirol", multiRole)
      .claim("id_token", id_token)
      .signWith(hashPassword)
      .compact();

    JsonObject json = Json.createObjectBuilder().add("JWT", jwt).build();

    return jwt;
  }

  @POST
  @Path("/checkLoginGubUY/{jwt}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response checkLogin(@PathParam("jwt") String jwt) throws IOException {
    String password;

    BufferedReader bf = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/hash.txt")));
    password = bf.readLine();
    System.out.println(password);
    SecretKey hashPassword = Keys.hmacShaKeyFor(password.getBytes(StandardCharsets.UTF_8));

    try {
      Claims claims = Jwts.parserBuilder().setSigningKey(hashPassword).build().parseClaimsJws(jwt).getBody();
      return Response.status(Response.Status.ACCEPTED).build();
    } catch (ExpiredJwtException e) {
      e.printStackTrace();
      return Response.status(Response.Status.UNAUTHORIZED).build();
    } catch (SignatureException e) {
      e.printStackTrace();
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

  @POST
  @Path("/loginLdap/{user}/{password}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response loginLdap(@PathParam("user") String user, @PathParam("password") String password) throws Exception {
    boolean test = iPersonController.authenticateLDAP(user, password);
    if (test) {
      return Response.status(Response.Status.ACCEPTED).build();
    } else return Response.status(Response.Status.UNAUTHORIZED).build();
  }

  @POST
  @Path("/createLdap/{user}/{password}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response createLdap(@PathParam("user") String user, @PathParam("password") String password) throws Exception {
    iPersonController.addUserLdap(user, password);

    return Response.status(Response.Status.ACCEPTED).build();
  }

  @POST
  @Path("/existLdap/{user}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response createLdap(@PathParam("user") String user) throws Exception {
    if (iPersonController.existsLdapUser(user)) {

      return Response.status(Response.Status.ACCEPTED).build();
    }
    else return Response.status(Response.Status.NO_CONTENT).build();
    }



  @DELETE
  @Path("/deleteLdap/{user}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response deleteLdap(@PathParam("user") String user) throws Exception {
    if (iPersonController.existsLdapUser(user)) {
      iPersonController.removeUserLdap(user);

      return Response.status(Response.Status.ACCEPTED).build();
    } else return Response.status(Response.Status.NOT_FOUND).build();
  }

  public String ObtenerKey() {
    //Ojo aca genero una key pero no la estoy guardando
    // SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //String secretString = Encoders.BASE64.encode(key.getEncoded());
    //Acá tendría que guardarla
    //return secretString;
    return null;
  }

  public Key ObtenerKeySoloDemo() {
    String mikey = "NoHaganEstoEnCasa.NoHaganEstoEnCasa.NoHaganEstoEnCasa";
    SecretKey sk = Keys.hmacShaKeyFor(mikey.getBytes(StandardCharsets.UTF_8));
    return sk;
  }
}
