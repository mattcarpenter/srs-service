package net.mattcarpenter.benkyou.srsservice.exception;

import lombok.Data;

import java.util.List;

@Data
public class ErrorWrapper {
    private List<Error> errors;
}
