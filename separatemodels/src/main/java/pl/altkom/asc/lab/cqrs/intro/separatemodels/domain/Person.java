package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String taxId;

    Person copy() {
        return new Person(firstName, lastName, taxId);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
