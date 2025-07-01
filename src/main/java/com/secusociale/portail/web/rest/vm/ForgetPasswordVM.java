package com.secusociale.portail.web.rest.vm;

/**
 * View Model object for storing the CheckUser's type and value.
 */
public class ForgetPasswordVM {

    private String email;

    private String phoneNumber;

    private String url;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;  }
}
