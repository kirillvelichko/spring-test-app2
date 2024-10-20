package my.project.domain.user;

import lombok.RequiredArgsConstructor;
import my.project.database.entity.UserEntity;
import my.project.database.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> getUsers(Optional<String> firstName, Optional<String> lastName) {
        if (firstName.isPresent() && lastName.isPresent()) {
            return userRepository.getAllByFirstNameAndLastName(firstName.get(), lastName.get());
        }
        if (firstName.isPresent()) {
            return userRepository.getAllByFirstName(firstName.get());
        }
        if (lastName.isPresent()) {
            return userRepository.getAllByLastName(lastName.get());
        }
        return emptyList();
    }
}
