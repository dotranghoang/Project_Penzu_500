package com.codegym.penzuproject.controller;

import com.codegym.penzuproject.model.Diary;
import com.codegym.penzuproject.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class DiaryRestAPIs {
    @Autowired
    private IDiaryService diaryService;

    @Autowired
    Environment env;

    @PostMapping("/diaries")
    public ResponseEntity<Diary> createDiary(@RequestBody Diary diary){
       if (diary == null){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       diaryService.save(diary);
       return new ResponseEntity<>(diary, HttpStatus.CREATED);
    }

    @GetMapping("/diaries")
    public ResponseEntity<List<Diary>> getListDiary(){
        List<Diary> diaries = diaryService.findAll();

        if(diaries.isEmpty()){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diaries, HttpStatus.CREATED);
    }

    @GetMapping("/diaries/{id}")
    public ResponseEntity<Optional<Diary>> getDiaryById(@PathVariable Long id){
        Optional<Diary> diary = diaryService.findById(id);
        if (diary == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diary, HttpStatus.OK);
    }

    @PutMapping("/diaries")
    public ResponseEntity<Diary> updateDiary(@RequestParam String title, @RequestBody Diary diary){
        Diary oldDiary = diaryService.findByTitle(title);
        List<Diary> diaries = diaryService.findAll();
        if (diaries.contains(oldDiary)){
            oldDiary.setTitle(diary.getTitle());
            oldDiary.setAuthor(diary.getAuthor());
            oldDiary.setDescription(diary.getDescription());
            oldDiary.setContent(diary.getContent());
            oldDiary.setDate(diary.getDate());
            oldDiary.setFile(diary.getFile());
            return new ResponseEntity<>(oldDiary, HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/diaries/{id}")
    public ResponseEntity<Void> removeDiary(@PathVariable Long id){
        diaryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
