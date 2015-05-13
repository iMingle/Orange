/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.annotations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
@SupportedAnnotationTypes({"org.mingle.orange.java.speciality.annotations.ExtractInterface"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class InterfaceExtractorProcessor extends AbstractProcessor {

	/* (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		Messager messager = processingEnv.getMessager();
		PrintWriter writer = null;
		JavaFileObject sourceFile = null;
		for (TypeElement typeElement : annotations) {
			for (Element e : roundEnv.getElementsAnnotatedWith(typeElement)) {
				if (ElementKind.CLASS == e.getKind()) {
					for (Element e1 : e.getEnclosedElements()) {
						if (ElementKind.METHOD == e1.getKind()) {
							try {
								sourceFile = processingEnv.getFiler().createSourceFile("IMultiply", e1);
							} catch (IOException e2) {
								e2.printStackTrace();
							}
						}
					}
				}
				
				
				
				System.out.println(e.getEnclosedElements());
				System.out.println(e.getAnnotation(ExtractInterface.class));
				System.out.println(e.getAnnotationMirrors().get(0));
				System.out.println(e.getClass());
				System.out.println(e.getEnclosingElement());
				System.out.println(e.getKind());
				System.out.println(e.getModifiers());
				System.out.println(e.getSimpleName());
//				Multiply(),multiply(int,int),add(int,int),main(java.lang.String[])
//				@org.mingle.orange.java.speciality.annotations.ExtractInterface(value=IMultiply)
//				@org.mingle.orange.java.speciality.annotations.ExtractInterface("IMultiply")
//				class com.sun.tools.javac.code.Symbol$ClassSymbol
//				org.mingle.orange.java.speciality.annotations
//				CLASS
//				[public]
//				Multiply
			}
		}
		return true;
	}

}
