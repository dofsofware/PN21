package com.secusociale.portail.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.secusociale.portail.domain.Helpers} entity.
 */
public class APGModel implements Serializable {

    private String transactionId;

    private String requestId;

    private String status;

    private String[] docSignes;

    public String[] getDocSignes() {
        return docSignes;
    }

    public String getStatus() {
        return status;
    }

    public void setDocSignes(String[] docSignes) {
        this.docSignes = docSignes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
