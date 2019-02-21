package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String taxId;

    public Person copy() {
        return new Person(firstName, lastName, taxId);
    }
}
