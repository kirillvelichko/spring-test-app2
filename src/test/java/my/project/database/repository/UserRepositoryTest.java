package my.project.database.repository;

import my.project.IntegrationTest;
import my.project.database.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static my.project.database.entity.user.Sex.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
class UserRepositoryTest extends IntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        var user = new UserEntity();
        user.setFirstName("Petr");
        user.setLastName("Petrov");
        user.setSex(MALE);
        user.setBirthDate(LocalDate.of(1990, 10, 15));
        userRepository.save(user);
    }

    @Test
    void getAllByFirstNameAndLastName_testMapping() throws InterruptedException {
        var userList = userRepository.getAllByFirstNameAndLastName("Petr", "Petrov");
        assertEquals(1, userList.size());
        var user = userList.get(0);
        assertNotNull(user.getId());
        assertEquals("Petr", user.getFirstName());
        assertEquals("Petrov", user.getLastName());
        assertEquals(MALE, user.getSex());
        assertEquals(LocalDate.of(1990, 10, 15), user.getBirthDate());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        var currentTime = LocalDateTime.now();
        assertTrue(user.getUpdatedAt().isBefore(currentTime));
        user.setFirstName("Alex");
        Thread.sleep(1);
        userRepository.save(user);
        assertTrue(user.getUpdatedAt().isAfter(currentTime));
    }

    @Test
    void deleteUsers_testQuery() {
        var userList = userRepository.getAllByFirstNameAndLastName("Petr", "Petrov");
        assertEquals(1, userList.size());
        var result = userRepository.deleteUsers("Petr", "Petrov");
        assertTrue(result);
        userList = userRepository.getAllByFirstNameAndLastName("Petr", "Petrov");
        assertEquals(0, userList.size());
    }
}