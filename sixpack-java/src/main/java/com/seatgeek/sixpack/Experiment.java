package com.seatgeek.sixpack;

import java.util.Set;

/**
 * An Experiment that has been created but has not yet been sent to the Sixpack server.
 * To actually start this Experiment, use the {@link #participate()}
 * method.
 */
public class Experiment {

    /**
     * The {@link Sixpack} instance that this {@link Experiment} is associated with
     */
    public final Sixpack sixpack;

    /**
     * The name of this test. This is the name that will appear in the web dashboard
     */
    public final String name;

    /**
     * The set of Alternatives in use in this Experiment
     */
    public final Set<Alternative> alternatives;

    /**
     * (optional) The forced {@link Alternative} that this {@link Experiment} will use
     */
    public final Alternative forcedChoice;

    /**
     * (optional) The % of users that will participate in this Experiment
     */
    public final Double trafficFraction;

    Experiment(Sixpack sixpack, String name, Set<Alternative> alternatives, Alternative forcedChoice, Double trafficFraction) {
        this.sixpack = sixpack;
        this.name = name;
        this.alternatives = alternatives;
        this.forcedChoice = forcedChoice;
        this.trafficFraction = trafficFraction;
    }

    /**
     * Starts this {@link Experiment} by sending the experiment data to the Sixpack server and returning
     * a {@link ParticipatingExperiment}.
     *
     * If an exception occurs trying to participate in the experiment, the control alternative will
     * be selected and returned.
     *
     * This call makes blocking network requests.
     */
    public ParticipatingExperiment participate() {
        return sixpack.participate(this);
    }

    /**
     * @return true if this Experiment was created with a forced {@link Alternative} choice
     */
    public boolean hasForcedChoice() {
        return forcedChoice != null;
    }

    @Override
    public String toString() {
        return name;
    }

    public Alternative getControlAlternative() {
        return alternatives.iterator().next();
    }
}
