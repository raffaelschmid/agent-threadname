package ch.jvmtuning.agent.threadname;


import ch.jvmtuning.agent.threadname.filter.InstrumentationFilter;
import ch.jvmtuning.agent.threadname.instrumentation.ThreadNameSetterClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class Transformer implements ClassFileTransformer {


    private final InstrumentationFilter instrumentationFilter;

    public Transformer(InstrumentationFilter instrumentationFilter) {
        this.instrumentationFilter = instrumentationFilter;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        final byte[] retVal;
        if (instrumentationFilter.shouldInstrumentClass(className)) {

            System.out.println("instrumenting class " + className);

            // reader will scan initiated class file
            final ClassReader classReader = new ClassReader(classfileBuffer);

            // writer will write changes to new class
            final ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

            // visitor will be staged between reader and writer
            ClassVisitor classVisitor = new ThreadNameSetterClassVisitor(classWriter, instrumentationFilter, className);

            // initiate visitor pattern
            classReader.accept(classVisitor, 0);

            // return byte array
            retVal = classWriter.toByteArray();
        } else {
            retVal = null;
        }

        return retVal;
    }


}
