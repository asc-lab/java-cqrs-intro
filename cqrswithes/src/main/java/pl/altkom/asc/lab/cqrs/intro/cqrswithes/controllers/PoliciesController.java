package pl.altkom.asc.lab.cqrs.intro.cqrswithes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.buyadditionalcover.BuyAdditionalCoverCommand;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.buyadditionalcover.BuyAdditionalCoverResult;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmbuyadditionalcover.ConfirmBuyAdditionalCoverCommand;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmbuyadditionalcover.ConfirmBuyAdditionalCoverResult;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmtermination.ConfirmTerminationCommand;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmtermination.ConfirmTerminationResult;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.terminatepolicy.TerminatePolicyCommand;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.terminatepolicy.TerminatePolicyResult;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.CommandBus;

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
}
