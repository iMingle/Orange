/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 偶数产生器
 *
 * @since 1.0
 * @author Mingle
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    
    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.concurrency.IntGenerator#next()
     */
    @Override
    public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }

}
