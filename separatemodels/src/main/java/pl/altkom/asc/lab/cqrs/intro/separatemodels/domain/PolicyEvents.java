package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.events.Event;

public class PolicyEvents {

    @Getter
    public static class PolicyCreated extends Event {
        private Policy newPolicy;

        public PolicyCreated(Object source, Policy newPolicy) {
            super(source);
            this.newPolicy = newPolicy;
        }
    }

    @Getter
    public static class PolicyAnnexed extends Event {
        private Policy annexedPolicy;
        private PolicyVersion annexVersion;

        public PolicyAnnexed(Object source, Policy annexedPolicy, PolicyVersion annexVersion) {
            super(source);
            this.annexedPolicy = annexedPolicy;
            this.annexVersion = annexVersion;
        }
    }

    @Getter
    public static class PolicyTerminated extends Event {
        private Policy terminatedPolicy;
        private PolicyVersion terminatedVersion;

        public PolicyTerminated(Object source, Policy terminatedPolicy, PolicyVersion terminatedVersion) {
            super(source);
            this.terminatedPolicy = terminatedPolicy;
            this.terminatedVersion = terminatedVersion;
        }
    }

    @Getter
    public static class PolicyAnnexCancelled extends Event {
        private Policy policy;
        private PolicyVersion cancelledAnnexVersion;
        private PolicyVersion currentVersionAfterAnnexCancellation;

        public PolicyAnnexCancelled(Object source,
                                    Policy policy,
                                    PolicyVersion cancelledAnnexVersion,
                                    PolicyVersion currentVersionAfterAnnexCancellation) {
            super(source);
            this.policy = policy;
            this.cancelledAnnexVersion = cancelledAnnexVersion;
            this.currentVersionAfterAnnexCancellation = currentVersionAfterAnnexCancellation;
        }
    }
}
