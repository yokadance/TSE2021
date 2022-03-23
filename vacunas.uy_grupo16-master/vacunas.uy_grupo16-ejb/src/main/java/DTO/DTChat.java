package DTO;

import java.io.Serializable;

public class DTChat implements Serializable {

  private String id;
  private String timestamp;
  private String message;
  private Long transmitter_id;
  private String contactName;
  private Long receiver_id;
  private Boolean checked;
  private String conversationId;

  public DTChat() {}

  public DTChat(
    String id,
    String timestamp,
    String message,
    Long transmitter_id,
    String contactName,
    Long receiver_id,
    Boolean checked,
    String conversationId
  ) {
    this.id = id;
    this.timestamp = timestamp;
    this.message = message;
    this.transmitter_id = transmitter_id;
    this.contactName = contactName;
    this.receiver_id = receiver_id;
    this.checked = checked;
    this.conversationId = conversationId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getTransmitter_id() {
    return transmitter_id;
  }

  public void setTransmitter_id(Long transmitter_id) {
    this.transmitter_id = transmitter_id;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public Long getReceiver_id() {
    return receiver_id;
  }

  public void setReceiver_id(Long receiver_id) {
    this.receiver_id = receiver_id;
  }

  public Boolean getChecked() {
    return checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }

  public String getConversationId() {
    return conversationId;
  }

  public void setConversationId(String conversationId) {
    this.conversationId = conversationId;
  }

  @Override
  public String toString() {
    return (
      "DTChat{" +
      "id='" +
      id +
      '\'' +
      ", timestamp='" +
      timestamp +
      '\'' +
      ", message='" +
      message +
      '\'' +
      ", transmitter_id=" +
      transmitter_id +
      ", contactName='" +
      contactName +
      '\'' +
      ", receiver_id=" +
      receiver_id +
      ", checked=" +
      checked +
      ", conversationId='" +
      conversationId +
      '\'' +
      '}'
    );
  }
}
