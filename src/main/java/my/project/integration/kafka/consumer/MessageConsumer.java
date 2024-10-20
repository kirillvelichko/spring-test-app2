package my.project.integration.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageConsumer {

    @KafkaListener(topics = "${spring.kafka.topics.message}", containerFactory = "kafkaListener")
    public void listenMessage(String message) {
        log.info("Incoming message: {}", message);
    }
}
