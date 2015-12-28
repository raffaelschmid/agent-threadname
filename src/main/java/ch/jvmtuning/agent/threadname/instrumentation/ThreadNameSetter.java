package ch.jvmtuning.agent.threadname.instrumentation;

/**
 *
 */
public class ThreadNameSetter {

    private static final ThreadLocal<String> ORIGINAL_THREAD_NAME = new ThreadLocal<>();

    public static void unsetThreadName() {
        Thread.currentThread().setName(ORIGINAL_THREAD_NAME.get());
    }

    public static void setThreadName(String threadName) throws NoSuchMethodException, SecurityException {

        ORIGINAL_THREAD_NAME.set(Thread.currentThread().getName());

        Thread.currentThread().setName(threadName);
    }


}