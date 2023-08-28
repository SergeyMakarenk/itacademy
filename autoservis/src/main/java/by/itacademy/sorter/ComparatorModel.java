package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.Comparator;

public class ComparatorModel implements Comparator<Transport> {
    @Override
    public int compare(final Transport o1, final Transport o2) {
        return o1.getModel().compareTo(o2.getModel());
    }

}
