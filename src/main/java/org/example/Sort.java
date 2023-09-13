package org.example;

import java.util.Comparator;

class Sort implements Comparable<Sort> {
    int index, value;

    Sort(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(Sort e) {
        return Comparator.comparingInt(Sort::getValue).reversed()
                .thenComparingInt(Sort::getIndex)
                .compare(this, e);
    }
}
