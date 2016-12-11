/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
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
 * @author mingle
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
