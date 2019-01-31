package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmbuyadditionalcover;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.CommandHandler;

@Component
@RequiredArgsConstructor
public class ConfirmBuyAdditionalCoverHandler implements CommandHandler<ConfirmBuyAdditionalCoverResult, ConfirmBuyAdditionalCoverCommand> {

    private final PolicyRepository policyRepository;

    @Override
    public ConfirmBuyAdditionalCoverResult handle(ConfirmBuyAdditionalCoverCommand command) {
        Policy policy = policyRepository.withNumber(command.getPolicyNumber());
        policy.confirmChanges(command.getVersionToConfirmNumber());
        return new ConfirmBuyAdditionalCoverResult(policy.getNumber(), policy.getPolicyVersions().latestActive().getVersionNumber());
    }
}
