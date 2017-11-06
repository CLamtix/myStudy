package proxy.client;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmClassGenerator extends ClassLoader {
	public static void main(String[] args) throws Exception {
		ClassWriter classWriter = new ClassWriter(0);
		classWriter.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC, "Plan", null, "java/lang/Object", null);
		
		//构建构造器
		MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
		
		//构建方法
		MethodVisitor mv1 = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "doSomeThing", "()V", null, null);
		mv1.visitCode();
		mv1.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv1.visitLdcInsn("i'm working tonight!");
		mv1.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		mv1.visitInsn(Opcodes.RETURN);
		mv1.visitMaxs(2, 2);
		mv1.visitEnd();
		classWriter.visitEnd();
		
		byte[] bytes = classWriter.toByteArray();
		
		FileOutputStream fos = new FileOutputStream(new File("F://Plan.class"));
		fos.write(bytes);
		fos.flush();
		fos.close();
		Class<?> clazz = new AsmClassGenerator().defineClass("Plan", bytes, 0, bytes.length);
		System.out.println(clazz.getName());
		
		Method m = clazz.getMethod("doSomeThing");
		m.invoke(m.getDeclaringClass().newInstance(), null);
	}
	
}
