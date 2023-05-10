package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

public class SecretsManager {

    public static AwsCredentials awsCredentials;

    public static AwsCredentials getSecrets() {
        String secretName = "iamaccess";
        Region region = Region.US_EAST_2;
        String profileName = "zhanarbek";
        if(awsCredentials==null){

            // Create an instance of AwsCredentialsProvider using the default credentials provider chain
            AwsCredentialsProvider credentialsProvider = DefaultCredentialsProvider.builder()
                    .profileName(profileName)
                    .build();

            // Create a Secrets Manager client
            // Create a request to retrieve the secret value
            try (SecretsManagerClient client = SecretsManagerClient.builder()
                    .region(region)
                    .credentialsProvider(credentialsProvider)
                    .build()) {
                GetSecretValueRequest request = GetSecretValueRequest.builder()
                        .secretId(secretName)
                        .build();
                // Retrieve the secret value from Secrets Manager
                GetSecretValueResponse response = client.getSecretValue(request);

                if (response.secretString() != null) {
                    // Process the secret value as a string
                    String secretValue = response.secretString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    awsCredentials = objectMapper.readValue(secretValue, AwsCredentials.class);
                    return awsCredentials;
                    // ...
                } else {
                    // Process the binary secret value
                    return null;
                }
            } catch (SecretsManagerException | JsonProcessingException e) {
                // Handle any exceptions or error scenarios
                System.out.println("SecretsManager couldn't retrieve secret data");
                return null;
            }
        }
        return awsCredentials;

    }
}
