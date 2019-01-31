package pl.altkom.asc.lab.cqrs.intro.separatemodels.init;


import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Car;

class InitCarsBuilder {
    static Car oldFordFocus() {
        return new Car("Ford Focus", "WAW1010", 2005);
    }
}
