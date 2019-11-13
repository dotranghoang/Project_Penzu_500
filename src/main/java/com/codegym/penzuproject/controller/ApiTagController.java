package com.codegym.penzuproject.controller;


import com.codegym.penzuproject.model.Tag;
import com.codegym.penzuproject.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ApiTagController {

    @Autowired
    private ITagService tagService;

    @GetMapping("api/auth/tags/")
    public ResponseEntity<List<Tag>> getTagList(){
        List<Tag> tags = (List<Tag>) tagService.findAll();

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("api/auth/tags/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id){
        Optional<Tag> tag = tagService.findById(id);

        if (tag == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/auth/tags/{name}")
    public ResponseEntity<Tag> getTagByName(@PathVariable String name){
        Optional<Tag> tag = tagService.findByName(name);

        if (tag == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("api/auth/tags")
    public ResponseEntity<Void> createTag(@RequestBody Tag tag){
        tagService.save(tag);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("api/auth/tags/{id}")
    public ResponseEntity<Tag> editTag(@RequestBody Tag tag, @PathVariable Long id){
        Optional<Tag> tag1 = tagService.findById(id);
        if (!tag1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tag1.get().setName(tag.getName());
        tagService.save(tag1.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("api/auth/tags/{id}")
    public ResponseEntity<Tag> removeTag(@PathVariable Long id){
        Optional<Tag> tag = tagService.findById(id);
        if (!tag.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tagService.remove(id);
        return new ResponseEntity<>(tag.get(), HttpStatus.OK);
    }
}
