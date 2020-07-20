package pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders;

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Person;

public class PersonsTestDataBuilder {

    public static final String FIRST_NAME_JAN = "Jan";
    public static final String LAST_NAME_KOWALSKI = "Kowalski";
    public static final String FIRST_NAME_KAZIMIERZ = "Kazimierz";

    public static Person kowalski() {
        return new Person(FIRST_NAME_JAN, LAST_NAME_KOWALSKI, "11111116");
    }

    public static Person nowak() {
        return new Person(FIRST_NAME_KAZIMIERZ, "Nowak", "11111116");
    }
}
