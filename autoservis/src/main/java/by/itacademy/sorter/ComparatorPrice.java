package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.Comparator;

public class ComparatorPrice implements Comparator<Transport> {

    @Override
    public int compare(Transport o1, Transport o2) {
        return o1.getPrice() - o2.getPrice();
    }
}
