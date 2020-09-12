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

package org.orange.effectivejava;

import lombok.Getter;

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
 * @author mingle
 */
public class Serialization implements Serializable {
    private static final long serialVersionUID = 1339037634067378632L;

    @Getter private final Date start;
    @Getter private final Date end;

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
            Serialization s = new Serialization(new Date(), new Date());
            oos.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            Serialization f = (Serialization) ois.readObject();
            System.out.println(f.getStart());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

abstract class AbstractFoo {
    private int x;
    private int y;

    /**
     * 跟踪初始化信息
     */
    private enum State {
        NEW, INITIALIZING, INITIALIZED
    }

    private final AtomicReference<State> init = new AtomicReference<>(State.NEW);

    public AbstractFoo(int x, int y) {
        initialize(x, y);
    }

    /**
     * 此构造方法和下面的方法允许父类的readObject方法初始化自己的状态
     */
    protected AbstractFoo() {
    }

    protected final void initialize(int x, int y) {
        if (!init.compareAndSet(State.NEW, State.INITIALIZING))
            throw new IllegalStateException("Already initialized");
        this.x = x;
        this.y = y;
        init.set(State.INITIALIZED);
    }

    protected final int getX() {
        checkInit();
        return x;
    }

    protected final int getY() {
        checkInit();
        return y;
    }

    /**
     * 所有的public或protected方法必须调用
     */
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

    public final void add(String s) {
    }

    /**
     * Serialize this {@code StringList} instance
     *
     * @param s
     * @throws IOException
     * @serialData The size of the list (the numbers of strings it contains) is emitted ({@code int}),
     * followed by all of its elements (each a {@code String}), in the proper sequence.
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
