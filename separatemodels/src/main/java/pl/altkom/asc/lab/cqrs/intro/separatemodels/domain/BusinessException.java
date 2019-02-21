package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
