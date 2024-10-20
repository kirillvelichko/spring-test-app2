package my.project.configuration.grpc;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

public class MetadataServerInterceptor implements ServerInterceptor, MetadataReader {
    private static final String AUTH_KEY = "auth";
    private static final Metadata.Key<String> AUTH_METADATA_KEY = Metadata.Key.of(AUTH_KEY, ASCII_STRING_MARSHALLER);
    private static final Context.Key<String> AUTH_CONTEXT_KEY = Context.key(AUTH_KEY);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata metadata,
                                                                 ServerCallHandler<ReqT, RespT> next) {

        String token = metadata.get(AUTH_METADATA_KEY);
        Context context = Context.current().withValue(AUTH_CONTEXT_KEY, token);
        return Contexts.interceptCall(context, call, metadata, next);
    }

    @Override
    public Optional<String> getAuthToken() {
        return Optional.ofNullable(AUTH_CONTEXT_KEY.get()).filter(StringUtils::hasText);
    }
}
