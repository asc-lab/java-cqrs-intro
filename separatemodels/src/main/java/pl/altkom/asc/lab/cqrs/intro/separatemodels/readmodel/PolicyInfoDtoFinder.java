package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PolicyInfoDtoFinder {

    private final PolicyInfoDtoRepository repository;

    public List<PolicyInfoDto> findByFilter(PolicyFilter filter) {
        //TODO add filtering
        return (List<PolicyInfoDto>) repository.findAll();
    }
}
