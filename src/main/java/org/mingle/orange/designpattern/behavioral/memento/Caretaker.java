/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.memento;

/**
 * 负责人: 负责保存好备忘录,不能对备忘录的内容进行操作或检查.
 * 
 * @since 1.0
 * @author Mingle
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
