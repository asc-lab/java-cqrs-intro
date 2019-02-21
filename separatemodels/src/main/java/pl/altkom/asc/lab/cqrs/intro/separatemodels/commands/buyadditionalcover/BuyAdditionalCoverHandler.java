package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.buyadditionalcover;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.*;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs.CommandHandler;

@Component
@Transactional
@RequiredArgsConstructor
public class BuyAdditionalCoverHandler implements CommandHandler<BuyAdditionalCoverResult, BuyAdditionalCoverCommand> {

    private final PolicyRepository policyRepository;

    @Override
    public BuyAdditionalCoverResult handle(BuyAdditionalCoverCommand command) {
        Policy policy = policyRepository.withNumber(command.getPolicyNumber());
        Cover newCover = policy.getProduct().getCovers().withCode(command.getNewCoverCode()).get();
        policy.extendCoverage(
                command.getEffectiveDateOfChange(),
                new CoverPrice(newCover, MonetaryAmount.from(command.getNewCoverPrice()), command.getNewCoverPriceUnit())
        );
        PolicyVersion newPolicyVersion = policy.getPolicyVersions().last();
        return new BuyAdditionalCoverResult(policy.getNumber(), newPolicyVersion.getVersionNumber());
    }
}
