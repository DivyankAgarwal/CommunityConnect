package ecc.project.community.connect.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.logs.AWSLogs;
import com.amazonaws.services.logs.AWSLogsClientBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.sqs.SqsClient;


@Getter
@Setter
@Configuration
public class AWSConfig {

    @Value("${amazon.aws.accesskey}")
    private String awsAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String awsSecretAccessKey;


    @Bean
    public CloudWatchLogsClient cloudWatchLogsClient() {
        var credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretAccessKey);
        return CloudWatchLogsClient
                .builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(credentials)).build();
    }

    @Bean
    public SqsClient sqsClient() {
        var credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretAccessKey);
        return SqsClient.builder().region(Region.US_EAST_2).credentialsProvider(StaticCredentialsProvider.create(credentials)).build();
    }

    @Bean
    public AWSLogs awsLogs() {
        return AWSLogsClientBuilder
                .standard().
                withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretAccessKey)))
                .withRegion(Regions.US_EAST_2)
                .build();
    }


}

