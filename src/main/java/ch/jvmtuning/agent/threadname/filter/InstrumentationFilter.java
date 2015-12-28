package ch.jvmtuning.agent.threadname.filter;

import java.util.regex.Pattern;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class InstrumentationFilter {

    private final Pattern classPattern;
    private final Pattern methodPattern;


    public InstrumentationFilter(String classPattern, String methodPattern) {
        this.classPattern = Pattern.compile(classPattern);
        this.methodPattern = Pattern.compile(methodPattern);
    }

    public boolean shouldInstrumentClass(String className) {
        return classPattern.matcher(normalizeClassName(className)).matches();
    }

    private String normalizeClassName(String className) {
        return className.replaceAll("/", ".");
    }

    public boolean shouldInstrumentMethod(String methodName) {
        return methodPattern.matcher(methodName).matches();
    }
}
