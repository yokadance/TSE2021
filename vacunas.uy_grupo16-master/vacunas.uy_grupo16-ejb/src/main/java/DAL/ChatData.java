package DAL;

import DTO.DTChat;
import DTO.DTVaccinator;
import IController.IVaccinatorController;
import IDAL.IChatData;
import entities.Chat;
import org.bson.Document;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ChatData implements IChatData {

  @PersistenceContext(unitName = "mongo-ogm")
  @PersistenceUnit(unitName = "mongo-ogm")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Inject
  IVaccinatorController iVaccinatorController;

  @Override
  public String saveChat(DTChat dtChat) {
    Chat chat = modelMapper.map(dtChat, Chat.class);
    String conversationId = "";
    if (chat.getTransmitter_id() > chat.getReceiver_id()) {
      conversationId = chat.getReceiver_id().toString() + chat.getTransmitter_id().toString();
    } else {
      conversationId = chat.getTransmitter_id().toString() + chat.getReceiver_id().toString();
    }
    chat.setConversationId(conversationId);
    chat.setTimestamp(LocalDateTime.now());
    data.persist(chat);
    return chat.toString();
  }

  @Override
  public List<DTChat> getChat(Long transmitterId, Long receiverId, String contactName) {
    try {
      List<DTChat> chatList = new ArrayList<>();
      List<Chat> list = data
        .createQuery(
          "select distinct c FROM Chat c where (c.receiver_id=:receiverId and c.transmitter_id=:transmitterId) or (c.transmitter_id=:receiverId and c.receiver_id=:transmitterId) order by c.timestamp asc"
        )
        .setParameter("transmitterId", transmitterId)
        .setParameter("receiverId", receiverId)
        .getResultList();
      list.forEach(
        chat -> {
          DTChat dtChat = modelMapper.map(chat, DTChat.class);
          dtChat.setContactName(contactName);
          chatList.add(dtChat);
        }
      );
      return chatList;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  @Override
  public List<DTChat> getLastMessages(Long receiver_id) {
    try {
      List<DTChat> chatList = new ArrayList<>();

      //   String query ="db.Chat.aggregate([{'$match': {'receiver_id': {'$in': [" + receiver_id + "]}}},{'$group': {'_id': {'transmitter_id': '$transmitter_id','receiver_id': '$receiver_id'},'timestamp': {'$last': '$timestamp'}, 'checked': {'$last':'$checked'}, 'message': {'$last':'$message'} }}])";
      String query =
        "db.Chat.aggregate([ { '$match': { '$or': [ { 'receiver_id': { '$in': [ " +
        receiver_id +
        " ] } }, { 'transmitter_id': { '$in': [ " +
        receiver_id +
        " ] } } ] } }, {'$sort' : { 'timestamp': -1} },{ '$group': { '_id': { 'conversationId': '$conversationId' }, 'timestamp': { '$first': '$timestamp' }, 'checked': { '$first': '$checked' }, 'message': { '$first': '$message' }, 'receiver_id': { '$first': '$receiver_id' }, 'transmitter_id': { '$first': '$transmitter_id' }} } ])";
      List<Object[]> list = data.createNativeQuery(query).getResultList();
      for (Object[] row : list) {
        Document s = (Document) row[0];
        DTChat dtChat = new DTChat();
        DTVaccinator dtVaccinator = new DTVaccinator();
        //if (receiver_id == (Long) row[5]) {
        if (receiver_id.equals((Long) row[5])) {
          dtVaccinator = iVaccinatorController.getByIdVaccinator((Long) row[4]);
        } else {
          dtVaccinator = iVaccinatorController.getByIdVaccinator((Long) row[5]);
        }
        dtChat.setContactName(dtVaccinator.getDtPerson().getName() + " " + dtVaccinator.getDtPerson().getLastname());
        dtChat.setChecked((Boolean) row[2]);
        dtChat.setMessage((String) row[3]);
        dtChat.setReceiver_id((Long) row[4]);
        dtChat.setTransmitter_id((Long) row[5]);
        dtChat.setConversationId((String) s.get("conversationId"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime dateTime = LocalDateTime.parse(row[1].toString(), formatter);
        dtChat.setTimestamp(dateTime.toString());
        chatList.add(dtChat);
      }
      return chatList;
    } catch (NoResultException e) {
      System.out.println(e);
      return null;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public void setiVaccinatorController(IVaccinatorController iVaccinatorController) {
    this.iVaccinatorController = iVaccinatorController;
  }
}
