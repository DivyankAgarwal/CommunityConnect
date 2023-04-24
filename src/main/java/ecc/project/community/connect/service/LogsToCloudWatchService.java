package ecc.project.community.connect.service;

import com.amazonaws.services.logs.AWSLogs;
import com.amazonaws.services.logs.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogsToCloudWatchService {

    private final AWSLogs awsLogs;

    @Value("${log.group.name}")
    private String logGroupName;

    @Value("${log.group.stream}")
    private String logGroupStreamName;

    public void logMessageToCloudWatch(String message) {

        List<InputLogEvent> logEvents = new ArrayList<>();
        InputLogEvent log = new InputLogEvent();
        Calendar calendar = Calendar.getInstance();

        log.setTimestamp(calendar.getTimeInMillis());
        log.setMessage(message);
        logEvents.add(log);

        String token = null;
        DescribeLogStreamsRequest logStreamsRequest = new DescribeLogStreamsRequest(logGroupName);

        logStreamsRequest.withLimit(5);
        List<LogStream> logStreamList;
        logStreamList = awsLogs.describeLogStreams(logStreamsRequest).getLogStreams();

        for (LogStream logStream : logStreamList) {
            if (logStream.getLogStreamName().equals(logGroupStreamName))
                token = logStream.getUploadSequenceToken();
        }

        PutLogEventsRequest putLogEventsRequest = new PutLogEventsRequest();
        PutLogEventsResult putLogEventsResult = new PutLogEventsResult();
        if (token != null) {

            appendLogsToCloudWatchWithToken(putLogEventsRequest, putLogEventsResult, token, logEvents);
        } else {
            firstHitToCloudWatch(putLogEventsRequest, logEvents, putLogEventsResult);
        }

    }

    private void appendLogsToCloudWatchWithToken(PutLogEventsRequest putLogEventsRequest,
                                                 PutLogEventsResult putLogEventsResult, String token, List<InputLogEvent> logEvents) {
        putLogEventsRequest.setLogGroupName(logGroupName);
        putLogEventsRequest.setLogStreamName(logGroupStreamName);
        putLogEventsRequest.setLogEvents(logEvents);

        putLogEventsRequest.setSequenceToken(token);

        putLogEventsResult = awsLogs.putLogEvents(putLogEventsRequest);

    }

    private void firstHitToCloudWatch(PutLogEventsRequest putLogEventsRequest, List<InputLogEvent> logEvents,
                                      PutLogEventsResult putLogEventsResult) {
        putLogEventsRequest.setLogGroupName(logGroupName);
        putLogEventsRequest.setLogStreamName(logGroupStreamName);
        putLogEventsRequest.setLogEvents(logEvents);

        putLogEventsResult = awsLogs.putLogEvents(putLogEventsRequest);

    }
}
