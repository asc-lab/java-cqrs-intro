package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
