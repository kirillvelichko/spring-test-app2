package my.project.configuration.grpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    public MetadataServerInterceptor metadataServerInterceptor() {
        return new MetadataServerInterceptor();
    }
}
