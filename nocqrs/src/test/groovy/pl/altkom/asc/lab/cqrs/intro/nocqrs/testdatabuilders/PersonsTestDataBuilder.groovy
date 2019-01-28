package pl.altkom.asc.lab.cqrs.intro.nocqrs.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Person

class PersonsTestDataBuilder {
    static Person kowalski() {
        return new Person("Jan", "Kowalski", "11111116")
    }
}
