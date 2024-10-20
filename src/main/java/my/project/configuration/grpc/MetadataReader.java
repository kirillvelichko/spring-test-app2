package my.project.configuration.grpc;

import java.util.Optional;

public interface MetadataReader {

    Optional<String> getAuthToken();
}
