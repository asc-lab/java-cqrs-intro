package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.terminatepolicy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.CommandHandler;

@Component
@RequiredArgsConstructor
public class TerminatePolicyHandler implements CommandHandler<TerminatePolicyResult, TerminatePolicyCommand> {

    private final PolicyRepository policyRepository;

    @Override
    public TerminatePolicyResult handle(TerminatePolicyCommand command) {
        Policy policy = policyRepository.withNumber(command.getPolicyNumber());
        policy.terminatePolicy(command.getTerminationDate());
        return new TerminatePolicyResult(policy.getNumber(), policy.getPolicyVersions().last().getVersionNumber());
    }
}
