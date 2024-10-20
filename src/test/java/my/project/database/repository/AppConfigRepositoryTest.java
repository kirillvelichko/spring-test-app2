package my.project.database.repository;

import my.project.IntegrationTest;
import my.project.database.entity.AppConfigEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
class AppConfigRepositoryTest extends IntegrationTest {
    @Autowired
    private AppConfigRepository appConfigRepository;

    @BeforeEach
    void init() {
        var appConfig = new AppConfigEntity();
        appConfig.setName("property");
        appConfig.setValue("value");
        appConfig.setDescription("some desc");
        appConfigRepository.save(appConfig);
    }

    @Test
    void getByName_testMapping() {
        var appConfig = appConfigRepository.getByName("property");
        assertNotNull(appConfig.getId());
        assertEquals("property", appConfig.getName());
        assertEquals("value", appConfig.getValue());
        assertEquals("some desc", appConfig.getDescription());
    }
}