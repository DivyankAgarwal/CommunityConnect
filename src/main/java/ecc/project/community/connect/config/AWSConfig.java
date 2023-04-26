package ecc.project.community.connect.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.logs.AWSLogs;
import com.amazonaws.services.logs.AWSLogsAsyncClientBuilder;
import com.amazonaws.services.logs.AWSLogsClientBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.sqs.SqsClient;


@Getter
@Setter
@Configuration
public class AWSConfig {

//    @Value("${amazon.aws.accesskey}")
//    private String awsAccessKey;
//
//    @Value("${amazon.aws.secretkey}")
//    private String awsSecretAccessKey;

    @Value("${amazon.aws.region}")
    private String awsRegion;

//    private String awsAccessKey = System.getenv("AWS_ACCESS_KEY_ID");
//    private String awsSecretAccessKey = System.getenv("AWS_SECRET_ACCESS_KEY");

    public static AwsCredentialsProvider getCredentialsProvider() {
        return DefaultCredentialsProvider.create();
    }

    DefaultAWSCredentialsProviderChain credentialsProviderChain = new DefaultAWSCredentialsProviderChain();
    Regions region = Regions.DEFAULT_REGION;


    @Bean
    public CloudWatchLogsClient cloudWatchLogsClient() {
//        var credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretAccessKey);
        return CloudWatchLogsClient
                .builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider()).build();

    }

    @Bean
    public SqsClient sqsClient() {
//        var credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretAccessKey);
        return SqsClient.builder().region(Region.of(awsRegion)).credentialsProvider(getCredentialsProvider()).build();
    }

    @Bean
    public AWSLogs awsLogs() {
        return AWSLogsClientBuilder
                .standard().
                withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(awsRegion)
                .build();

    }


}

