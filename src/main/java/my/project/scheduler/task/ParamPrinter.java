package my.project.scheduler.task;

import lombok.RequiredArgsConstructor;
import my.project.domain.hello.HelloService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static my.project.scheduler.common.CronExpression.EVERY_10_MINUTES;
import static my.project.scheduler.common.TaskRunner.runTask;

@Component
@RequiredArgsConstructor
public class ParamPrinter {
    private final HelloService helloService;

    @Scheduled(cron = EVERY_10_MINUTES)
    @SchedulerLock(name = "printParam")
    public void printParam() {
        runTask(helloService::hello, "Print cfg param from database");
    }
}
