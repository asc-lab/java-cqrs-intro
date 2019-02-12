package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

public interface PolicyRepository {
    Policy withNumber(String number);

    void add(Policy policy);
}
