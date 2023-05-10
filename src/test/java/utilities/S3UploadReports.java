package utilities;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;

public class S3UploadReports {
    private static final String bucketName = Config.getValue("cloud.aws.bucket_name.reports");
    private static final AmazonS3 s3Client = S3Config.getS3Client();

    @Test
    public void uploadFile(){
        String fileName = "Cucumber_report_"+LocalDateTime.now();
        File file = new File(Config.getValue("cucumber.reports.path"));
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
    }

}
