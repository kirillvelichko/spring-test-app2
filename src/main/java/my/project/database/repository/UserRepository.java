package my.project.database.repository;

import my.project.database.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    String DELETE_USERS_BY_FIRST_NAME_AND_LAST_NAME = """
            delete from "user"
            where first_name = :firstName
            and last_name = :lastName
            """;

    List<UserEntity> getAllByFirstName(String firstName);

    List<UserEntity> getAllByLastName(String lastName);

    List<UserEntity> getAllByFirstNameAndLastName(String firstName, String lastName);

    @Modifying
    @Query(DELETE_USERS_BY_FIRST_NAME_AND_LAST_NAME)
    boolean deleteUsers(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
