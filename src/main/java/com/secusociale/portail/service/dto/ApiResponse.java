package com.secusociale.portail.service.dto;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private ResponseStatus status;
    private Integer code;
    private T body;

    private ApiResponse(ResponseStatus status, ResponseCode code, T body) {
        this.status = status;
        this.code = code.getValue();
        this.body = body;
    }

    public enum ResponseStatus {
        SUCCESS, ERROR
    }

    public enum ResponseCode {
        OK(200),
        PARTIAL_CONTENT(206),
        BAD_REQUEST(400),
        NOT_FOUND(404),
        INTERNAL_ERROR(500);

        private final int value;

        ResponseCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static <T> ApiResponse<T> successResponse(ResponseCode code, T body) {
        return new ApiResponse<>(ResponseStatus.SUCCESS, code, body);
    }

    public static <T> ApiResponse<T> errorResponse(ResponseCode code, T body) {
        return new ApiResponse<>(ResponseStatus.ERROR, code, body);
    }

    public static <T> ApiResponse<T> partialContent(T body) { return new ApiResponse<>(ResponseStatus.SUCCESS, ResponseCode.PARTIAL_CONTENT, body); }
    public static <T> ApiResponse<T> success(T body) {
        return successResponse(ResponseCode.OK, body);
    }

    public static <T> ApiResponse<T> error500(T body) {
        return errorResponse(ResponseCode.INTERNAL_ERROR, body);
    }

    public static <T> ApiResponse<T> error400(T body) {
        return errorResponse(ResponseCode.BAD_REQUEST, body);
    }

    public static <T> ApiResponse<T> error404(T body) {
        return errorResponse(ResponseCode.NOT_FOUND, body);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public T getBody() {
        return body;
    }
}
