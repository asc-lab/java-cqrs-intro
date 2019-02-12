package pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Car

class CarsTestDataBuilder {
    static Car oldFordFocus() {
        return new Car("Ford Focus", "WAW1010", 2005)
    }
}
