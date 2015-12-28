package ch.jvmtuning.agent.threadname.filter;

import asm.ClassVisitor;
import asm.MethodVisitor;
import asm.Opcodes;

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

        if ((mv == null) || (!instrumentationFilter.shouldInstrumentMethod(name))) {
            return mv;
        }

        return createMethodVisitor(mv, access, name, desc);
    }

    protected abstract MethodVisitor createMethodVisitor(MethodVisitor mv, int access, String name, String desc);
}
