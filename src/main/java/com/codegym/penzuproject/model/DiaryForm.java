package com.codegym.penzuproject.model;

import org.springframework.web.multipart.MultipartFile;

public class DiaryForm {
    private Long id;
    private String title;
    private String author;
    private String desciption;
    private String content;
    private String date;
    private MultipartFile file;

    public DiaryForm() {
    }

    public DiaryForm(Long id, String title, String author, String desciption, String content, String date, MultipartFile file) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.desciption = desciption;
        this.content = content;
        this.date = date;
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
