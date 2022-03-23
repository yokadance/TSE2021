package pushNotification;

import DTO.DTCitizenToken;
import IController.IReservationController;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import pushNotification.Model.PushNotificationRequest;
import pushNotification.Service.FCMService;

@Singleton
public class TimerBean {

  @EJB
  IReservationController iReservationController;

  //@Schedule(second = "*", minute = "*", hour = "10", persistent = false)
  @Schedule(dayOfWeek = "*", hour = "9", minute = "0", second = "0", persistent = false)
  public void atSchedule() throws InterruptedException, ExecutionException {
    List<DTCitizenToken> dtCitizenTokens = iReservationController.getCitizenTokenByReservation();

    for (DTCitizenToken dtCitizenToken : dtCitizenTokens) {
      if (dtCitizenToken.getToken() != null) {
        sendMessage(
          dtCitizenToken.getName(),
          dtCitizenToken.getToken(),
          dtCitizenToken.getVaccinationCenterName(),
          dtCitizenToken.getVcaccinationCenterLocation(),
          dtCitizenToken.getReservationTime(),
          dtCitizenToken.getVaccinationPostName()
        );
      }
    }
  }

  private void sendMessage(
    String name,
    String token,
    String vaccinationCenterName,
    String vaccinationCenterLocation,
    String reservationTime,
    String vaccinationPostName
  ) throws ExecutionException, InterruptedException {
    String message =
      "Estimado " +
      name +
      ", le recordamos que el dia de mañana usted tiene una cita para vacunarse. Te recordamos que tu reserva es en " +
      vaccinationCenterName +
      " (" +
      vaccinationCenterLocation +
      "), en el puesto " +
      vaccinationPostName +
      ", a la hora " +
      reservationTime +
      ". Te recordamos que, de ser necesario, puedes cancelar esta reserva en nuestra web.";

    FCMService fcmService = new FCMService();
    PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
    pushNotificationRequest.setMessage(message);
    pushNotificationRequest.setTitle("¡Hola " + name + "!");
    pushNotificationRequest.setToken(token);
    fcmService.sendMessageToToken(pushNotificationRequest);
  }
}
