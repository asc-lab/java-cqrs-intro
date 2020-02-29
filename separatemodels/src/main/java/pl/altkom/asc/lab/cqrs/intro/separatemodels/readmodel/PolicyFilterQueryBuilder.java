package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.springframework.util.StringUtils;

class PolicyFilterQueryBuilder {

    private static final String SELECT_QUERY = "SELECT policy_id, policy_number, cover_from, cover_to, vehicle, policy_holder, total_premium_amount FROM policy_info_dto";
    private final PolicyFilter filter;

    PolicyFilterQueryBuilder(PolicyFilter filter) {
        this.filter = filter;
    }

    String build() {
        String where = "1 = 1";
        if (!StringUtils.isEmpty(filter.getNumber())) {
            where += " AND policy_number = '" + filter.getNumber() + "'";
        }
        if (!StringUtils.isEmpty(filter.getHolderFirstName())) {
            where += " AND policy_holder like '%" + filter.getHolderFirstName() + "%'";
        }
        if (!StringUtils.isEmpty(filter.getHolderLastName())) {
            where += " AND policy_holder like '%" + filter.getHolderLastName() + "%'";
        }
        if (filter.getStartDateFrom() != null) {
            where += " AND cover_from >= '" + filter.getStartDateFrom() + "'";
        }
        if (filter.getStartDateTo() != null) {
            where += " AND cover_from <= '" + filter.getStartDateTo() + "'";
        }
        if (!StringUtils.isEmpty(filter.getCarPlateNumber())) {
            where += " AND vehicle like '%" + filter.getCarPlateNumber() + "%'";
        }
        String query = SELECT_QUERY;
        query += " WHERE " + where;
        return query;
    }

}
