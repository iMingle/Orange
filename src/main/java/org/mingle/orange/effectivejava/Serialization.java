/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 序列化
 * 
 * @since 1.0
 * @author Mingle
 */
public class Serialization implements Serializable {
    private static final long serialVersionUID = 1339037634067378632L;
    
    private final Date start;
    private final Date end;
    
    public Serialization(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 序列化代理,优先选择的方式,可以阻止伪字节流的攻击,并且外围类的域可以是final的
     */
    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = -726413810585864902L;
        
        private final Date start;
        private final Date end;
        
        public SerializationProxy(Serialization s) {
            this.start = s.start;
            this.end = s.end;
        }
        
        /**
         * 返回真正的序列化类实例,将代理转变回外围类的实例
         * 
         * @return
         */
        private Object readResolve() {
            return new Serialization(start, end);
        }
    }
    
    /**
     * 序列化是用代理类取代本身
     * 
     * @return
     */
    private Object writeReplace() {
        return new SerializationProxy(this);
    }
    
    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    /**
     * 如果构造是的默认值违背了约束条件
     * 
     * @throws InvalidObjectException
     */
    @SuppressWarnings("unused")
    private void readObjectNoData() throws InvalidObjectException {
        throw new InvalidObjectException("Stream data required");
    }

    public static void main(String[] args) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            Foo foo = new Foo(5, 5);
            oos.writeObject(foo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            Foo f = (Foo) ois.readObject();
            System.out.println(f.getX());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

}

abstract class AbstractFoo {
    private int x;
    private int y;
    
    // 跟踪初始化信息
    private enum State {
        NEW, INITIALIZING, INITIALIZED
    }
    private final AtomicReference<State> init = new AtomicReference<>(State.NEW);
    
    public AbstractFoo(int x, int y) {
        initialize(x, y);
    }
    
    // 此构造方法和下面的方法允许父类的readObject方法初始化自己的状态
    protected AbstractFoo() {}
    protected final void initialize(int x, int y) {
        if (!init.compareAndSet(State.NEW, State.INITIALIZING))
            throw new IllegalStateException("Already initialized");
        this.x = x;
        this.y = y;
        init.set(State.INITIALIZED);
    }
    
    // 提供访问内部状态
    protected final int getX() {
        checkInit();
        return x;
    }
    
    protected final int getY() {
        checkInit();
        return y;
    }
    
    // 所有的public或protected方法必须调用
    private void checkInit() {
        if (init.get() != State.INITIALIZED)
            throw new IllegalStateException("Uninitialized");
    }
}

class Foo extends AbstractFoo implements Serializable {
    private static final long serialVersionUID = 3968193554302599953L;
    
    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        // 手动反序列化并初始化父类的状态
        int x = in.readInt();
        int y = in.readInt();
        initialize(x, y);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // 手动序列化父类的状态
        out.writeInt(getX());
        out.writeInt(getY());
    }
    
    public Foo(int x, int y) {
        super(x, y);
    }
}

final class StringList implements Serializable {
    private static final long serialVersionUID = -5685050420587458489L;
    
    private transient int size = 0;
    private transient Entry head = null;
    
    private static class Entry {
        String data;
        Entry next;
        @SuppressWarnings("unused")
        Entry previous;
    }
    
    public final void add(String s) {}
    
    /**
     * Serialize this {@code StringList} instance
     * 
     * @serialData The size of the list (the numbers of strings it contains) is emitted ({@code int}),
     * followed by all of its elements (each a {@code String}), in the proper sequence.
     * 
     * @param s
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(size);
        for (Entry e = head; e != null; e = e.next)
            s.writeObject(e.data);
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int numElements = s.readInt();
        
        for (int i = 0; i < numElements; i++)
            add((String) s.readObject());
    }
}