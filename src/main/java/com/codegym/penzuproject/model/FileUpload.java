package com.codegym.penzuproject.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
    private MultipartFile file;

    public FileUpload(MultipartFile file) {
        this.file = file;
    }

    public FileUpload() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
