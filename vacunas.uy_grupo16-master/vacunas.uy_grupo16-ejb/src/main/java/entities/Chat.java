package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;
import java.time.LocalDateTime;

@Entity
@PersistenceUnit(unitName = "mongo-ogm")
public class Chat {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private LocalDateTime timestamp;

  private String message;
  private Long transmitter_id;
  private Long receiver_id;
  private String conversationId;
  private Boolean checked;

  public Chat() {}

  public Chat(
    String id,
    LocalDateTime timestamp,
    String message,
    Long transmitter_id,
    Long receiver_id,
    String conversationId,
    Boolean checked
  ) {
    this.id = id;
    this.timestamp = timestamp;
    this.message = message;
    this.transmitter_id = transmitter_id;
    this.receiver_id = receiver_id;
    this.conversationId = conversationId;
    this.checked = checked;
  }

  public String getId() {
    return id;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public Long getTransmitter_id() {
    return transmitter_id;
  }

  public Long getReceiver_id() {
    return receiver_id;
  }

  public String getConversationId() {
    return conversationId;
  }

  public Boolean getChecked() {
    return checked;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setTransmitter_id(Long transmitter_id) {
    this.transmitter_id = transmitter_id;
  }

  public void setReceiver_id(Long receiver_id) {
    this.receiver_id = receiver_id;
  }

  public void setConversationId(String conversationId) {
    this.conversationId = conversationId;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }

  @Override
  public String toString() {
    return (
      "Chat{" +
      "id='" +
      id +
      '\'' +
      ", timestamp=" +
      timestamp +
      ", message='" +
      message +
      '\'' +
      ", transmitter_id=" +
      transmitter_id +
      ", receiver_id=" +
      receiver_id +
      ", conversationId='" +
      conversationId +
      '\'' +
      ", checked=" +
      checked +
      '}'
    );
  }
}
