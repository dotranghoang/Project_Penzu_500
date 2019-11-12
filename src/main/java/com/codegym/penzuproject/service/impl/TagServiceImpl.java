package com.codegym.penzuproject.service.impl;

import com.codegym.penzuproject.model.Tag;
import com.codegym.penzuproject.repository.ITagRepository;
import com.codegym.penzuproject.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TagServiceImpl implements ITagService {

    @Autowired
    private ITagRepository tagRepository;

    @Override
    public Iterable<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void remove(Long id) {
        tagRepository.deleteById(id);
    }
}
