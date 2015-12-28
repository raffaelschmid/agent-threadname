package ch.jvmtuning.agent.threadname.instrumentation;

import ch.jvmtuning.agent.threadname.filter.FilterClassVisitor;
import ch.jvmtuning.agent.threadname.filter.InstrumentationFilter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;


/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class ThreadNameSetterClassVisitor extends FilterClassVisitor {
    private final String className;

    public ThreadNameSetterClassVisitor(ClassVisitor classVisitor, InstrumentationFilter instrumentationFilter, String className) {
        super(classVisitor, instrumentationFilter);

        this.className = className;
    }

    @Override
    protected MethodVisitor createMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
        return new ThreadNameSetterMethodVisitor(mv, access, new MethodKey(className, name), desc);
    }


}
