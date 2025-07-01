package com.secusociale.portail.domain;

import com.secusociale.portail.model.MultipartWithExtension;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;
import java.util.Date;

import static javax.xml.bind.DatatypeConverter.parseBase64Binary;

public class BASE64DecodedMultipartFile implements MultipartFile {

    private final byte[] fileBytes;
    private final String fileName;
    private static String extension;

    private static String typeContent;

    public BASE64DecodedMultipartFile(byte[] fileBytes, String base64, String fileName) {
        this.fileBytes = fileBytes;
        String header = base64.split(";")[0];
        BASE64DecodedMultipartFile.typeContent = header.split(":")[1];
        MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
        MimeType mimeType = null;
        try {
            mimeType = allTypes.forName(BASE64DecodedMultipartFile.typeContent);
            BASE64DecodedMultipartFile.extension = mimeType.getExtension();
        } catch (MimeTypeException e) {
            e.printStackTrace();
            extension = header.split("/")[1];
        }
        this.fileName = fileName + extension;

    }


    public static InputStream base64ToInputStreamV2(String base64) throws IOException {
        byte[] b = Base64.getDecoder().decode(base64);
        return new ByteArrayInputStream(b);
    }

    public static InputStream base64ToInputStream(String base64) {
        String base64Image = base64.split(",")[1];
        byte[] b = Base64.getDecoder().decode(base64Image);
        return new ByteArrayInputStream(b);
    }

    public static String multipartToBase64String(MultipartFile multipartFile) {
        try {
            String base64 = "";
            base64 = Base64.getEncoder().encodeToString(multipartFile.getBytes());
            return base64;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String base64Image = base64.split(",")[1];
            byte[] base64Binary = parseBase64Binary(base64Image);
            String hash = RandomStringUtils.randomAlphanumeric(10);
            return new BASE64DecodedMultipartFile(base64Binary, base64, hash + "_" + new Date().getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MultipartFile base64ToMultipart(String base64, String nomFichier) {
        try {
            String base64Image = base64.split(",")[1];
            byte[] base64Binary = parseBase64Binary(base64Image);
            String hash = RandomStringUtils.randomAlphanumeric(10);
            return new BASE64DecodedMultipartFile(base64Binary, base64, nomFichier + "_" + hash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MultipartWithExtension base64ToMultipartWithExtension(String base64, String nomFichier) {
        try {
            String base64Image = base64.split(",")[1];
            byte[] base64Binary = parseBase64Binary(base64Image);
            String hash = RandomStringUtils.randomAlphanumeric(10);
            MultipartFile multipartFile = new BASE64DecodedMultipartFile(base64Binary, base64, nomFichier + "_" + hash);
            return new MultipartWithExtension(multipartFile, extension);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String getContentType() {
        return BASE64DecodedMultipartFile.typeContent;
    }

    @Override
    public boolean isEmpty() {
        return fileBytes == null || fileBytes.length == 0;
    }

    @Override
    public long getSize() {
        return fileBytes.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return fileBytes;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileBytes);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(fileBytes);
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

}
