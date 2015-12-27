package ch.jvmtuning.agent.threadname;

import ch.jvmtuning.agent.threadname.filter.Options;

import java.lang.instrument.Instrumentation;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class Agent {

    /**
     * This method will be invoked when the agent is attached.
     *
     * @param agentArgs
     * @param instrumentation
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {

        final Options options = Options.parse(agentArgs);

        System.out.println("------------------------------------------------------------");
        System.out.println("Initializing thread-name agent with the following options:");
        System.out.println("Class Pattern : " + options.getClassPattern());
        System.out.println("Method Pattern: " + options.getMethodPattern());
        System.out.println("------------------------------------------------------------");

        final InstrumentationFilter instrumentationFilter = new InstrumentationFilter(
                options.getClassPattern(),
                options.getMethodPattern()
        );

        instrumentation.addTransformer(new ThreadNameTransformer(instrumentationFilter));
    }

    /**
     * This method is used as a launcher to attach the agent to a live jvm.
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
