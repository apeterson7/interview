package org.finra.interview.controller;

import com.amazonaws.util.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

@Controller
@RequestMapping("api/upload")
@CrossOrigin(origins = "http://localhost:4200")
public class BulkUploadController {

    private final ExcelUploadService bulkUploadService;

    private static final String FILE_DIRECTORY = "/var/files";

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
    public List<Question> handleQuestionUpload(@RequestParam("file") MultipartFile file,
                                               RedirectAttributes redirectAttributes) throws IOException, InvalidFormatException {

        List<Question> questions = new ArrayList<>();

        System.out.println("questions");

//        String fileName =  file.getOriginalFilename();
//
//        String location = FILE_DIRECTORY + "/" + fileName;
//
//        Path filePath = Paths.get(location);
//
//        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//        File stagedFile = new File(location);



        try{
            questions = bulkUploadService.addQuestions(IOUtils.toByteArray(file.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }

        return questions;
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/";
    }

//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }

}
