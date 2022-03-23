package webservice.soap;

import DTO.*;
import IController.*;
import entities.Reservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService(portName = "sendSchedule")
public class SoapSendSchedule {

  @Resource
  WebServiceContext wsctx;

  @EJB
  IScheduleController iScheduleController;

  @EJB
  IReservationController iReservationController;

  @EJB
  IVaccinationActController iVaccinationActController;

  @EJB
  IPackageController iPackageController;

  @EJB
  IVaccinationCenterController iVaccinationCenterController;

  @EJB
  IAssignmentController iAssignmentController;

  @EJB
  IVaccinationPostController iVaccinationPostController;

  @WebResult(name="schedules", targetNamespace="")
  @WebMethod(operationName = "sendScheduleById")
  public DTSchedule sendScheduleById(Long id) {

    //MessageContext mctx = wsctx.getMessageContext();
    //get detail from request headers
    //Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
    //List userList = (List) http_headers.get("username");
    //List passList = (List) http_headers.get("password");
    //String user = (String) mctx.get("username");
   // System.out.println(user);

    //String username = "";
    //String password = "";

    /*if (userList != null) {
      username = userList.get(0).toString();
    }

    if (passList != null) {
      password = passList.get(0).toString();
    }

    if (username.equals("nikos") && password.equals("superpassword")) {*/

      DTSchedule dtSchedule = iScheduleController.getScheduleById(id);
      return dtSchedule;
    }
  //  return null;
  //}

  @WebMethod(operationName = "sendReservations")
  public List<DTReservationSend> sendReservations(Long id) {
    List<DTReservationSend> listReservations = iReservationController.getReservationsSend(id);
    System.out.println(listReservations.size());
    return listReservations;
  }

  @WebMethod(operationName = "sendReservations2")
  public List<DTReservationSend> sendReservations2(Long id) {
    //MessageContext mctx = wsctx.getMessageContext();
    //get detail from request headers


    /*Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
    List userList = (List) http_headers.get("username");
    List passList = (List) http_headers.get("password");*/

   /* String username = "";
    String password = "";

    if (userList != null) {
      username = userList.get(0).toString();
      System.out.println("entro acá");
    }

    if (passList != null) {
      password = passList.get(0).toString();
    }*/

   // if (username.equals("") && password.equals("")) {

  //    System.out.println(username);
    List<DTReservationSend> listReservations = iReservationController.getReservationsSend2(id);
    System.out.println(listReservations.size());
    return listReservations;
    }
  //  return null;
 // }

  @WebMethod(operationName = "receiveConfirmed")
  public void receiveConfirmed(String confirmed) {

    String str = confirmed;
    str =str.replace("[", "");
    str = str.replace("]", "");
    str = str.replace(" ", "");
    String[] res = str.split(",", 0);


    for (int i=0; i<res.length; i++){
      System.out.println(res[i]);
      Long idReserva = Long.parseLong(res[i]);
      System.out.println(idReserva);

      iReservationController.reservationToConfirmed(idReserva);

      DTReservation dtReservation = iReservationController.getByIdReservation(idReserva);
      DTVaccinationAct dtVaccinationAct = new DTVaccinationAct();
      DTVaccinationPost dtVaccinationPost = iVaccinationPostController.getVaccinationPostFromReservation(idReserva);
      //dtVaccinationPost.setId(1L);

      dtVaccinationAct.setVaccinationPost(iReservationController.dtVaccinationPostFromReservation(idReserva));
      dtVaccinationAct.setVaccinationPost(dtVaccinationPost);
      dtVaccinationAct.setCitizen(iReservationController.dtCitizenFromReservation(idReserva));

      List <DTPackage> dtPackageList = iReservationController.dtPackageFromReservation(idReserva);
      System.out.println("TAMAÑO DE LA LISTA DE PAQUETES: " + dtPackageList.size());
      boolean flag = false;
      for (DTPackage dtPackage : dtPackageList ){
        if (!flag)
          if (dtPackage.getQuantity() > 0 ){

            //restar 1

           dtVaccinationAct.setaPackage(dtPackage);
           dtPackage.setQuantity(dtPackage.getQuantity() -1);

            iVaccinationActController.saveVaccinationAct(dtVaccinationAct);
            iPackageController.discountPackageQuantity(dtPackage.getId());
            flag = true;
          }
        }



      }

    }

  @WebMethod(operationName = "loginVaccinatorCenter")
  public boolean loginVaccinatorCenter(Long id, String password) {
    return  iVaccinationCenterController.validatePassword(id,password);
  }

  @WebMethod(operationName = "vaccinatorByPost")
  public List<DTVaccinatorsVcenter> getAssignmentByPost(Long id){
    return iAssignmentController.getVaccinatorsOnPosts(id);
  }

  }


