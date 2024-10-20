package my.project.configuration.dynamic;

import my.project.IntegrationTest;
import my.project.database.repository.AppConfigRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static my.project.configuration.dynamic.Property.SIGNATURE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class DynamicPropsTest extends IntegrationTest {
    @Autowired
    private AppDynamicProps dynamicProps;
    @Autowired
    private AppConfigRepository appConfigRepository;

    @AfterEach
    void clearCacheAfterTest() {
        dynamicProps.clearCache();
    }

    @Test
    void getValue_testCacheRefresh() {
        assertEquals("Warmest regards.", dynamicProps.getValue(SIGNATURE));
        var appConfig = appConfigRepository.getByName(SIGNATURE.getName());
        appConfig.setValue("Kind regards.");
        appConfigRepository.save(appConfig);
        assertEquals("Warmest regards.", dynamicProps.getValue(SIGNATURE));
        dynamicProps.clearCache();
        assertEquals("Kind regards.", dynamicProps.getValue(SIGNATURE));
    }
}