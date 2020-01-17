package net.mattcarpenter.benkyou.srsservice.exception;

import lombok.Data;

@Data
public class Error {
    private String message;
    private String code;

    public Error(ErrorCode code) {
        this.code = code.toString();
        this.message = code.getMessage();
    }

    public Error(ErrorCode code, String message) {
        this.code = code.toString();
        this.message = message;
    }
}
