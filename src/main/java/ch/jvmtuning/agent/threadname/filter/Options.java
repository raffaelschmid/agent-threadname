package ch.jvmtuning.agent.threadname.filter;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class Options {

    private static final Option DEFAULT_CLASS = new Option(Name.CLASS_PATTERN, ".*");
    private static final Option DEFAULT_METHOD = new Option(Name.CLASS_PATTERN, ".*");


    private final List<Option> options;

    public Options(List<Option> options) {
        this.options = options;
    }

    public String getClassPattern() {
        return options.stream()
                .filter(option -> option.is(Name.CLASS_PATTERN))
                .findFirst().orElse(DEFAULT_CLASS)
                .getValue();
    }

    public String getMethodPattern() {
        return options.stream()
                .filter(option -> option.is(Name.METHOD_PATTERN))
                .findFirst().orElse(DEFAULT_METHOD)
                .getValue();
    }

    public static Options parse(String agentArgs) {

        final List<Option> collectedOptions = agentArgs != null ? stream(agentArgs.split(","))
                .map(part -> part.split("="))
                .filter(part -> part.length > 1)
                .map(part -> new Option(Name.byName(part[0]), part[1]))
                .collect(toList()) : Collections.emptyList();

        return new Options(collectedOptions);
    }


}
