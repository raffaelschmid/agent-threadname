package ch.jvmtuning.agent.threadname.instrumentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LocalVariablesTable {


    /**
     * thread safe implementation because of concurrent classloading
     */
    private static Map<MethodKey, List<Parameter>> map = new ConcurrentHashMap<>();

    public static String[] getMethodVariableNames(MethodKey methodKey) {
        List<Parameter> vars = map.get(methodKey);

        if (vars == null) {
            return new String[0];
        }

        /**
         * TODO: Cache Result
         */
        return vars.stream()
                .sorted((e1, e2) -> Integer.compare(e1.getPosition(), e2.getPosition()))
                .map(Parameter::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    public static void addVariable(MethodKey methodKey, int index, String name) {
        List<Parameter> vars = map.get(methodKey);

        //new cut point method? => add to map
        if (vars == null) {
            vars = new ArrayList<>();
            map.put(methodKey, vars);
        }

        //if we haven't added this variable, let's do so
        vars.add(new Parameter(index, name));
    }

}