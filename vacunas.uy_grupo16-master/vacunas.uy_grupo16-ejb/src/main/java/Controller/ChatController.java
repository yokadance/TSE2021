package Controller;

import DTO.DTChat;
import DTO.DTVaccinator;
import IController.IChatController;
import IController.IVaccinatorController;
import IDAL.IChatData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class ChatController implements IChatController {

  @EJB
  IChatData iChatData;

  @Inject
  IVaccinatorController iVaccinatorController;

  @Override
  public String saveChat(DTChat dtChat) {
    return iChatData.saveChat(dtChat);
  }

  @Override
  public List<DTChat> getChat(Long contactId, Long myId) {
    DTVaccinator dtVaccinator = iVaccinatorController.getByIdVaccinator(contactId);
    return iChatData.getChat(contactId, myId, dtVaccinator.getDtPerson().getName());
  }

  @Override
  public List<DTChat> getLastMessages(String receiverCi) {
    List<DTChat> dtChat = new ArrayList<>();
    try {
      DTVaccinator dtVaccinator = iVaccinatorController.getVaccinatorByCi(receiverCi);
      Long receiverId = dtVaccinator.getId();
      return iChatData.getLastMessages(receiverId);
    } catch (Exception e) {
      //return null;
      return dtChat;
    }
  }

  public void setiChatData(IChatData iChatData) {
    this.iChatData = iChatData;
  }

  public void setiVaccinatorController(IVaccinatorController iVaccinatorController) {
    this.iVaccinatorController = iVaccinatorController;
  }
}
