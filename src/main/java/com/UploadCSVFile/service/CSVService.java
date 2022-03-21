package com.UploadCSVFile.service;

import com.UploadCSVFile.helper.CSVHelper;
import com.UploadCSVFile.model.Tutorial;
import com.UploadCSVFile.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    TutorialRepository tutorialRepository;

    public void save(MultipartFile multipartFile) {
        try {
            List<Tutorial> tutorials = CSVHelper.csvToTutorials(multipartFile.getInputStream());
            tutorialRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data " + e.getMessage());
        }
    }

    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }
}
