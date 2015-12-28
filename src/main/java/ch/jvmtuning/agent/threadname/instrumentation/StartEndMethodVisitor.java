package ch.jvmtuning.agent.threadname.instrumentation;


import asm.MethodVisitor;
import asm.Opcodes;

public abstract class StartEndMethodVisitor extends MethodVisitor {
    protected final int access;
    protected final MethodKey methodKey;
    protected final String methodDesc;

    public StartEndMethodVisitor(MethodVisitor mv, int access, MethodKey methodKey, String desc) {
        super(Opcodes.ASM5, mv);
        this.access = access;
        this.methodKey = methodKey;
        this.methodDesc = desc;
    }

    @Override
    public void visitCode() {
        super.visitCode();

        insertPrologue();
    }

    @Override
    public void visitInsn(int opcode) {
        switch (opcode) {
            case Opcodes.RETURN:
            case Opcodes.IRETURN:
            case Opcodes.LRETURN:
            case Opcodes.FRETURN:
            case Opcodes.DRETURN:
            case Opcodes.ARETURN:
                insertEpilogue();
                break;
        }

        super.visitInsn(opcode);
    }


    protected abstract void insertPrologue();

    protected abstract void insertEpilogue();
}