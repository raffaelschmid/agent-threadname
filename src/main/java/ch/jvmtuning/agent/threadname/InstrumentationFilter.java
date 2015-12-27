package ch.jvmtuning.agent.threadname;

import java.util.regex.Pattern;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class InstrumentationFilter {
    private final Pattern classPattern;
    private final Pattern methodPattern;

    public InstrumentationFilter(Pattern classPattern, Pattern methodPattern) {
        this.classPattern = classPattern;
        this.methodPattern = methodPattern;
    }

    public boolean shouldInstrumentClass(String className) {
        return "spec/validity/ExpectedResourceDigests".equals(className);
    }
}
