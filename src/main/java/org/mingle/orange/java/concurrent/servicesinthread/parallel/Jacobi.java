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

package org.mingle.orange.java.concurrent.servicesinthread.parallel;

/**
 * Jacobi算法,计算树
 * 
 * @since 1.0
 * @author Mingle
 */
public class Jacobi extends NullFJTask {
    static final double EPSILON = 0.001; // convergence criterion
    final JTree root;
    final int maxSteps;

    Jacobi(double[][] A, double[][] B, int firstRow, int lastRow, int firstCol,
            int lastCol, int maxSteps, int leafCells) {
        this.maxSteps = maxSteps;
        root = build(A, B, firstRow, lastRow, firstCol, lastCol, leafCells);
    }

    @Override
    public void run() {
        for (int i = 0; i < maxSteps; ++i) {
            invoke(root);
            if (root.maxDiff < EPSILON) {
                System.out.println("Converged");
                return;
            } else
                root.reset();
        }
    }

    static JTree build(double[][] a, double[][] b, int lr, int hr, int lc,
            int hc, int size) {
        if ((hr - lr + 1) * (hc - lc + 1) <= size)
            return new Leaf(a, b, lr, hr, lc, hc);
        int mr = (lr + hr) / 2; // midpoints
        int mc = (lc + hc) / 2;
        return new Interior(build(a, b, lr, mr, lc, mc, size), build(a, b, lr,
                mr, mc + 1, hc, size), build(a, b, mr + 1, hr, lc, mc, size),
                build(a, b, mr + 1, hr, mc + 1, hc, size));
    }
}

abstract class JTree extends NullFJTask {
    volatile double maxDiff; // for convergence check
}

class Interior extends JTree {
    private final JTree[] quads;

    Interior(JTree q1, JTree q2, JTree q3, JTree q4) {
        quads = new JTree[] { q1, q2, q3, q4 };
    }

    @Override
    public void run() {
        coInvoke(quads);
        double md = 0.0;
        for (int i = 0; i < quads.length; ++i) {
            md = Math.max(md, quads[i].maxDiff);
            quads[i].reset();
        }
        maxDiff = md;
    }
}

class Leaf extends JTree {
    private final double[][] A;
    private final double[][] B;
    private final int loRow;
    private final int hiRow;
    private final int loCol;
    private final int hiCol;
    private int steps = 0;

    Leaf(double[][] A, double[][] B, int loRow, int hiRow, int loCol, int hiCol) {
        this.A = A;
        this.B = B;
        this.loRow = loRow;
        this.hiRow = hiRow;
        this.loCol = loCol;
        this.hiCol = hiCol;
    }

    @Override
    public synchronized void run() {
        boolean AtoB = (steps++ % 2) == 0;
        double[][] a = (AtoB) ? A : B;
        double[][] b = (AtoB) ? B : A;
        double md = 0.0;
        for (int i = loRow; i <= hiRow; ++i) {
            for (int j = loCol; j <= hiCol; ++j) {
                b[i][j] = 0.25 * (a[i - 1][j] + a[i][j - 1] + a[i + 1][j] + a[i][j + 1]);
                md = Math.max(md, Math.abs(b[i][j] - a[i][j]));
            }
        }
        maxDiff = md;
    }
}