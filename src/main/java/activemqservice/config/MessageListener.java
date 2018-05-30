package activemqservice.config;

import activemqservice.objects.Message;
import activemqservice.services.UserService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private UserService userService;

    public MessageListener(UserService userService){
        this.userService = userService;
    }

    @JmsListener(destination = "userBox", containerFactory = "myFactory")
    public void receiveMessage(Message message) {
        switch (message.getMessageType()){
            case ADD:
                userService.save(message.getUser());
                System.out.println(userService.getCount());
                break;
            case DELETE:
                userService.delete(message.getIds());
                System.out.println(userService.getCount());
        }
    }

}
