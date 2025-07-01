package com.secusociale.portail.service.dto.custom;

public class ListDocDTO {
    private DocDTO[] docs;

    public void setDocs(DocDTO[] docs) {
        this.docs = docs;
    }

    public DocDTO[] getDocs() {
        return docs;
    }
}
