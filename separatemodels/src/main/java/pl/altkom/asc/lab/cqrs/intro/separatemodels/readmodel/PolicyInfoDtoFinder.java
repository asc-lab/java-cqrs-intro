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
        String query = "SELECT policy_id, policy_number, cover_from, cover_to, vehicle, policy_holder, total_premium_amount FROM policy_info_dto";
        return jdbcTemplate.query(query, new PolicyInfoDtoMapRow());
    }
}
