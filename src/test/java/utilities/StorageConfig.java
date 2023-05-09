package utilities;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class StorageConfig {

    private final static String accessKey = Config.getValue("cloud.aws.credentials.access-key");

    private final static String accessSecret = Config.getValue("cloud.aws.credentials.secret-key");

    private final static String region = Config.getValue("cloud.aws.region.static");

    private static AmazonS3 generateS3client(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
    }

    public static AmazonS3 getS3Client(){
        return generateS3client();
    }
}
