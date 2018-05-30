package activemqservice.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {

    private MessageType messageType;
    private User user;
    private Long[] ids;

    public Message(MessageType messageType) {
        this.messageType = messageType;
    }
}
