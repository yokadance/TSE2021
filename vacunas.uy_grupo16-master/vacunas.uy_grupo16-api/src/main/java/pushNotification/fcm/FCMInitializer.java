package pushNotification.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.io.InputStream;


    @Singleton
    @Startup
    public class FCMInitializer {

        Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

        @PostConstruct
        public void initialize(){
            InputStream serviceAccountStream = getClass().getClassLoader().getResourceAsStream("fcm/vacunasuy-g16-firebase-adminsdk-2nkba-bd963a1c0b.json");

            try {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                        .setDatabaseUrl("https://vacunasuy-g16-default-rtdb.firebaseio.com/")
                        .build();

                FirebaseApp.initializeApp(options);

                logger.info("Se inicializo el componente Firebase Cloud Messaging");

            } catch (IOException e) {
                logger.error("Error al iniciar FCM", e);
            }
        }
    }



