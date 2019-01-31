package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
