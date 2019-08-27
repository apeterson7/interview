package org.finra.interview.controller;


import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.InterviewNotFoundException;
import org.finra.interview.models.Interview;
import org.finra.interview.models.Question;
import org.finra.interview.services.EmailService;
import org.finra.interview.services.InterviewService;
import org.finra.interview.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;


@Log4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class InterviewController {

    private final InterviewService interviewService;
    private final EmailService emailService;


    @Autowired
    public InterviewController(InterviewService interviewService,
                               EmailService emailService){
        this.interviewService = interviewService;
        this.emailService = emailService;
    }

    @GetMapping("/interviews")
    @CrossOrigin
    public Iterable<Interview> findAll(){ return interviewService.list(); }

    //changed
    @GetMapping("/interview/{id}")
    public Interview findById(@PathVariable UUID id) throws InterviewNotFoundException { return interviewService.findById(id); }


    @PostMapping("/interview")
    @ResponseStatus(HttpStatus.CREATED)
    public Interview save(@RequestBody Interview interview){
        return interviewService.save(interview);

    }

//    @PutMapping("/interview")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Interview update(@RequestBody String string) throws InterviewNotFoundException{
//
//        System.out.println(string);
//
////        JSONParser parser = new JSONParser();
////        JSONObject json = (JSONObject) parser.parse(stringToParse);
//
//        ObjectMapper mapper = new ObjectMapper();
//        try{
//            Map<String, Object > map = mapper.readValue(string, Map.class);
//
//            map.get("interview_id");
//            map.get("status");
//            map.get("");
//
//            JsonNode rootNode = mapper.readTree(string);
//
//            rootNode.findValue("interview_id").asText();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println(interview);
////        System.out.println(interview.getStatus()+" "+ interview.getInterview_id());
//        Interview ret = interviewService.update();
//
//        return new Interview();
////        return new Interview();
//
//    }


    @PutMapping("/interview/{id}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStatus(@PathVariable UUID id, @PathVariable Integer status) throws InterviewNotFoundException {
        log.info("HERE INTERVIEW UPDATING "+id+" STATUS "+status);

        interviewService.updateStatus(id,status);

    }

    @PutMapping("/submit-interview/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Interview submitInterview(@PathVariable UUID id) throws InterviewNotFoundException {

        return interviewService.submitInterview(id);

    }

}
