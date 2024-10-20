package my.project.integration.api.rest.user;

import lombok.RequiredArgsConstructor;
import my.project.domain.user.UserService;
import my.project.integration.api.rest.user.mapper.UserMapper;
import my.project.integration.api.rest.user.request.UserRequest;
import my.project.integration.api.rest.user.response.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/user/get/list")
    public UserResponse getUsers(@RequestBody UserRequest request){
        Optional<String> firstName = Optional.ofNullable(request.getFirstName());
        Optional<String> lastName = Optional.ofNullable(request.getLastName());
        var userList = userService.getUsers(firstName, lastName);
        return userMapper.toResponse(userList);
    }
}
