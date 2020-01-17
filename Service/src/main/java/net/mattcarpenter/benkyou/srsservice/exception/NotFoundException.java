package net.mattcarpenter.benkyou.srsservice.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String string) {
        super(string);
    }
}
