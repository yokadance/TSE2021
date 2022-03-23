package webservice.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logingubuy")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "LoginGubUy" })
public class authLoginGubuy {

  private ObjectMapper mapper = new ObjectMapper();

  @GET
  @Path("/authgubuy")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response authLogin() {
    String url =
      "https://auth-testing.iduruguay.gub.uy/oidc/v1/authorize?scope=openid%20personal_info%20email&response_type=code&client_id=890192&redirect_uri=http://localhost:8080&client_secret=457d52f181bf11804a3365b49ae4d29a2e03bbabe74997a2f510b179";
    return Response.temporaryRedirect(URI.create(url)).build();
  }

  @GET
  @Path("/authcode")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response authRedirect(@QueryParam("code") String code, @QueryParam("state") String state)
    throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();

    String redirect_uri = URLEncoder.encode(
      "http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/logingubuy/authcode",
      StandardCharsets.UTF_8.toString()
    );
    //    String redirect_uri = URLEncoder.encode("http://localhost:8080/VacunasUYG16-api/api/logingubuy/authcode", StandardCharsets.UTF_8.toString());

    String inputForm = "grant_type=authorization_code&redirect_uri=" + redirect_uri + "&code=" + code;

    HttpRequest request = HttpRequest
      .newBuilder(URI.create("https://auth-testing.iduruguay.gub.uy/oidc/v1/token"))
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Authorization", "Basic ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5")
      .POST(HttpRequest.BodyPublishers.ofString(inputForm))
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    String ret = response.body();

    JsonNode actualObj = mapper.readTree(ret);

    String id_token = actualObj.get("id_token").textValue();
    String access_token = actualObj.get("access_token").textValue();

    String ret2 = consumeWS("http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/logingubuy/userinfo/" + access_token, "GET");
    //        String ret2 = consumeWS("http://localhost:8080/VacunasUYG16-api/api/logingubuy/userinfo/" + access_token, "GET");

    String encodeData = URLEncoder.encode(ret2, StandardCharsets.UTF_8.toString());

    String beJwt = consumeWS(
      "http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/login/loginGubUY/" + encodeData + "/" + id_token,
      "POST"
    );
    //    String beJwt = consumeWS("http://localhost:8080/VacunasUYG16-api/api/login/loginGubUY/" + encodeData, "POST");

    //    return Response.temporaryRedirect(URI.create("http://localhost:3000/LogGobUy/" + beJwt)).build();
    return Response.temporaryRedirect(URI.create("https://node1340-vacunasuyg16.web.elasticloud.uy/LogGobUy/" + beJwt)).build();
  }

  @GET
  @Path("/loginmobile")
  @Produces(MediaType.TEXT_HTML)
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public String authRedirectMobile(@QueryParam("code") String code, @QueryParam("state") String state)
    throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();

    String redirect_uri = URLEncoder.encode(
      "http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/logingubuy/loginmobile",
      StandardCharsets.UTF_8.toString()
    );

    String inputForm = "grant_type=authorization_code&redirect_uri=" + redirect_uri + "&code=" + code;

    HttpRequest request = HttpRequest
      .newBuilder(URI.create("https://auth-testing.iduruguay.gub.uy/oidc/v1/token"))
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Authorization", "Basic ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5")
      .POST(HttpRequest.BodyPublishers.ofString(inputForm))
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    String ret = response.body();

    JsonNode actualObj = mapper.readTree(ret);

    String id_token = actualObj.get("id_token").textValue();
    String access_token = actualObj.get("access_token").textValue();

    String ret2 = consumeWS("http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/logingubuy/userinfo/" + access_token, "GET");

    String encodeData = URLEncoder.encode(ret2, StandardCharsets.UTF_8.toString());

    String beJwt = consumeWS(
      "http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/login/loginGubUY/" + encodeData + "/" + id_token,
      "POST"
    );

    return (
      "<p><span style=\"color: #000000;\">AGUARDE UN MOMENTO POR FAVOR...</span></p>\n" +
      "<pre style=\"color: #ffffff;\">" +
      beJwt +
      "</pre>"
    );
  }

  @GET
  @Path("/userinfo/{accesstkn}")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public String userInfo(@PathParam("accesstkn") String accesstkn) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest
      .newBuilder(URI.create("https://auth-testing.iduruguay.gub.uy/oidc/v1/userinfo"))
      .header("Authorization", "Bearer " + accesstkn)
      .POST(HttpRequest.BodyPublishers.ofString(""))
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }

  @GET
  @Path("/logout")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response logOut() throws IOException, InterruptedException {
    //    return Response.temporaryRedirect(URI.create("http://localhost:3000/")).build();
    return Response.temporaryRedirect(URI.create("https://node1340-vacunasuyg16.web.elasticloud.uy/")).build();
  }

  @GET
  @Path("/logoutGubUy")
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public Response logOutGubUy() throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();

    String redirect_uri = URLEncoder.encode(
      "http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/logingubuy/logout",
      StandardCharsets.UTF_8.toString()
    );

    String id_token_hint = "";
    String inputForm = "id_token_hint=" + id_token_hint + "&redirect_uri=" + redirect_uri + "&state=";

    HttpRequest request = HttpRequest
      .newBuilder(URI.create("https://auth-testing.iduruguay.gub.uy/oidc/v1/logout?" + inputForm))
      .GET()
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    //        String ret = response.body();
    //        return response.body();
    return Response.temporaryRedirect(URI.create("http://localhost:3000/")).build();
  }

  private String consumeWS(String urlWs, String method) throws IOException {
    URL url = new URL(urlWs);

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod(method);
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
      throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
    }

    InputStreamReader in = new InputStreamReader(conn.getInputStream());

    BufferedReader br = new BufferedReader(in);

    String output;

    String ret = null;

    while ((output = br.readLine()) != null) {
      System.out.println(output);
      ret = output;
    }

    conn.disconnect();

    return ret;
  }

  @GET
  @Path("/testHtml")
  @Produces(MediaType.TEXT_HTML)
  @ApiResponses({ @ApiResponse(code = 200, message = "Success") })
  public String test() {
    return (
      "<p><span style=\"color: #000000;\">AGUARDE UN MOMENTO POR FAVOR...</span></p>\n" +
      "<pre style=\"color: #ffffff;\">ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5ODkwMTkyOjQ1N2Q1MmYxODFiZjExODA0YTMzNjViNDlhZTRkMjlhMmUwM2JiYWJlNzQ5OTdhMmY1MTBiMTc5</pre>"
    );
  }
}
