package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DateRange {
    private LocalDate from;
    private LocalDate to;

    public static DateRange between(LocalDate from, LocalDate to) {
        return new DateRange(from, to);
    }

    public boolean contains(LocalDate eventDate) {
        if (eventDate.isAfter(to))
            return false;

        if (eventDate.isBefore(from))
            return false;

        return true;
    }

    public DateRange clone() {
        return new DateRange(from, to);
    }

    public DateRange endOn(LocalDate endDate) {
        return new DateRange(from, endDate);
    }

    public long days() {
        return DAYS.between(from, to);
    }
}
