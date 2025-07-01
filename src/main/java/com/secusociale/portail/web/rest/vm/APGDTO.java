package com.secusociale.portail.web.rest.vm;

/**
 * View Model object for storing the CheckUser's type and value.
 */
public class APGDTO {

    private String code;
    private String message;
    private double amount;
    private String transactionId;
    private String auditNumber;
    private String transactionDate;

    public APGDTO(String code, String message, double amount, String transactionId, String auditNumber, String transactionDate) {
        this.code = code;
        this.message = message;
        this.amount = amount;
        this.transactionId = transactionId;
        this.auditNumber = auditNumber;
        this.transactionDate = transactionDate;
    }

    public APGDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAuditNumber() {
        return auditNumber;
    }

    public void setAuditNumber(String auditNumber) {
        this.auditNumber = auditNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
