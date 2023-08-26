package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.Comparator;
import java.util.List;

public interface TransportSorter {
    void sort(List<Transport> list, Comparator<Transport> comparator);

    void sortReader(List<Transport> listTransport);
}
