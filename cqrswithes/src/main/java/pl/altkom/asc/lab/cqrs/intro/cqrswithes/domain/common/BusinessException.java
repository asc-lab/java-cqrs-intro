package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
