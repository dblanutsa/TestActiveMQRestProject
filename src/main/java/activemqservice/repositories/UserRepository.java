package activemqservice.repositories;

import activemqservice.objects.UserDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDTO, Long> {

    @Query("SELECT COUNT(u) from UserDTO u")
    long getCount();

    @Modifying
    @Query("delete from UserDTO u where id=:id")
    void delete(@Param("id") long id);
}
