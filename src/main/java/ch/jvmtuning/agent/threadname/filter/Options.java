package ch.jvmtuning.agent.threadname.filter;

import java.util.regex.Pattern;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class Options {

    private final Pattern classPattern;
    private final Pattern methodPattern;

    public Options(final Pattern classPattern,
                   final Pattern methodPattern) {
        this.classPattern = classPattern;
        this.methodPattern = methodPattern;
    }

    public static Options parse(String agentArgs) {
        return new Options(null, null);
    }

    public Pattern getClassPattern() {
        return classPattern;
    }

    public Pattern getMethodPattern() {
        return methodPattern;
    }
}
