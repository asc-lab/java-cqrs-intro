package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class Covers {

    private final List<Cover> covers;

    public Optional<Cover> withCode(String code) {
        return covers.stream()
                .filter(c -> c.getCode().equals(code))
                .findAny();
    }
}
