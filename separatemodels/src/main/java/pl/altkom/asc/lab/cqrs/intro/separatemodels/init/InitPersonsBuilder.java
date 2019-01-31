package pl.altkom.asc.lab.cqrs.intro.separatemodels.init;

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Person;

class InitPersonsBuilder {
    static Person kowalski() {
        return new Person("Jan", "Kowalski", "11111116");
    }
}
