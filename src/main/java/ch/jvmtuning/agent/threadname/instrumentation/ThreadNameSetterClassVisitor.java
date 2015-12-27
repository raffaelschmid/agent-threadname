package ch.jvmtuning.agent.threadname.instrumentation;

import ch.jvmtuning.agent.threadname.filter.FilterClassVisitor;
import ch.jvmtuning.agent.threadname.filter.InstrumentationFilter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class ThreadNameSetterClassVisitor extends FilterClassVisitor {
    public ThreadNameSetterClassVisitor(ClassVisitor classVisitor, InstrumentationFilter instrumentationFilter) {
        super(classVisitor, instrumentationFilter);
    }

    @Override
    protected MethodVisitor createMethodVisitor(MethodVisitor mv) {
        return mv;
    }
}
