package com.codegym.penzuproject.repository;

import com.codegym.penzuproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
