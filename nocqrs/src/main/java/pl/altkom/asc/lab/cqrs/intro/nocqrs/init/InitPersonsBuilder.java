package pl.altkom.asc.lab.cqrs.intro.nocqrs.init;

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Person;

class InitPersonsBuilder {
    static Person kowalski() {
        return new Person("Jan", "Kowalski", "11111116");
    }
}
