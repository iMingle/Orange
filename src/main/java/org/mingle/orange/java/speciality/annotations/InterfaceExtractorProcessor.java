/*
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
 * limitations under the License.
 */

package org.mingle.orange.java.speciality.annotations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

/**
 *
 * @author mingle
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
        PrintWriter writer = null;
        JavaFileObject sourceFile = null;
        for (TypeElement typeElement : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
                if (ElementKind.CLASS == element.getKind()) {
                    try {
                        sourceFile = processingEnv.getFiler().createSourceFile(element.getAnnotation(ExtractInterface.class).value());
                        writer = new PrintWriter(sourceFile.openWriter());
                        writer.println(element.getEnclosingElement() + ";");
                        writer.println("public interface " + element.getAnnotation(ExtractInterface.class).value() + " {");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    for (Element e : element.getEnclosedElements()) {
                        if (ElementKind.METHOD == e.getKind()) {
                            if (e.getModifiers().contains(Modifier.PUBLIC) && !e.getModifiers().contains(Modifier.STATIC)) {
                                ExecutableElement ee = (ExecutableElement) e;
                                writer.print("    public ");
                                writer.print(ee.getReturnType() + " ");
                                writer.print(ee.getSimpleName() + "(");
                                int i = 0;
                                for (VariableElement parm : ee.getParameters()) {
                                    String body = parm.getEnclosingElement().toString();
                                    body = body.substring(body.indexOf('(') + 1, body.indexOf(')'));
                                    String[] parms = body.split(",");
                                    if (i == parms.length - 1) {
                                        writer.print(parms[i++] + " " + parm.getSimpleName());
                                    } else {
                                        writer.print(parms[i++] + " " + parm.getSimpleName() + ", ");
                                    }
                                }
                                writer.println(");");
                            }
                        }
                    }
                    
                    writer.println('}');
                    writer.close();
                }
            }
        }
        return true;
    }

}
