package com.secusociale.portail.web.rest.vm;

/**
 * View Model object for storing the CheckUser's type and value.
 */
public class CheckVM {

    private String type;

    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
