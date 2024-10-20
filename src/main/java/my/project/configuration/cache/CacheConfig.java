package my.project.configuration.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    public static final String APP_CONFIG_CACHE = "app_config_cache";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(APP_CONFIG_CACHE);
    }
}
