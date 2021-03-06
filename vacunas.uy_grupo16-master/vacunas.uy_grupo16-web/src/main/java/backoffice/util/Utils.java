package backoffice.util;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import org.omnifaces.util.Messages;

/**
 * Created by rmpestano on 07/02/17.
 */
@ApplicationScoped
public class Utils implements Serializable {

  public static void addDetailMessage(String message) {
    addDetailMessage(message, null);
  }

  public static void addDetailMessage(String message, FacesMessage.Severity severity) {
    //        FacesMessage facesMessage = Messages.create("").detail(message).get();
    FacesMessage facesMessage = Messages.create(message).detail(message).get();
    if (severity != null && severity != FacesMessage.SEVERITY_INFO) {
      facesMessage.setSeverity(severity);
    }
    Messages.add(null, facesMessage);
  }
}
