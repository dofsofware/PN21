package com.secusociale.portail.service;

public enum Algorithms {
    //Key Generation algorithms
    RSA_KP_1024("RSA", "RSA Key pair generation algorithm", 1024),
    RSA_KP_2048("RSA", "RSA Key pair generation algorithm", 2048),

    AES_128("AES", "AES generation algorithm", 128),
    AES_256("AES", "AES generation algorithm", 256),

    //Encryption algorithms
    RSA_ECB_PKCS1PADDING_1024("RSA/ECB/PKCS1PADDING", "RSA/ECB/PKCS1PADDING 1024 bits", 1024),
    RSA_ECB_PKCS1PADDING_2048("RSA/ECB/PKCS1PADDING", "RSA/ECB/PKCS1PADDING 2048 bits", 2048),
    RSA_ECB_PKCS1PADDING_4096("RSA/ECB/PKCS1PADDING", "RSA/ECB/PKCS1PADDING 4096 bits", 4096),

    AES_CBC_PKCS5PADDING_128("AES/CBC/PKCS5PADDING", "AES/CBC/PKCS5PADDING 128 bits", 128);

    private String algorithm;
    private String description;
    private int tailleCle;

    private Algorithms(String algo, String desc, int taille) {
        this.algorithm = algo;
        this.description = desc;
        this.tailleCle = taille;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getDescription() {
        return description;
    }

    public int getTailleCle() {
        return tailleCle;
    }
}
