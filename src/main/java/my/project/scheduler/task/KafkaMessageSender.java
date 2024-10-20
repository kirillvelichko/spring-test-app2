package my.project.scheduler.task;

import lombok.RequiredArgsConstructor;
import my.project.integration.kafka.producer.MessageProducer;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static my.project.scheduler.common.CronExpression.EVERY_MINUTE;
import static my.project.scheduler.common.TaskRunner.runTask;


@Component
@RequiredArgsConstructor
public class KafkaMessageSender {
    private final MessageProducer messageProducer;

    @Scheduled(cron = EVERY_MINUTE)
    @SchedulerLock(name = "createMessage")
    public void createMessage() {
        runTask(messageProducer::sendMessage, "Send kafka message");
    }
}
