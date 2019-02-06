package pl.altkom.asc.lab.cqrs.intro.nocqrs.db.specifications;

import org.springframework.data.jpa.domain.Specification;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.PolicyRepository;

public class PolicySpecification extends BaseSpecification<Policy, PolicyRepository.PolicyFilter> {

    @Override
    public Specification<Policy> getFilter(PolicyRepository.PolicyFilter request) {
        return null;
    }

    private Specification<Policy> numberContains(String number) {
        return policyAttributeContains("number", number);
    }

    private Specification<Policy> policyAttributeContains(String attribute, String value) {
        return (root, query, cb) -> {
            if(value == null) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get(attribute)),
                    containsLowerCase(value)
            );
        };
    }
}
