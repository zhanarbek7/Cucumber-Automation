package entities;

import lombok.Data;

@Data
public class AwsCredentials {
    private String access_key;
    private String secret_key;
    private String region;
}
