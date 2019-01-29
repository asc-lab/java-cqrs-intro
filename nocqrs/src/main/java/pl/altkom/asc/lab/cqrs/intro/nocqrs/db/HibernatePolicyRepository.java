package pl.altkom.asc.lab.cqrs.intro.nocqrs.db;

import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Policy;

import java.util.List;

@Component
public class HibernatePolicyRepository implements PolicyRepository {
    @Override
    public Policy withNumber(String number) {
        return null;
    }

    @Override
    public void add(Policy policy) {

    }

    @Override
    public List<Policy> find(PolicyFilter filter) {
        return null;
    }
}
