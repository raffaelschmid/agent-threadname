package ch.jvmtuning.agent.threadname;

import ch.jvmtuning.agent.threadname.filter.InstrumentationFilter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public abstract class FilterClassVisitor extends ClassVisitor {


    private final InstrumentationFilter instrumentationFilter;

    public FilterClassVisitor(ClassVisitor cv, InstrumentationFilter instrumentationFilter) {
        super(Opcodes.ASM5, cv);

        this.instrumentationFilter = instrumentationFilter;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        final MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

        return (mv != null && instrumentationFilter.shouldInstrumentMethodClass(name)) ? createMethodVisitor(mv) : mv;
    }

    protected abstract MethodVisitor createMethodVisitor(MethodVisitor mv);
}
