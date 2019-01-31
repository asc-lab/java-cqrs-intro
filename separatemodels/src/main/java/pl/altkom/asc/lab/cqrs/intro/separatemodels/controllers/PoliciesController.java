package pl.altkom.asc.lab.cqrs.intro.separatemodels.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmbuyadditionalcover.ConfirmBuyAdditionalCoverCommand;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmbuyadditionalcover.ConfirmBuyAdditionalCoverResult;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.buyadditionalcover.BuyAdditionalCoverCommand;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.buyadditionalcover.BuyAdditionalCoverResult;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.cancellastannex.CancelLastAnnexCommand;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.cancellastannex.CancelLastAnnexResult;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmtermination.ConfirmTerminationCommand;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmtermination.ConfirmTerminationResult;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.terminatepolicy.TerminatePolicyCommand;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.terminatepolicy.TerminatePolicyResult;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.CommandBus;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.queries.findpolicies.FindPoliciesQuery;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.queries.getpolicydetails.GetPolicyDetailsQuery;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.queries.getpolicydetails.PolicyDto;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.queries.findpolicies.PolicyInfoDto;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class PoliciesController {

    private final CommandBus bus;

    @PostMapping
    public ResponseEntity<CreatePolicyResult> createPolicy(@RequestBody CreatePolicyCommand command) {
        return ok(bus.executeCommand(command));
    }

    @PostMapping("/buyAdditionalCover")
    public ResponseEntity<BuyAdditionalCoverResult> buyAdditionalCover(@RequestBody BuyAdditionalCoverCommand command) {
        return ok(bus.executeCommand(command));
    }

    @PostMapping("/confirmBuyAdditionalCover")
    public ResponseEntity<ConfirmBuyAdditionalCoverResult> buyAdditionalCover(@RequestBody ConfirmBuyAdditionalCoverCommand command) {
        return ok(bus.executeCommand(command));
    }

    @PostMapping("/terminate")
    public ResponseEntity<TerminatePolicyResult> terminatePolicy(@RequestBody TerminatePolicyCommand command) {
        return ok(bus.executeCommand(command));
    }

    @PostMapping("/confirmTermination")
    public ResponseEntity<ConfirmTerminationResult> terminatePolicy(@RequestBody ConfirmTerminationCommand command) {
        return ok(bus.executeCommand(command));
    }

    @PostMapping("/cancelLastAnnex")
    public ResponseEntity<CancelLastAnnexResult> cancelLastAnnex(@RequestBody CancelLastAnnexCommand command) {
        return ok(bus.executeCommand(command));
    }

    @GetMapping("/{policyNumber}")
    public ResponseEntity<PolicyDto> getPolicyDetails(@PathVariable String policyNumber) {
        return ok(bus.executeQuery(new GetPolicyDetailsQuery(policyNumber)));
    }

    @GetMapping("/find")
    public Collection<PolicyInfoDto> find(@RequestBody FindPoliciesQuery query) {
        return bus.executeQuery(query);
    }
}
