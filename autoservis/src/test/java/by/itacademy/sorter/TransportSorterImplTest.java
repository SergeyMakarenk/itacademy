package by.itacademy.sorter;

import by.itacademy.transport.Transport;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportSorterImplTest {

    @Test
    void test_sortArrayList_withComparator_success(){
        final TransportSorter sorter = new TransportSorterImpl();
        final List<Transport> transportList = new ArrayList<>();
        final Transport bikeAudiTwenty = new Transport("bike", "Audi", 20);
        final Transport autoHondaTen = new Transport("auto", "Honda", 10);
        transportList.add(bikeAudiTwenty);
        transportList.add(autoHondaTen);
        sorter.sort(transportList, new ComparatorType());
        assertEquals(transportList.get(0), autoHondaTen);
        sorter.sort(transportList, new ComparatorModel());
        assertEquals(transportList.get(0), bikeAudiTwenty);
        sorter.sort(transportList, new ComparatorPrice());
        assertEquals(transportList.get(0), autoHondaTen);
    }
}