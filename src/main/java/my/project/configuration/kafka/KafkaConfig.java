package my.project.configuration.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.VoidDeserializer;
import org.apache.kafka.common.serialization.VoidSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

@Configuration
public class KafkaConfig {
    private static final KafkaContainer kafkaContainer;

    static {
        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));
        kafkaContainer.start();
        createTopics("topic_name");
    }

    private static void createTopics(String... topics) {
        var newTopics = Arrays.stream(topics)
                .map(topic -> new NewTopic(topic, 1, (short) 1))
                .collect(Collectors.toList());
        try (var admin = AdminClient.create(Map.of(BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers()))) {
            admin.createTopics(newTopics);
        }
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Void, String> kafkaListener() {
        var factory = new ConcurrentKafkaListenerContainerFactory<Void, String>();
        factory.setConsumerFactory(getConsumerFactory(kafkaContainer.getBootstrapServers()));
        return factory;
    }

    @Bean
    public KafkaTemplate<Void, String> kafkaTemplate() {
        return new KafkaTemplate<>(getProducerFactory());
    }

    private ProducerFactory<Void, String> getProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
        return new DefaultKafkaProducerFactory<>(configProps, VoidSerializer::new, StringSerializer::new);
    }

    private ConsumerFactory<Void, String> getConsumerFactory(String bootstrapServers) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "app2");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props, new VoidDeserializer(), new StringDeserializer());
    }
}
