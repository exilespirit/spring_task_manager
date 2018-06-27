package task_manager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task_manager.Messages.TaskMessage;
import task_manager.model.Task;
import task_manager.model.TaskRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class NotificationService {

    private SimpMessagingTemplate template;

    @Autowired
    public NotificationService(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Autowired
    EmailServiceImpl emailServiceImpl;

    @Autowired
    TaskRepository taskRepository;
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Scheduled(cron="0 * * * * *")
    public void sendNotifications(){
        LocalDateTime ts = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime ts2 = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusSeconds(59);
        List<Task> list = taskRepository.findAllByTimeBetween(ts, ts2);
        log.info("The time is now {}", ts);
        log.info("Interval {}", ts2);
        log.info("size {}", list.size());
        for(Task item : list) {
            log.info("Task {} notification send", item.getId());
            emailServiceImpl.sendSimpleMessage(item.getUser().getEmail(), item.getTitle(), item.getDescription());
            sendTask(item);
        }
    }


    public void sendTask(Task task) {
        TaskMessage message = new TaskMessage();
        message.setTitle(task.getTitle());
        message.setDescription(task.getDescription());
        message.setId(task.getId().toString());
        message.setUid(task.getUser().getId().toString());
        log.info("Message send: {}", message.getId());
        log.info(message.getUid());
        this.template.convertAndSend("/topic/user/"+task.getUser().getId(), message);
    }
}
