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

package org.orange.java.collection;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author mingle
 */
public class TreeSetTest {

    public static void main(String[] args) {
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Modem", 9912));

        System.out.println(parts);

        SortedSet<Item> sortByDescription = new TreeSet<>((o1, o2) -> {
            String a = o1.getDescription();
            String b = o2.getDescription();
            return a.compareTo(b);
        });

        sortByDescription.addAll(parts);

        System.out.println(sortByDescription);
    }

}

/**
 * An item with a description and a part number
 */
class Item implements Comparable<Item> {
    private String description;
    private int partNumber;

    /**
     * @param description
     * @param partNumber
     */
    public Item(String description, int partNumber) {
        super();
        this.description = description;
        this.partNumber = partNumber;
    }

    @Override
    public String toString() {
        return "Item [description=" + description + ", partNumber="
                + partNumber + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + partNumber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (partNumber != other.partNumber)
            return false;
        return true;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Item o) {
        return this.partNumber - o.partNumber;
    }
}
