package org.finra.interview.controller;

import com.amazonaws.util.IOUtils;
import lombok.extern.log4j.Log4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.services.ExcelUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Controller
@RequestMapping("api/upload")
@CrossOrigin(origins = "http://localhost:4200")
public class BulkUploadController {

    private final ExcelUploadService bulkUploadService;


    @Autowired
    public BulkUploadController(ExcelUploadService bulkUploadService) {
        this.bulkUploadService = bulkUploadService;
    }

    @PostMapping("/candidates")
    public List<Candidate> handleCandidateUpload(@RequestParam("file") MultipartFile file,
                                                 RedirectAttributes redirectAttributes) {

        return bulkUploadService.addCandidates(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");

//        return "redirect:/";
    }

    @PostMapping("/questions")
    @ResponseBody
    public ResponseEntity handleQuestionUpload(@RequestParam("file") MultipartFile file) throws IOException, InvalidFormatException, NotOLE2FileException {

        List<Question> questions = new ArrayList<>();

        questions = bulkUploadService.addQuestions(IOUtils.toByteArray(file.getInputStream()));

        log.info(questions);

//        return questions;

        return ResponseEntity.ok(questions);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleStorageFileNotFound(Exception exc) {
//        System.out.println("!!!!!");
//        exc.printStackTrace();
//        return ResponseEntity.notFound().build();
//    }

}
