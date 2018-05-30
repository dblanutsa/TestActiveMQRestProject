package activemqservice.services;

import activemqservice.objects.User;
import activemqservice.objects.UserDTO;
import activemqservice.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(toUserDTO(user));
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long id : ids){
            userRepository.delete(id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getUsers() {
        List<UserDTO> users = new ArrayList<>();
        for (UserDTO userDTO : userRepository.findAll()){
            users.add(userDTO);
        }
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return userRepository.getCount();
    }

    private User fromUserDTO(UserDTO userDTO){
        return new User(userDTO.getFirstName(), userDTO.getLastName());
    }

    private UserDTO toUserDTO(User user){
        return new UserDTO(user.getFirstName(), user.getLastName());
    }
}
