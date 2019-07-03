package org.finra.interview.services;

import lombok.Builder;
import lombok.extern.log4j.Log4j;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.CandidateRepository;
import org.finra.interview.repositories.QuestionRepository;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Log4j
@Service
public class ExcelUploadService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CandidateRepository candidateRepository;

    //TODO
    public List<Candidate> addCandidates(MultipartFile file)  {
        List<Candidate> candidates = new ArrayList<>();
        return candidates;
    }


    public List<Question> addQuestions(byte[] file) throws InvalidFormatException, IOException{

        System.out.println("Bytes: "+file.length);


        File f = new File("test");

        OutputStream os = new FileOutputStream(f);
        os.write(file);

        os.close();

        List<Question> questions = new ArrayList<>();
//        File f = new File("src/main/resources/staging.xlsx");
//        file.transferTo(f);

        Workbook workbook = WorkbookFactory.create(f);
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        try{
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                questions.add(validateQuestionRow(row));
            }

        }catch(Exception e){ }

        System.out.println(questions);
        //Save all questions
//        questionRepository.saveAll(questions);

        return questions;
    }

    public static Question validateQuestionRow(Row row) throws InvalidFormatException{
        DataFormatter dataFormatter = new DataFormatter();

        //Log Row Info
        row.forEach(cell -> { log.info(dataFormatter.formatCellValue(cell)+" "); });
        log.info("Row "+row.getRowNum());

        //Check First Row
        if(row.getRowNum() == 0){
            if(!(dataFormatter.formatCellValue(row.getCell(0)).equals("name"))&&
                    (dataFormatter.formatCellValue(row.getCell(1)).equals("type"))&&
                    (dataFormatter.formatCellValue(row.getCell(2)).equals("level"))&&
                    (dataFormatter.formatCellValue(row.getCell(3)).equals("text"))&&
                    (dataFormatter.formatCellValue(row.getCell(4)).equals("answer"))&&
                    (dataFormatter.formatCellValue(row.getCell(5)).equals("score"))
                    ){
                throw new InvalidFormatException("Document Missing Column Headers");
            }
        //Validate question rows
        }else {

            String name = dataFormatter.formatCellValue(row.getCell(0));
            String type = dataFormatter.formatCellValue(row.getCell(1));
            String level = dataFormatter.formatCellValue(row.getCell(2));
            String text = dataFormatter.formatCellValue(row.getCell(3));
            String answer = dataFormatter.formatCellValue(row.getCell(4));
            String score = dataFormatter.formatCellValue(row.getCell(5));

            if (!(Arrays.asList("single answer", "multiple choice").contains(type))) {
                throw new InvalidFormatException("Error Row " + row.getRowNum() + ": type must be 'single answer' or 'multiple choice'");
            }
            if (!(Arrays.asList("junior", "senior", "mid").contains(level))) {
                throw new InvalidFormatException("Error Row " + row.getRowNum() + ": level must be 'junior', 'senior' or 'mid'");
            }
            if (text.length() > 10485760 || answer.length() > 10485760) {
                throw new InvalidFormatException("Error Row " + row.getRowNum() + ": Field too long");
            }
            try {
                Integer.parseInt(score);
            } catch (NumberFormatException e) {
                throw new InvalidFormatException("Error Row " + row.getRowNum() + ": Integer Expected");
            }

            return Question.builder()

                    .name(name)
                    .type(type)
                    .level(level)
                    .text(text)
                    .answer(answer)
                    .score(Integer.parseInt(score))
                    .build();

        }
        return new Question();
    }

    public static Candidate validateCandidateRow(){

        return new Candidate();
    }
}
