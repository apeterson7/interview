package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;


@Log4j
@RestController
@CrossOrigin
@RequestMapping("api/resume")
public class ResumeController {


    private final S3Service s3Service;

    @Autowired
    public ResumeController(S3Service s3Service){
        this.s3Service = s3Service;
    }

    @GetMapping("/candidate/{id}")
    public File getResume(@PathVariable String id) throws IOException{

        return s3Service.retrieveFileFromS3(id);
    }

    @PostMapping("/candidate/{id}")
    public void uploadResume(@PathVariable String id, @RequestBody File file){

        s3Service.uploadFileToS3(file, id);
    }

}
