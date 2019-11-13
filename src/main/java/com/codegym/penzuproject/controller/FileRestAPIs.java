package com.codegym.penzuproject.controller;

import com.codegym.penzuproject.model.Diary;
import com.codegym.penzuproject.model.FileUpload;
import com.codegym.penzuproject.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RequestMapping("/api/auth/file")
@RestController
public class FileRestAPIs {
    @Autowired
    Environment env;

    @Autowired
    private IDiaryService diaryService;

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileUpload> uploadFile(@ModelAttribute FileUpload file, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            MultipartFile multipartFile = file.getFile();
            String fileName = multipartFile.getOriginalFilename();
            String fileUpload = env.getProperty("file.upload-dir").toString();
            try {
                FileCopyUtils.copy(file.getFile().getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Optional<Diary> diary = diaryService.findById(id);
            diary.get().setFile(fileName);
            diaryService.save(diary.get());

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
