package my.project.integration.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProducer {
    private final KafkaTemplate<Void, String> kafkaTemplate;

    @Value("${spring.kafka.topics.message}")
    private String topic;

    @SneakyThrows
    public void sendMessage() {
        kafkaTemplate.send(topic, "Kafka message").get();
    }
}

