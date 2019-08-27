package org.finra.interview.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.finra.interview.models.Candidate;
import org.finra.interview.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Date;
import java.util.UUID;

import static org.springframework.util.FileCopyUtils.copyToByteArray;

@Service
public class S3Service {

    @Autowired
    public S3Service(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    private CandidateRepository candidateRepository;

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private byte[] getFileFroms3bucket(String fileName) {
        try{
            S3Object object = s3client.getObject(new GetObjectRequest(bucketName, fileName));
            InputStream in = object.getObjectContent();

            byte[] data = FileCopyUtils.copyToByteArray(in);
            return data;
        }catch(Exception e){
            e.printStackTrace();
        }

        return new byte[0];
    }

    public String uploadResume(MultipartFile multipartFile, Long candidateId) {

        System.out.println(endpointUrl);
        System.out.println(bucketName);
        System.out.println(accessKey);
        System.out.println(secretKey);


        String fileObjKeyName = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            UUID fileIdentifier = UUID.randomUUID();
            fileObjKeyName = candidateId+"/"+fileIdentifier.toString();
            uploadFileTos3bucket(fileObjKeyName, file);
            candidateRepository.setResumeId(candidateId,fileIdentifier.toString());
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return endpointUrl+"/"+fileObjKeyName;
    }

    public byte[] getResume(Long candidateId, String resumeId){
        String fileName = candidateId+"/"+resumeId;
        return getFileFroms3bucket(fileName);
    }


    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        return "Successfully deleted";
    }

}
