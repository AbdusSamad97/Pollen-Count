package com.example.pollenmonitoring;

public class User {
    private String email;
    private String token;
    private String Paper_Mulbery;
    private String Acacia;
    private String Eucalyptus;
    private String Pines;
    private String Grasses;
    private String Cannabis;
    private String Dandelion;
    private String Alternaria;

    public User(String email, String token, String paper_Mulbery, String acacia, String eucalyptus, String pines,
                String grasses, String cannabis, String dandelion, String alternaria) {
        this.email = email;
        this.token = token;
        Paper_Mulbery = paper_Mulbery;
        Acacia = acacia;
        Eucalyptus = eucalyptus;
        Pines = pines;
        Grasses = grasses;
        Cannabis = cannabis;
        Dandelion = dandelion;
        Alternaria = alternaria;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPaper_Mulbery(String paper_Mulbery) {
        Paper_Mulbery = paper_Mulbery;
    }

    public void setAcaia(String acacia) {
        Acacia = acacia;
    }

    public void setEucalyptus(String eucalyptus) {
        Eucalyptus = eucalyptus;
    }

    public void setPines(String pines) {
        Pines = pines;
    }

    public void setGrasses(String grasses) {
        Grasses = grasses;
    }

    public void setCannabis(String cannabis) {
        Cannabis = cannabis;
    }

    public void setDandelion(String dandelion) {
        Dandelion = dandelion;
    }

    public void setAlternia(String alternaria) {
        Alternaria = alternaria;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getPaper_Mulbery() {
        return Paper_Mulbery;
    }

    public String getAcacia() {
        return Acacia;
    }

    public String getEucalyptus() {
        return Eucalyptus;
    }

    public String getPines() {
        return Pines;
    }

    public String getGrasses() {
        return Grasses;
    }

    public String getCannabis() {
        return Cannabis;
    }

    public String getDandelion() {
        return Dandelion;
    }

    public String getAlternaria() {
        return Alternaria;
    }
}

