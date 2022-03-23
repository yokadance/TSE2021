package Entities;

import entities.Chat;
import java.time.LocalDateTime;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChatTest {

  Chat chat;

  private String fakeString = "Test";

  private Date fakeDate = new Date();

  private Long fakeLong = 1L;

  private int fakeInt = 1;

  @Before
  public void setup() {}

  @After
  public void teardown() {}

  @Test
  public void ChatTest() {
    LocalDateTime ldt = null;
    chat = new Chat();
    chat = new Chat(fakeString, ldt, fakeString, fakeLong, fakeLong, fakeString, true);
    chat.getId();
    chat.getTimestamp();
    chat.getMessage();
    chat.getTransmitter_id();
    chat.getReceiver_id();
    chat.getConversationId();
    chat.getChecked();
    chat.setId(fakeString);
    chat.setTimestamp(ldt);
    chat.setMessage(fakeString);
    chat.setTransmitter_id(fakeLong);
    chat.setReceiver_id(fakeLong);
    chat.setConversationId(fakeString);
    chat.setChecked(true);
    chat.toString();
  }
}
