package ch.jvmtuning.agent.threadname;

import ch.jvmtuning.agent.threadname.filter.Options;
import ch.jvmtuning.agent.threadname.instrumentation.ThreadNameSetter;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.implementation.MethodDelegation.to;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class ThreadNameSetterAgent {

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


        new AgentBuilder.Default()
                .type(ElementMatchers.nameMatches(options.getClassPattern()))
                .transform((builder, type) -> {
                    return builder
                            .method(ElementMatchers.nameMatches(options.getMethodPattern()))
                            .intercept(to(new ThreadNameSetter()).filter(named("before"))
                                    .andThen(SuperMethodCall.INSTANCE
                                            .andThen(to(new ThreadNameSetter()).filter(named("after")))));
                })
                .installOn(instrumentation);
    }

    /**
     * This method is used as a launcher to attach the agent to a live jvm.
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
