package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String taxId;

    public Person copy() {
        return new Person(firstName, lastName, taxId);
    }
}
