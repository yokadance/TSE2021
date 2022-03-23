package IController;

import DTO.DTChat;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IChatController {
  String saveChat(DTChat dtChat);

  List<DTChat> getChat(Long transmitterCi, Long receiverCi);
  List<DTChat> getLastMessages(String receiver_ci);
}
