package my.project.domain.hello;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.configuration.dynamic.AppDynamicProps;
import org.springframework.stereotype.Service;

import static my.project.configuration.dynamic.Property.SIGNATURE;

@Service
@Slf4j
@RequiredArgsConstructor
public class HelloService {
    private final AppDynamicProps dynamicProps;

    public void hello() {
        log.info("Hello! {}", dynamicProps.getValue(SIGNATURE));
    }
}
