package DAL;

import DTO.DTChat;
import DTO.DTPerson;
import DTO.DTVaccinator;
import IController.IVaccinatorController;
import entities.Chat;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChatDataTest {

  private ChatData chatData;

  private Chat chat;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private IVaccinatorController iVaccinatorController;

  @Before
  public void setup() {
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    chat = mock(Chat.class);
    iVaccinatorController = mock(IVaccinatorController.class);
    //        restriction = mock(Restriction.class);
    this.chatData = new ChatData();
    this.chatData.setData(data);
    this.chatData.setModelMapper(modelMapper);
    this.chatData.setiVaccinatorController(iVaccinatorController);
  }

  @After
  public void teardown() {
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(chat);
    Mockito.reset(iVaccinatorController);
  }

  @Test
  public void saveChatTestWithTransmitterLesserThanReceiver() {
    DTChat dtc = new DTChat();
    when(modelMapper.map(dtc, Chat.class)).thenReturn(chat);
    when(chat.getTransmitter_id()).thenReturn(1L);
    when(chat.getReceiver_id()).thenReturn(2L);

    chatData.saveChat(dtc);
  }

  @Test
  public void saveChatTestWithTransmitterGreaterThanReceiver() {
    DTChat dtc = new DTChat();
    when(modelMapper.map(dtc, Chat.class)).thenReturn(chat);
    when(chat.getTransmitter_id()).thenReturn(2L);
    when(chat.getReceiver_id()).thenReturn(1L);

    chatData.saveChat(dtc);
  }

  @Test
  public void getChatTestWithoutException() {
    List<Chat> lC = new ArrayList<>();
    lC.add(chat);
    when(
      data.createQuery(
        "select distinct c FROM Chat c where (c.receiver_id=:receiverId and c.transmitter_id=:transmitterId) or (c.transmitter_id=:receiverId and c.receiver_id=:transmitterId) order by c.timestamp asc"
      )
    )
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("transmitterId", 1L)).thenReturn(typedQuery);
    when(typedQuery.setParameter("receiverId", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lC);
    when(modelMapper.map(chat, DTChat.class)).thenReturn(new DTChat());

    chatData.getChat(1L, 1L, "Test");
  }

  @Test
  public void getChatTestWithException() {
    List<Chat> lC = new ArrayList<>();
    lC.add(chat);
    when(
      data.createQuery(
        "select distinct c FROM Chat c where (c.receiver_id=:receiverId and c.transmitter_id=:transmitterId) or (c.transmitter_id=:receiverId and c.receiver_id=:transmitterId) order by c.timestamp asc"
      )
    )
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("transmitterId", 1L)).thenReturn(typedQuery);
    when(typedQuery.setParameter("receiverId", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(null);

    chatData.getChat(1L, 1L, "Test");
  }

  @Test
  public void getLastMessagesTestWithoutExceptionAndReceiverNotEqualToId() { //GSM - Incompleta 25/06
    DTVaccinator dtv = new DTVaccinator();
    DTPerson dtp = new DTPerson();
    dtp.setName("Test");
    dtp.setLastname("Test");
    dtv.setDtPerson(dtp);
    String mongoQuery =
      "db.Chat.aggregate([ { '$match': { '$or': [ { 'receiver_id': { '$in': [ " +
      1L +
      " ] } }, { 'transmitter_id': { '$in': [ " +
      2L +
      " ] } } ] } }, {'$sort' : { 'timestamp': -1} },{ '$group': { '_id': { 'conversationId': '$conversationId' }, 'timestamp': { '$first': '$timestamp' }, 'checked': { '$first': '$checked' }, 'message': { '$first': '$message' }, 'receiver_id': { '$first': '$receiver_id' }, 'transmitter_id': { '$first': '$transmitter_id' }} } ])";
    List<Object[]> list = new ArrayList<>();
    Document s = mock(Document.class);
    Object[] obj = { (Document) any(), "2021-01-01", true, "Test", 1L, 2L };
    list.add(obj);
    when(data.createNativeQuery(mongoQuery)).thenReturn(query);
    when(query.getResultList()).thenReturn(list);
    when(iVaccinatorController.getByIdVaccinator((Long) obj[5])).thenReturn(dtv);
    //    when(s.get("conversationId")).thenReturn("Test");

    chatData.getLastMessages(1L);
  }
}
