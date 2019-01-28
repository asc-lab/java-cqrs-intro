package pl.altkom.asc.lab.cqrs.intro.nocqrs.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Car

class CarsTestDataBuilder {
    static Car oldFordFocus() {
        return new Car("Ford Focus", "WAW1010", 2005)
    }
}
