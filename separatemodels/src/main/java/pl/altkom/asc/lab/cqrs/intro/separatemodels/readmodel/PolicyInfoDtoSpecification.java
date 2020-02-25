package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PolicyInfoDtoSpecification implements Specification<PolicyInfoDto> {
  @Override
  public Predicate toPredicate(Root<PolicyInfoDto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
    return null;
  }
}
