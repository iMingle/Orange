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

package org.mingle.orange.arithmetic.unionfind;

/**
 * The {@code QuickFindUF} class represents a <em>union–find data type</em>
 * (also known as the <em>disjoint-sets data type</em>).
 * It supports the <em>union</em> and <em>find</em> operations,
 * along with a <em>connected</em> operation for determining whether
 * two sites are in the same component and a <em>count</em> operation that
 * returns the total number of components.
 * <p>
 * The union–find data type models connectivity among a set of <em>n</em>
 * sites, named 0 through <em>n</em>&minus;1.
 * The <em>is-connected-to</em> relation must be an
 * <em>equivalence relation</em>:
 * <ul>
 * <li> <em>Reflexive</em>: <em>p</em> is connected to <em>p</em>.
 * <li> <em>Symmetric</em>: If <em>p</em> is connected to <em>q</em>,
 * then <em>q</em> is connected to <em>p</em>.
 * <li> <em>Transitive</em>: If <em>p</em> is connected to <em>q</em>
 * and <em>q</em> is connected to <em>r</em>, then
 * <em>p</em> is connected to <em>r</em>.
 * </ul>
 * <p>
 * An equivalence relation partitions the sites into
 * <em>equivalence classes</em> (or <em>components</em>). In this case,
 * two sites are in the same component if and only if they are connected.
 * Both sites and components are identified with integers between 0 and
 * <em>n</em>&minus;1.
 * Initially, there are <em>n</em> components, with each site in its
 * own component.  The <em>component identifier</em> of a component
 * (also known as the <em>root</em>, <em>canonical element</em>, <em>leader</em>,
 * or <em>set representative</em>) is one of the sites in the component:
 * two sites have the same component identifier if and only if they are
 * in the same component.
 * <ul>
 * <li><em>union</em>(<em>p</em>, <em>q</em>) adds a
 * connection between the two sites <em>p</em> and <em>q</em>.
 * If <em>p</em> and <em>q</em> are in different components,
 * then it replaces
 * these two components with a new component that is the union of
 * the two.
 * <li><em>find</em>(<em>p</em>) returns the component
 * identifier of the component containing <em>p</em>.
 * <li><em>connected</em>(<em>p</em>, <em>q</em>)
 * returns true if both <em>p</em> and <em>q</em>
 * are in the same component, and false otherwise.
 * <li><em>count</em>() returns the number of components.
 * </ul>
 * <p>
 * The component identifier of a component can change
 * only when the component itself changes during a call to
 * <em>union</em>—it cannot change during a call
 * to <em>find</em>, <em>connected</em>, or <em>count</em>.
 * <p>
 * This implementation uses quick find.
 * Initializing a data structure with <em>n</em> sites takes linear time.
 * Afterwards, the <em>find</em>, <em>connected</em>, and <em>count</em>
 * operations take constant time but the <em>union</em> operation
 * takes linear time.
 * For alternate implementations of the same API, see
 * {@link UF}, {@link QuickUnionUF}, and {@link WeightedQuickUnionUF}.
 *
 * @author mingle
 */
public class QuickFindUF {
    private int[] id;    // id[i] = component identifier of i
    private int count;   // number of components

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IndexOutOfBoundsException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        return id[p];
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * {@code false} otherwise
     * @throws IndexOutOfBoundsException unless
     *                                   both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IndexOutOfBoundsException unless
     *                                   both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pID = id[p];   // needed for correctness
        int qID = id[q];   // to reduce the number of array accesses

        // p and q are already in the same component
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }
}
