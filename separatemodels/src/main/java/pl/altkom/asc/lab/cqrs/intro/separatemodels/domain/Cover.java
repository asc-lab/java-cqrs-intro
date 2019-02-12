package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cover {
    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private String name;
}
