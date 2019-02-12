package pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Person

class PersonsTestDataBuilder {
    static Person kowalski() {
        return new Person("Jan", "Kowalski", "11111116")
    }
}
