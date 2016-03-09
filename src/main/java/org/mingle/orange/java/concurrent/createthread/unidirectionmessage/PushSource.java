/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.createthread.unidirectionmessage;

/**
 * 接口,基于推动的单向流
 * 
 * @since 1.0
 * @author Mingle
 */
public interface PushSource {
    Box produce();
}

/**
 * 推动的阶段
 */
interface PushStage {
    void putA(Box p);
}

/**
 * 双输入推动的阶段
 */
interface DualInputPushStage extends PushStage {
    void putB(Box p);
}
