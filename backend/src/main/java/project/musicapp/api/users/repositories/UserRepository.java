package project.musicapp.api.users.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.musicapp.api.users.model.User;
import project.musicapp.api.users.utils.UserQuerySQL;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = UserQuerySQL.FIND_ALL_USERS_BY_USERNAME, nativeQuery = true)
    List<User> findAllUsersByUsername(@Param("value") String value,
                                      @Param("limit") int limit,
                                      @Param("offset") int offset);
}
