package my.project.configuration.dynamic;

import lombok.RequiredArgsConstructor;
import my.project.database.repository.AppConfigRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static my.project.configuration.cache.CacheConfig.APP_CONFIG_CACHE;
import static my.project.scheduler.common.CronExpression.EVERY_10_MINUTES;

@Component
@CacheConfig(cacheNames = APP_CONFIG_CACHE)
@RequiredArgsConstructor
public class AppDynamicProps {
    private final AppConfigRepository appConfigRepository;

    @Cacheable
    public String getValue(Property property) {
        return appConfigRepository.getByName(property.getName()).getValue();
    }

    @CacheEvict(allEntries = true)
    @Scheduled(cron = EVERY_10_MINUTES)
    public void clearCache() {
    }
}
