package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmbuyadditionalcover;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.CommandHandler;

@Component
@RequiredArgsConstructor
public class ConfirmBuyAdditionalCoverHandler implements CommandHandler<ConfirmBuyAdditionalCoverResult, ConfirmBuyAdditionalCoverCommand> {

    private final PolicyRepository policyRepository;

    @Override
    public ConfirmBuyAdditionalCoverResult handle(ConfirmBuyAdditionalCoverCommand command) {
        Policy policy = policyRepository.getById(command.getPolicyId());

        policy.confirmTermination(command.getVersionToConfirmNumber());
        policyRepository.save(policy, policy.getVersion());

        return new ConfirmBuyAdditionalCoverResult(
                policy.getId(),
                policy.getPolicyVersions().latestActive().getVersionNumber()
        );
    }
}
