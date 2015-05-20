/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.annotations.atunit;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;

import org.mingle.orange.java.util.BinaryFile;
import org.mingle.orange.java.util.ProcessFiles;

/**
 * 移除@Unit测试代码工具
 *
 * @since 1.8
 * @author Mingle
 */
public class AtUnitRemover implements ProcessFiles.Strategy {
	private static boolean remove = false;
	
	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("-r")) {
			remove = true;
			String[] nargs = new String[args.length - 1];
			System.arraycopy(args, 1, nargs, 0, nargs.length);
			args = nargs;
		}
		new ProcessFiles(new AtUnitRemover(), "class").start(args);
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.util.ProcessFiles.Strategy#process(java.io.File)
	 */
	@Override
	public void process(File cFile) {
		boolean modified = false;
		try {
			String cName = ClassNameFinder.thisClass(BinaryFile.read(cFile));
			if (!cName.contains("."))
				return; // 忽略未在包中的类
			ClassPool cPool = ClassPool.getDefault();
			CtClass ctClass = cPool.get(cName);
			for (CtMethod method : ctClass.getDeclaredMethods()) {
				MethodInfo mi = method.getMethodInfo();
				AnnotationsAttribute attr = (AnnotationsAttribute) mi.getAttribute(AnnotationsAttribute.visibleTag);
				if (attr == null)
					continue;
				for (Annotation ann : attr.getAnnotations()) {
					if (ann.getTypeName().startsWith("org.mingle.orange.java.speciality.annotations")) {
						System.out.println(ctClass.getName() + " Method: " + mi.getName() + " " + ann);
						if (remove) {
							ctClass.removeMethod(method);
							modified = true;
						}
					}
				}
			}
			if (modified)
				ctClass.toBytecode(new DataOutputStream(new FileOutputStream(cFile)));
			ctClass.detach();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
