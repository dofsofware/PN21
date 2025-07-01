package com.secusociale.portail.model;

import org.springframework.web.multipart.MultipartFile;

public class MultipartWithExtension {
    private MultipartFile multipartFile;
    private String extension;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public MultipartWithExtension(MultipartFile multipartFile, String extension) {
        this.multipartFile = multipartFile;
        this.extension = extension;
    }

    public MultipartWithExtension() {
    }
}
