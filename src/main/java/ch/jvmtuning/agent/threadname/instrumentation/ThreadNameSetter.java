package ch.jvmtuning.agent.threadname.instrumentation;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 */
public class ThreadNameSetter {

    private static final ThreadLocal<String> ORIGINAL_THREAD_NAME = new ThreadLocal<>();

    public void after() {
        Thread.currentThread().setName(ORIGINAL_THREAD_NAME.get());
    }

    public void before(@Origin Method method, @AllArguments Object[] arguments) throws NoSuchMethodException, SecurityException {

        Arrays.stream(method.getParameters()).forEach(System.out::println);
        Arrays.stream(arguments).forEach(System.out::println);

        ORIGINAL_THREAD_NAME.set(Thread.currentThread().getName());

        Thread.currentThread().setName("foobar");
    }


}