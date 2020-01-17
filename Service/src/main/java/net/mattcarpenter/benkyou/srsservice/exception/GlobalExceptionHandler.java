package net.mattcarpenter.benkyou.srsservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorWrapper> handleException(Exception ex, WebRequest request) {
        ErrorWrapper wrapper = new ErrorWrapper();
        wrapper.setErrors(Collections.singletonList(new Error(ErrorCode.UNKNOWN_ERROR)));
        LOG.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<ErrorWrapper> handleException(NotFoundException ex, WebRequest request) {
        ErrorWrapper wrapper = new ErrorWrapper();
        wrapper.setErrors(Collections.singletonList(new Error(ErrorCode.NOT_FOUND, ex.getMessage())));
        LOG.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(wrapper, HttpStatus.NOT_FOUND);
    }
}
