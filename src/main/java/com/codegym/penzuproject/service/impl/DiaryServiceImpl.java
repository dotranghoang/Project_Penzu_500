package com.codegym.penzuproject.service.impl;

import com.codegym.penzuproject.model.Diary;
import com.codegym.penzuproject.repository.IDiaryRepository;
import com.codegym.penzuproject.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DiaryServiceImpl implements IDiaryService {
    @Autowired
    private IDiaryRepository diaryRepository;
    @Override
    public List<Diary> findAll() {
        return (List<Diary>) diaryRepository.findAll();
    }

    @Override
    public Optional<Diary> findById(Long id) {
        return diaryRepository.findById(id);
    }

    @Override
    public Diary findByTitle(String title) {
        List<Diary> diaries = diaryRepository.findAll();
        for (Diary diary: diaries) {
            if (diary.getTitle().equals(title)){
                return diary;
            }
        }
        return null;
    }

    @Override
    public void save(Diary diary) {
        diaryRepository.save(diary);
    }

    @Override
    public void remove(Long id) {
        diaryRepository.deleteById(id);
    }
}
