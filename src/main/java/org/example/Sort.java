package org.example;

class Sort implements Comparable<Sort> {
    int index, value;
    Sort(int index, int value){
        this.index = index;
        this.value = value;
    }

    public int compareTo(Sort e) {
        return this.value - e.value;
    }
}
