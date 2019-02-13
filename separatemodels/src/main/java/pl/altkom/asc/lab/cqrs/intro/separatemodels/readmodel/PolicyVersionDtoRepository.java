package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyVersion;

import java.util.List;

public interface PolicyVersionDtoRepository extends CrudRepository<PolicyVersionDto, Long> {

    @Query("UPDATE policy_version_dto " +
            "SET " +
            "version_status = :version.versionStatus " +
            "WHERE " +
            "policy_version_id = :version.policyVersionId")
    void update(@Param("version") PolicyVersion version);

    @Query(value = "SELECT " +
            "id, policy_version_id, policy_id, " +
            "policy_number, version_number, " +
            "product_code, " +
            "version_status, policy_status, " +
            "policy_holder, insured, car, " +
            "cover_from, cover_to, version_from, version_to, " +
            "total_premium_amount " +
            "FROM policy_version_dto " +
            "WHERE " +
            "policy_number = :policyNumber " +
            "AND version_number = :versionNumber",
            rowMapperClass = PolicyVersionDto.PolicyVersionDtoRowMapper.class)
    PolicyVersionDto findByPolicyNumberAndVersionNumber(
            @Param("policyNumber") String policyNumber,
            @Param("versionNumber") int versionNumber);

    @Query("SELECT * " +
            "FROM policy_version_cover_dto " +
            "WHERE " +
            "policy_version_dto = :policyVersionDtoId")
    List<PolicyVersionDto.PolicyVersionCoverDto> getCoversInVersion(@Param("policyVersionDtoId") Long policyVersionDtoId);

    @Query(value = "SELECT " +
            "version_number, " +
            "version_from, " +
            "version_to, " +
            "version_status " +
            "FROM policy_version_dto " +
            "WHERE " +
            "policy_number = :policyNumber",
            rowMapperClass = PolicyVersionsListDto.PolicyVersionInfoDtoRowMapper.class)
    List<PolicyVersionsListDto.PolicyVersionInfoDto> findVersionsByPolicyNumber(@Param("policyNumber") String policyNumber);
}
