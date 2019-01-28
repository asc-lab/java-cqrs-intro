package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cover {

    private UUID id;
    private String code;
    private String name;
}
