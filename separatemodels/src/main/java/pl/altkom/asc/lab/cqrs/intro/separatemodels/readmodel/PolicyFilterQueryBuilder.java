package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

class PolicyFilterQueryBuilder {
    private String query = "SELECT policy_id, policy_number, cover_from, cover_to, vehicle, policy_holder, total_premium_amount FROM policy_info_dto";

    PolicyFilterQueryBuilder where() {
        query += " WHERE 1 = 1";
        return this;
    }

    PolicyFilterQueryBuilder policyNumberEquals(String policyNumber) {
        if (!StringUtils.isEmpty(policyNumber)) {
            query += " AND policy_number = '" + policyNumber + "'";
        }
        return this;
    }

    PolicyFilterQueryBuilder policyHolderLike(String policyHolder) {
        if (!StringUtils.isEmpty(policyHolder)) {
            query += " AND policy_holder like '%" + policyHolder + "%'";
        }
        return this;
    }

    PolicyFilterQueryBuilder policyStartDateFrom(LocalDate startDate) {
        if (startDate != null) {
            query += " AND cover_from >= '" + startDate + "'";
        }
        return this;
    }

    PolicyFilterQueryBuilder policyStartDateTo(LocalDate startDate) {
        if (startDate != null) {
            query += " AND cover_from <= '" + startDate + "'";
        }
        return this;
    }

    PolicyFilterQueryBuilder policyCarPlateNumberLike(String carPlateNumber) {
        if (!StringUtils.isEmpty(carPlateNumber)) {
            query += " AND vehicle like '%" + carPlateNumber + "%'";
        }
        return this;
    }

    List<PolicyInfoDto> execute(JdbcTemplate jdbcTemplate, PolicyInfoDtoMapRow policyInfoDtoMapRow) {
        return jdbcTemplate.query(query, policyInfoDtoMapRow);
    }
}
