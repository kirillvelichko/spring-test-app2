package my.project.integration.api.rest.user.mapper;

import my.project.database.entity.UserEntity;
import my.project.integration.api.rest.user.response.User;
import my.project.integration.api.rest.user.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse toResponse(List<UserEntity> entityList) {
        List<User> userList = entityList.stream()
                .map(x -> User.builder()
                        .firstName(x.getFirstName())
                        .lastName(x.getLastName())
                        .build())
                .collect(Collectors.toList());
        var response = new UserResponse();
        response.setUserList(userList);
        return response;
    }
}
