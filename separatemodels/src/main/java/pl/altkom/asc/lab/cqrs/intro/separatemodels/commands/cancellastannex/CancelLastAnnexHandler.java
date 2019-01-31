package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.cancellastannex;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.CommandHandler;

@Component
@RequiredArgsConstructor
public class CancelLastAnnexHandler implements CommandHandler<CancelLastAnnexResult, CancelLastAnnexCommand> {

    private final PolicyRepository policyRepository;

    @Override
    public CancelLastAnnexResult handle(CancelLastAnnexCommand command) {
        Policy policy = policyRepository.withNumber(command.getPolicyNumber());
        policy.cancelLastAnnex();
        return new CancelLastAnnexResult(policy.getNumber(), policy.getPolicyVersions().latestActive().getVersionNumber());
    }
}
