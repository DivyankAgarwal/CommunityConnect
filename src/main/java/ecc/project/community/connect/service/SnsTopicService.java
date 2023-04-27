package ecc.project.community.connect.service;

import com.amazonaws.services.sns.model.ListTopicsResult;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

import java.util.List;

@Service
@Slf4j
public class SnsTopicService {

    @Value("${prioritypost.sns.topic.arn}")
    private String priorityPostSnsTopicArn;
    private final SnsClient snsClient;

    public SnsTopicService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }


    public void listSNSTopics() {

        try {
            ListTopicsRequest request = ListTopicsRequest.builder().build();

            ListTopicsResponse result = snsClient.listTopics(request);

            System.out.println("Status was " + result.sdkHttpResponse().statusCode() + "\n\nTopics\n\n" + result.topics());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    public boolean sendSnsNotificationToLambda(List<String> allEmailAddress, String highAlertPost){
        System.out.println(allEmailAddress);
        String message = String.join(",",allEmailAddress);
        System.out.println("SNS Message Payload: " + message);
        String payload = String.join(",", allEmailAddress) + "|" + highAlertPost;
        PublishResponse publishResponse;
        try{
            PublishRequest publishRequest = PublishRequest.builder().message(payload).topicArn(priorityPostSnsTopicArn).build();
            System.out.println(publishRequest);
            publishResponse = snsClient.publish(publishRequest);
            System.out.println("SNS Message ID: " + publishResponse.messageId());
        } catch (SnsException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            throw new IllegalStateException();
        }
        return publishResponse.sdkHttpResponse().statusCode() == 200;
    }
}
