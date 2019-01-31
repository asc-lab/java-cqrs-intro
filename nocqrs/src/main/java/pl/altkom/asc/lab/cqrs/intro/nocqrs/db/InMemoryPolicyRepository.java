package pl.altkom.asc.lab.cqrs.intro.nocqrs.db;

import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.PolicyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryPolicyRepository implements PolicyRepository {

    private Map<String, Policy> POLICIES = new ConcurrentHashMap<>();

    @Override
    public Policy withNumber(String offerNumber) {
        return POLICIES.get(offerNumber);
    }

    @Override
    public void add(Policy policy) {
        POLICIES.put(policy.getNumber(), policy);
    }

    @Override
    public List<Policy> find(PolicyFilter filter) {
        //TODO: add filtering
        return new ArrayList<>(POLICIES.values());
    }
}
