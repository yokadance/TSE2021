package IDAL;

import DTO.DTChat;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IChatData {
  String saveChat(DTChat dtChat);

  List<DTChat> getChat(Long transmitterId, Long receiverId, String contactName);
  List<DTChat> getLastMessages(Long receiver_id);
}
