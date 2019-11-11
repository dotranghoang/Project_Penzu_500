package com.codegym.penzuproject.service;

import com.codegym.penzuproject.model.Diary;

import java.util.List;
import java.util.Optional;

public interface IDiaryService {
    public List<Diary> findAll();
    public Optional<Diary> findById(Long id);
    public Diary findByTitle(String title);
    public void save(Diary diary);
    public void remove(Long id);
}
