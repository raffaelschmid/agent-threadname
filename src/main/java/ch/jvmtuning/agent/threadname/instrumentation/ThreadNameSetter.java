package ch.jvmtuning.agent.threadname.instrumentation;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class ThreadNameSetter {

    private static final ThreadLocal<String> ORIGINAL_THREAD_NAME = new ThreadLocal<>();
    private static final Map<Class<?>, Boolean> simpleNames = new ConcurrentHashMap<>();


    public static void unsetThreadName() {
        Thread.currentThread().setName(ORIGINAL_THREAD_NAME.get());
    }

    public static void setThreadName(String methodKey, Object[] args) throws NoSuchMethodException, SecurityException {

        ORIGINAL_THREAD_NAME.set(Thread.currentThread().getName());

        // get the method's variable names, convert the string identifier that
        // was embedded into the bytecode back to comparable objects
        String[] varNames = LocalVariablesTable.getMethodVariableNames(MethodKey.fromString(methodKey));

        StringBuilder statefulName = new StringBuilder();

        // append the original thread name
        statefulName.append(Thread.currentThread().getName());

        // when did we get hold of the thread
        statefulName.append(" Start time: ");
        statefulName.append(new Date());

        // add the dynamic state
        statefulName.append(" args: ");

        int index = 0;

        for (String varName : varNames) {
            statefulName.append(varName);
            statefulName.append(" = ");

            if (args.length > index) {
                statefulName.append(formatValue(args[index]));
            }

            if (index < varNames.length - 1) {
                statefulName.append(", ");
            }

            index++;
        }

        // set the stateful thread name
        Thread.currentThread().setName(statefulName.toString());
    }

    private static String formatValue(Object value) throws NoSuchMethodException, SecurityException {
        if (value == null) {
            return "null";
        }

        return value.toString();
    }


}