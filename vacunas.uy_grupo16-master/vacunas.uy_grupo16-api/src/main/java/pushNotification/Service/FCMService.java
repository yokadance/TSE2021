package pushNotification.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pushNotification.Model.PushNotificationRequest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Stateless
public class FCMService {

    Logger logger = LoggerFactory.getLogger(FCMService.class);

    public void sendMessageToToken(PushNotificationRequest request) throws ExecutionException, InterruptedException {
        Message message = getMessageFromRequest(request);
        String response = sendAndGetResponse(message);
        logger.info("Enviando mensaje al dispositivo con el token: " + request.getToken() + ", " + response);
    }

    private String sendAndGetResponse(Message message) throws ExecutionException, InterruptedException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message getMessageFromRequest(PushNotificationRequest request){
        return Message.builder()
                .putData("click_action", "VACUNASUY HA ENVIADO NOTIFICACION")
                .putData("message_title", request.getTitle())
                .putData("message_body", request.getMessage())
                .setNotification(Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getMessage())
                        .build())
                .setToken(request.getToken())
                .build();
    }
}
