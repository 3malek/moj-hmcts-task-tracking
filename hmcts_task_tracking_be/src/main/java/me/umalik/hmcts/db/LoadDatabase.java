package me.umalik.hmcts.db;

import me.umalik.hmcts.domain.Status;
import me.umalik.hmcts.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository)
    {
        LocalDateTime localDateTimeNow = LocalDateTime.now();

        Task task = new Task(
                "Demo Test Data",
                "Notice of change in representation.  Change the solicitor details.",
                Status.CANCELLED,
                localDateTimeNow.plusDays(10));

        return args -> {
            log.info("Preloading " + repository.save( task ));
        };
    }
}