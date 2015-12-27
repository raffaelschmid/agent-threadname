package ch.jvmtuning.agent.threadname;


import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class ThreadNameTransformer implements ClassFileTransformer {


    private final InstrumentationFilter instrumentationFilter;

    public ThreadNameTransformer(InstrumentationFilter instrumentationFilter) {
        this.instrumentationFilter = instrumentationFilter;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        final byte[] retVal;
        if (instrumentationFilter.shouldInstrumentClass(className)) {

            System.out.println("instrumenting class " + className);

            retVal = null;
        } else {
            retVal = null;
        }

        return retVal;
    }


}
