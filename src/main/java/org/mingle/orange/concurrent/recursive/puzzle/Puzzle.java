/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.recursive.puzzle;

import java.util.Set;

/**
 * 表示"搬箱子"之类谜题的抽象类
 * P表示位置类,M表示移动类
 * 
 * @since 1.8
 * @author Mingle
 */
public interface Puzzle<P, M> {
	P initialPosition();
	boolean isGoal(P position);
	Set<M> legalMoves(P position);
	P move(P position, M move);
}
