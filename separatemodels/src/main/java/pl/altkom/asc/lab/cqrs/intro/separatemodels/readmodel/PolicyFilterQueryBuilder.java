package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.springframework.util.StringUtils;

class PolicyFilterQueryBuilder {

    private static final String SELECT_QUERY = "SELECT policy_id, policy_number, cover_from, cover_to, vehicle, policy_holder, total_premium_amount FROM policy_info_dto";
    private final PolicyFilter filter;

    PolicyFilterQueryBuilder(PolicyFilter filter) {
        this.filter = filter;
    }

    String build() {
        String where = "";
        if (!StringUtils.isEmpty(filter.getNumber())) {
            where += "policy_number = '" + filter.getNumber() + "'";
        }
        String query = SELECT_QUERY;
        if (!StringUtils.isEmpty(where)) {
            query += " WHERE " + where;
        }
        return query;
    }

}
