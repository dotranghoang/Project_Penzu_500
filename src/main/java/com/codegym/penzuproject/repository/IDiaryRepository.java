package com.codegym.penzuproject.repository;

import com.codegym.penzuproject.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IDiaryRepository extends JpaRepository<Diary, Long> {
}
