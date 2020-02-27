package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PolicyInfoDtoFinder {

    private final JdbcTemplate jdbcTemplate;

    public List<PolicyInfoDto> findByFilter(PolicyFilter filter) {
        PolicyFilterQueryBuilder policyFilterQueryBuilder = new PolicyFilterQueryBuilder(filter);
        String query = policyFilterQueryBuilder.build();
        return jdbcTemplate.query(query, new PolicyInfoDtoMapRow());
    }

}
