package activemqservice.services;

import activemqservice.objects.User;
import activemqservice.objects.UserDTO;
import java.util.List;

public interface UserService {

    void save(User user);

    void delete(Long[] ids);

    List<UserDTO> getUsers();

    long getCount();

}
