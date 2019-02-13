package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class PolicyVersionsListDto {
    private String policyNumber;
    private List<PolicyVersionInfoDto> versionsInfo;

    @Getter
    @AllArgsConstructor
    public static class PolicyVersionInfoDto {
        private int number;
        private LocalDate versionFrom;
        private LocalDate versionTo;
        private String versionStatus;
    }

    static class PolicyVersionInfoDtoRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            return new PolicyVersionInfoDto(
                    rs.getInt("version_number"),
                    rs.getDate("version_from").toLocalDate(),
                    rs.getDate("version_to").toLocalDate(),
                    rs.getString("version_status")
            );
        }
    }
}
