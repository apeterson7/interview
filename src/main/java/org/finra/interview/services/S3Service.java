package org.finra.interview.services;

import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonServiceException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class S3Service {



    public void uploadFileToS3(File file, String key_name){
        log.info(String.format("Uploading %s to S3 bucket %s...\n", file.getName() , "resumes"));
        String bucket_name = "resumes";

        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        s3.putObject(bucket_name, key_name, file);

    }

    public File retrieveFileFromS3(String key_name) throws AmazonServiceException, IOException {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        String bucket_name = "resumes";

        S3Object o = s3.getObject(bucket_name, key_name);
        S3ObjectInputStream s3is = o.getObjectContent();

        File f = new File(key_name);
        FileOutputStream fos = new FileOutputStream(f);
        byte[] read_buf = new byte[1024];
        int read_len = 0;
        while ((read_len = s3is.read(read_buf)) > 0) {
            fos.write(read_buf, 0, read_len);
        }

        s3is.close();
        fos.close();

        return f;
    }

    public void deleteFileFromS3(String key_name) throws AmazonServiceException{
        String bucket_name = "resumes";

        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        ListObjectsV2Result result = s3.listObjectsV2(bucket_name);
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        List<String> keys = new ArrayList<>();
        for (S3ObjectSummary os: objects) {
            keys.add(os.getKey());
            System.out.println("* " + os.getKey());
        }
        if(keys.contains(key_name)){
            s3.deleteObject(bucket_name, key_name);
        }
    }

}
