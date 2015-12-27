package ch.jvmtuning.agent.threadname.filter;

import java.util.Map;
import java.util.Objects;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum Name {

    CLASS_PATTERN("classPattern"),
    METHOD_PATTERN("methodPattern");

    private final String parameterName;

    Name(String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * All parameters by name
     */
    private static final Map<String, Name> KEYS_BY_PARAMETER = stream(values()).collect(toMap(Name::getParameterName, Objects::requireNonNull));

    public static Name byName(String key) {
        return Objects.requireNonNull(KEYS_BY_PARAMETER.get(key));
    }

    public String getParameterName() {
        return parameterName;
    }

}