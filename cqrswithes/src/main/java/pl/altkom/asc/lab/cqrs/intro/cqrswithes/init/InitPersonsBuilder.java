package pl.altkom.asc.lab.cqrs.intro.cqrswithes.init;

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.Person;

class InitPersonsBuilder {
    static Person kowalski() {
        return new Person("Jan", "Kowalski", "11111116");
    }
}
