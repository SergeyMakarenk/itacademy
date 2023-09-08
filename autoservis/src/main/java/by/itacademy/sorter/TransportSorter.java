package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public interface TransportSorter {
    <T extends Comparable<T>> void sort(final List<Transport> listTransport, final Function<Transport, T> keyExtractor);

    void sortReader(List<Transport> listTransport);
}
