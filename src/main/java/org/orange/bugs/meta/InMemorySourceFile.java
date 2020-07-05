package org.orange.bugs.meta;

public class InMemorySourceFile extends javax.tools.SimpleJavaFileObject {
    final String code;

    protected InMemorySourceFile(String className, String classCode) {
        super(InMemoryJavaFileManager.makeURIforClass(className, Kind.SOURCE), Kind.SOURCE);
        code = classCode;
    }

    @Override
    public CharSequence getCharContent(boolean arg0) {
        return code;
    }
}
