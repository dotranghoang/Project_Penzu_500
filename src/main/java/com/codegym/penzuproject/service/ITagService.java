package com.codegym.penzuproject.service;

import com.codegym.penzuproject.model.Tag;

import java.util.Optional;

public interface ITagService {
    Iterable<Tag> findAll();

    Optional<Tag> findById(Long id);

    Optional<Tag> findByName(String name);

    void save(Tag tag);

    void remove(Long id);
}
