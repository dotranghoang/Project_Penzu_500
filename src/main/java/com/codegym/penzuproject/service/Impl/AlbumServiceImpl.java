package com.codegym.penzuproject.service.Impl;

import com.codegym.penzuproject.model.Album;
import com.codegym.penzuproject.repository.IAlbumRepository;
import com.codegym.penzuproject.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements IAlbumService {

    @Autowired
    private IAlbumRepository repository;

    @Override
    public Optional<Album> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Album> findAll() {
        return repository.findAll();
    }

    @Override
    public Album save(Album album) {
        return repository.save(album);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Album> findAllByUserId(Long user_id) {
        return repository.findAllByUserId(user_id);
    }
}
