package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmtermination;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.CommandHandler;

@Component
@RequiredArgsConstructor
public class ConfirmTerminationHandler implements CommandHandler<ConfirmTerminationResult, ConfirmTerminationCommand> {

    private final PolicyRepository policyRepository;

    @Override
    public ConfirmTerminationResult handle(ConfirmTerminationCommand command) {
        Policy policy = policyRepository.withNumber(command.getPolicyNumber());
        policy.confirmChanges(command.getVersionToConfirmNumber());
        return new ConfirmTerminationResult(policy.getNumber(), policy.getPolicyVersions().latestActive().getVersionNumber());
    }
}
