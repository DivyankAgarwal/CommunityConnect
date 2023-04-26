package ecc.project.community.connect.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class ErrorLoggingService {


    private LogsToCloudWatchService logsToCloudWatchService;

    private static final String ERROR = "ERROR ";

    public void throwError() {
        System.out.println("It is error throwing method");
        log.error("Error from Service with time {} ", LocalDateTime.now());
        //logsToCloudWatchService.logMessageToCloudWatch(ERROR + "Hello from Spring Boot");
    }
}
