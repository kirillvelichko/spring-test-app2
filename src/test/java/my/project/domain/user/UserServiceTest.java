package my.project.domain.user;

import my.project.database.entity.UserEntity;
import my.project.database.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService(userRepository);
    }

    @Test
    void getUsers_testGetByFirstAndLastName() {
        var expectedUsers = List.of(new UserEntity());
        when(userRepository.getAllByFirstNameAndLastName("Petr", "Petrov")).thenReturn(expectedUsers);

        var actualUsers = userService.getUsers(Optional.of("Petr"), Optional.of("Petrov"));
        assertIterableEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).getAllByFirstNameAndLastName(any(), any());
    }

    @Test
    void getUsers_testGetByFirstName() {
        var expectedUsers = List.of(new UserEntity());
        when(userRepository.getAllByFirstName("Petr")).thenReturn(expectedUsers);

        var actualUsers = userService.getUsers(Optional.of("Petr"), Optional.empty());
        assertIterableEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).getAllByFirstName(any());
    }

    @Test
    void getUsers_testGetByLastName() {
        var expectedUsers = List.of(new UserEntity());
        when(userRepository.getAllByLastName("Petrov")).thenReturn(expectedUsers);

        var actualUsers = userService.getUsers(Optional.empty(), Optional.of("Petrov"));
        assertIterableEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).getAllByLastName(any());
    }

    @Test
    void getUsers_testGetByEmpty() {
        var actualUsers = userService.getUsers(Optional.empty(), Optional.empty());
        assertIterableEquals(emptyList(), actualUsers);
    }
}