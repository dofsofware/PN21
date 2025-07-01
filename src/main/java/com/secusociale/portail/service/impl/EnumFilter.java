package com.secusociale.portail.service.impl;

public class EnumFilter <T extends Enum<T>> {
    private T equals;
    private String contains;

    // Getters et Setters
    public T getEquals() {
        return equals;
    }

    public void setEquals(T equals) {
        this.equals = equals;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }
}
