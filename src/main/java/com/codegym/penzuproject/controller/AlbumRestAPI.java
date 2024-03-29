package com.codegym.penzuproject.controller;

import com.codegym.penzuproject.message.request.SearchAlbumByTagIdAndTitle;
import com.codegym.penzuproject.message.request.SearchAlbumsByTitle;
import com.codegym.penzuproject.model.Album;
import com.codegym.penzuproject.model.Diary;
import com.codegym.penzuproject.model.Image;
import com.codegym.penzuproject.service.IAlbumService;
import com.codegym.penzuproject.service.IImageService;
import com.codegym.penzuproject.service.Impl.AlbumFirebaseServiceExtends;
import com.codegym.penzuproject.service.Impl.ImageFirebaseServiceExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AlbumRestAPI {

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private AlbumFirebaseServiceExtends albumFirebaseServiceExtends;

    @Autowired
    private ImageFirebaseServiceExtends imageFirebaseServiceExtends;

    @GetMapping("/album")
    public ResponseEntity<?> getAllAlbum() {
        List<Album> albums = (List<Album>) albumService.findAll();

        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(albums,HttpStatus.OK);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<?> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumService.findById(id);

        if (!album.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(album,HttpStatus.OK);
    }

    @PostMapping("/album")
    public ResponseEntity<?> createAlbum(@RequestBody Album album) {
        LocalDateTime now = LocalDateTime.now();
        album.setDate(now);
        albumService.save(album);
        return new ResponseEntity<>(album, HttpStatus.CREATED);
    }

    @PutMapping("/album/{id}")
    public ResponseEntity<?> updateAlbum(@PathVariable Long id , @RequestBody Album album) {
        Optional<Album> album1 = albumService.findById(id);
        if (!album1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        album1.get().setTag(album.getTag());
        album1.get().setDescription(album.getDescription());
        albumService.save(album1.get());

        return new ResponseEntity<>(album1 , HttpStatus.OK);
    }

    @DeleteMapping("/album/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id) {
        Optional<Album> album = albumService.findById(id);
        List<Image> images = (List<Image>) imageService.findImagesByAlbumId(id);

        if (!images.isEmpty()) {
            for (int i = 0 ; i < images.size() ; i++) {
                imageFirebaseServiceExtends.deleteFirebaseStorageFile(images.get(i));
            }
        }

        if (!album.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (album.get().getBlobString() != null) {
            albumFirebaseServiceExtends.deleteFirebaseStorageFile(album.get());
        }

        albumService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/album/search-by-userId/{id}")
    public ResponseEntity<?> getAlbumsByUserId(@PathVariable Long id) {
        List<Album> albums = (List<Album>) albumService.findAllByUserId(id);
        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PostMapping("/album/search-album-by-title")
    public ResponseEntity<?> findAlbumsByTitle(@RequestBody SearchAlbumsByTitle searchAlbumsByTitle) {
        if(searchAlbumsByTitle.getTitle() == "" || searchAlbumsByTitle.getTitle() == null) {
            List<Album> albums = (List<Album>) albumService.findAll();
            if(albums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albums,HttpStatus.OK);
        } else {
            List<Album> albums = (List<Album>) albumService.findAlbumsByTitleContaining(searchAlbumsByTitle.getTitle());
            if(albums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albums,HttpStatus.OK);
        }
    }

    @GetMapping("/album/pagination/ASC")
    public ResponseEntity<?> getListAlbumAndSortingByDateASC(@PageableDefault(value = 100000 )Pageable pageable) {
        Page<Album> albums = albumService.findAllByOrderByDateAsc(pageable);
        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(albums,HttpStatus.OK);
    }

    @GetMapping("/album/pagination/DESC")
    public ResponseEntity<?> getListAlbumAndSortingByDateDESC(@PageableDefault(value = 100000 )Pageable pageable) {
        Page<Album> albums = albumService.findAllByOrderByDateDesc(pageable);
        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(albums,HttpStatus.OK);
    }

    @PostMapping("/album/search-by-tagId-and-title")
    public ResponseEntity<?> searchAlbumByTagIdAndTitle(@RequestBody SearchAlbumByTagIdAndTitle formSearch) {
        if (formSearch.getTitle() == null && formSearch.getTagId() == null) {
            List<Album> albums = (List<Album>) albumService.findAll();
            if(albums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albums,HttpStatus.OK);
        }

        if (formSearch.getTitle() == null && formSearch.getTagId() != null) {
            List<Album> albums = (List<Album>) albumService.findAlbumsByTagId(formSearch.getTagId());
            if(albums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albums,HttpStatus.OK);
        }

        if (formSearch.getTitle() != null && formSearch.getTagId() == null) {
            List<Album> diaries = (List<Album>) albumService.findAlbumsByTitleContaining(formSearch.getTitle());
            if(diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries,HttpStatus.OK);
        }

        if (formSearch.getTagId() != null && formSearch.getTitle() != null) {
            List<Album> albums = (List<Album>) albumService.findAlbumsByTagIdAndTitleContaining(formSearch.getTagId(),formSearch.getTitle());
            if(albums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albums,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
