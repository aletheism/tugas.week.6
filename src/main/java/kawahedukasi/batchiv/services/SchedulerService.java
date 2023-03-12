package kawahedukasi.batchiv.services;

import io.quarkus.scheduler.Scheduled;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;

@ApplicationScoped
public class SchedulerService {
    @Inject
    MailService mailService;

    Logger logger = LoggerFactory.getLogger(SchedulerService.class);

//    @Scheduled(cron = "* 53 23 10 * ? *")
    @Scheduled(every = "5s")
    public void sendItemListExcel() throws JRException, IOException {
        mailService.sendAllToEmail("bot.gigihdemo@gmail.com");
        logger.info("All Related File Sent to Email");
    }
}
