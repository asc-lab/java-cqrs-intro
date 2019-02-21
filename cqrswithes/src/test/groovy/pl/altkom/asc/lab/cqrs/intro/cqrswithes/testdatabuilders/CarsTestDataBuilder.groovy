package pl.altkom.asc.lab.cqrs.intro.cqrswithes.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.Car

class CarsTestDataBuilder {
    static Car oldFordFocus() {
        return new Car("Ford Focus", "WAW1010", 2005)
    }
}
