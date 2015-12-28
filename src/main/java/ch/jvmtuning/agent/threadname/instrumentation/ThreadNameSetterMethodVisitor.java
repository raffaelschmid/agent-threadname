package ch.jvmtuning.agent.threadname.instrumentation;

import asm.MethodVisitor;
import asm.Opcodes;
import asm.Type;

public class ThreadNameSetterMethodVisitor extends StartEndMethodVisitor {

	public ThreadNameSetterMethodVisitor(MethodVisitor mv, int access, MethodKey methodKey, String desc) {
		super(mv, access, methodKey, desc);
	}

	@Override
	protected void insertPrologue() {

		System.out.println("Instrumenting method: " + methodKey);

		// load the method identifier into the stack
		mv.visitLdcInsn(methodKey.toString());


        // invoke the Thread name setter
		super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(ThreadNameSetter.class), "setThreadName","(Ljava/lang/String;)V", false);
	}

	@Override
	protected void insertEpilogue() {
		
		//restore the thread name
		super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(ThreadNameSetter.class), "unsetThreadName", "()V", false);

	}
}