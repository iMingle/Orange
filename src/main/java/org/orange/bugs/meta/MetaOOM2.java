package org.orange.bugs.meta;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * 
 * Start the program with -XX:+UseCompressedClassPointers -XX:MaxMetaspaceSize=60m.
 * 
 * Test loads a number of small classes, each in its own classloader. Then removes them and does
 * a GC. Then starts loading large classes until an OOM occurs.
 * 
 * @author Thomas Stuefe
 *
 */
public class MetaOOM2 {

	static JavaCompiler compiler = null;
	static InMemoryJavaFileManager fileManager = null;

	static boolean compileSourceForClassName(String classname, int sizeFactor) {
		ClassLoader cl = null;

		String code = SourceCodeGenerator.makeSource(sizeFactor).replaceAll("CLASSNAME", classname);

		InMemorySourceFile sourceFile = new InMemorySourceFile(classname, code);
		List<JavaFileObject> sourceFiles = new ArrayList<>();
		sourceFiles.add(sourceFile);

		if (compiler == null) {
			compiler = ToolProvider.getSystemJavaCompiler();
		}

		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

		if (fileManager == null) {
			fileManager = new InMemoryJavaFileManager(compiler.getStandardFileManager(diagnostics, null, null));
		}

		CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, sourceFiles);

		boolean success = task.call();
		for (Diagnostic diagnostic : diagnostics.getDiagnostics()) { // error?
			System.out.println(diagnostic.getCode());
			System.out.println(diagnostic.getKind());
			System.out.println(diagnostic.getPosition());
			System.out.println(diagnostic.getStartPosition());
			System.out.println(diagnostic.getEndPosition());
			System.out.println(diagnostic.getSource());
			System.out.println(diagnostic.getMessage(null));
		}

		return success;
	}

	public static void main(String[] args) throws Exception {
		int numSmallClasses = 3000;
//		int numSmallClasses = 0;
		int numLargeClasses = 10000;

		LinkedList<ClassLoader> smallLoaders = new LinkedList<>();

		// create and load n small classes in n classloaders
		for (int i = 0; i < numSmallClasses; i++) {
			String className = "smallclass" + i;
			if (compileSourceForClassName(className, 10)) {
				ClassLoader cl = fileManager.getClassLoader(null);
				smallLoaders.add(cl);
				Class<?> clazz = Class.forName(className, true, cl);
				if (i % 100 == 0) {
					System.out.println("small classes loaded: " + i);
					System.gc();
				}
			}
		}

		System.out.print("Loaded " + numSmallClasses + " small classes.");

		// clean all up
		smallLoaders.clear();
		System.gc();

		System.out.print("Removed small classes.");

		// Now create, inside one classloader, many large classes.
//		compileSourceForClassName("name", 1);
		ClassLoader cl2 = fileManager.getClassLoader(null);
		LinkedList<Class> largeClasses = new LinkedList<>();
		for (int n = 0; n < numLargeClasses; n++) {
			try {
				String className = "largeclass" + n;
				if (compileSourceForClassName(className, 100)) {
					Class<?> clazz = Class.forName(className, true, cl2);
					largeClasses.add(clazz);
					if (n % 100 == 0) {
						System.out.println("large classes loaded: " + n);
						System.gc();
					}

				}
			} catch (OutOfMemoryError e) {
				System.out.println("Final Result: " + n + " large classes loaded.");
				throw e;
			}
		}
	}

}
