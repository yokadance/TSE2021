package Controllers;

import Controller.ChatController;
import DTO.DTPerson;
import DTO.DTVaccinator;
import IController.IVaccinatorController;
import IDAL.IChatData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChatControllerTests {

  @Spy
  private IChatData iChatData;

  private ChatController chatController;

  private IVaccinatorController iVaccinatorController;

  @Before
  public void setup() {
    this.iChatData = mock(IChatData.class);
    this.iVaccinatorController = mock(IVaccinatorController.class);
    this.chatController = new ChatController();
    this.chatController.setiChatData(iChatData);
    this.chatController.setiVaccinatorController(iVaccinatorController);
  }

  @After
  public void teardown() {
    Mockito.reset(iChatData);
  }

  @Test
  public void saveChatTest() {
    chatController.saveChat(any());
    Mockito.verify(iChatData, times(1)).saveChat(any());
  }

  @Test
  public void getChatTest() {
    DTVaccinator dtv = new DTVaccinator();
    DTPerson dtp = new DTPerson();
    dtp.setName("Test");
    dtv.setDtPerson(dtp);
    when(iVaccinatorController.getByIdVaccinator(1L)).thenReturn(dtv);
    when(iChatData.getChat(1L, 1L, "Test")).thenReturn(new ArrayList<>());

    chatController.getChat(1L, 1L);
    Mockito.verify(iChatData, times(1)).getChat(1L, 1L, "Test");
  }

  @Test
  public void getLastMessagesTestOk() {
    DTVaccinator dtv = new DTVaccinator();
    dtv.setId(1L);
    when(iVaccinatorController.getVaccinatorByCi("Test")).thenReturn(dtv);
    when(iChatData.getLastMessages(1L)).thenReturn(new ArrayList<>());

    chatController.getLastMessages("Test");
    Mockito.verify(iChatData, times(1)).getLastMessages(1L);
  }

  @Test
  public void getLastMessagesTestWithException() {
    DTVaccinator dtv = new DTVaccinator();
    dtv.setId(1L);
    when(iVaccinatorController.getVaccinatorByCi("Test")).thenReturn(null);
    when(iChatData.getLastMessages(1L)).thenReturn(new ArrayList<>());

    chatController.getLastMessages("Test");
  }
}
