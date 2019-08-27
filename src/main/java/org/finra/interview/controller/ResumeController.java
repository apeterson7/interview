package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Log4j
@RestController
@CrossOrigin
@RequestMapping("api/resume")
public class ResumeController {


        private S3Service s3Service;

        @Autowired
        ResumeController(S3Service amazonClient) {
            this.s3Service = amazonClient;
        }

        @PostMapping("/uploadResume/{candidateId}")
        public String uploadFile(@RequestPart(value = "file") MultipartFile file, @PathVariable Long candidateId) {
            System.out.println(file.getSize()+" "+ candidateId);
            return this.s3Service.uploadResume(file, candidateId);
        }

        @GetMapping("/resume/{candidateId}/{resumeId}")
        public byte[] uploadFile(@PathVariable Long candidateId, @PathVariable String resumeId) {
            return this.s3Service.getResume(candidateId, resumeId);
        }

        @DeleteMapping("/deleteFile")
        public String deleteFile(@RequestPart(value = "url") String fileUrl) {
            return this.s3Service.deleteFileFromS3Bucket(fileUrl);
        }


//
//    private final S3Service s3Service;
//
//    @Autowired
//    public ResumeController(S3Service s3Service){
//        this.s3Service = s3Service;
//    }
//
//    @GetMapping("/candidate/{id}")
//    @CrossOrigin
//    public File getResume(@PathVariable String id) throws IOException{
//
//        return s3Service.retrieveFileFromS3(id);
//    }
//
//    @PostMapping("/candidate/{id}")
//    @CrossOrigin
//    public void uploadResume(@PathVariable String id, @RequestBody File file){
//
//        s3Service.uploadFileToS3(file, id);
//    }

}
