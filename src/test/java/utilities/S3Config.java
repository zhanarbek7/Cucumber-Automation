package utilities;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.util.Objects;

public class S3Config {

    private final static String accessKey = Objects.requireNonNull(SecretsManager.getSecrets()).getAccess_key();

    private final static String accessSecret = Objects.requireNonNull(SecretsManager.getSecrets()).getSecret_key();

    private final static String region = Objects.requireNonNull(SecretsManager.getSecrets()).getRegion();

    private static AmazonS3 generateS3client(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
    }

    public static AmazonS3 getS3Client(){
        return generateS3client();
    }
}
