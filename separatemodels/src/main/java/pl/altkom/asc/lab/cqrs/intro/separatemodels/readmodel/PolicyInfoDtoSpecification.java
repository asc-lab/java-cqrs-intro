package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PolicyInfoDtoSpecification implements Specification<PolicyInfoDto> {

  private final PolicyFilter filter;

  public PolicyInfoDtoSpecification(PolicyFilter filter) {
    this.filter = filter;
  }

  @Override
  public Predicate toPredicate(Root<PolicyInfoDto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();
    if (filter.getNumber() != null) {
      predicates.add(criteriaBuilder.equal(root.get("policy_number"), filter.getNumber()));
    }
    return criteriaQuery.where(predicates.toArray(new Predicate[0])).getRestriction();
  }
}
