package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.buyadditionalcover;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.CommandHandler;

@Component
@RequiredArgsConstructor
public class BuyAdditionalCoverHandler implements CommandHandler<BuyAdditionalCoverResult, BuyAdditionalCoverCommand> {

    private final PolicyRepository policyRepository;

    @Override
    public BuyAdditionalCoverResult handle(BuyAdditionalCoverCommand command) {
        Policy policy = policyRepository.getById(command.getPolicyId());

        policy.extendCoverage(
                command.getEffectiveDateOfChange(),
                command.getNewCoverCode(),
                command.getNewCoverPrice(),
                command.getNewCoverPriceUnit()
        );
        policyRepository.save(policy, policy.getVersion());

        return new BuyAdditionalCoverResult(
                policy.getId(),
                policy.getPolicyVersions().last().getVersionNumber()
        );
    }
}
