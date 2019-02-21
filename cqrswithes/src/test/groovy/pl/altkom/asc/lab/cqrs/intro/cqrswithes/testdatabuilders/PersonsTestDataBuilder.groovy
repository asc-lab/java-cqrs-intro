package pl.altkom.asc.lab.cqrs.intro.cqrswithes.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.Person

class PersonsTestDataBuilder {
    static Person kowalski() {
        return new Person("Jan", "Kowalski", "11111116")
    }
}
