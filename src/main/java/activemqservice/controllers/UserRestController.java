package activemqservice.controllers;

import activemqservice.objects.Message;
import activemqservice.objects.MessageType;
import activemqservice.objects.User;
import activemqservice.objects.UserDTO;
import activemqservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserRestController {


    @Autowired
    private UserService userService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/user/all")
    public List<UserDTO> getAllUsers(){
        return userService.getUsers();
    }

    @PostMapping("/user/delete")
    public void deleteUserByID(@RequestParam("ids[]") Long[] ids){
        System.out.println(Arrays.toString(ids));
        Message message = new Message(MessageType.DELETE);
        message.setIds(ids);
        jmsTemplate.convertAndSend("userBox", message);
    }

    @PostMapping("/user/new")
    public String createNewUser(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return bindingResult.getFieldErrors().get(0).getDefaultMessage();
        }
        Message message = new Message(MessageType.ADD);
        message.setUser(user);
        jmsTemplate.convertAndSend("userBox", message);
        return null;
    }

}
