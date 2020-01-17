package net.mattcarpenter.benkyou.srsservice.exception;

public enum ErrorCode {

    NOT_FOUND(4000, "Resource not found"),

    UNKNOWN_ERROR(4001, "An unknown error occurred");


    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() { return message; }
}
