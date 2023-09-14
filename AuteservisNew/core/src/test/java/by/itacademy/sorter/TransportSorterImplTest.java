package by.itacademy.sorter;

import by.itacademy.Transport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportSorterImplTest {

    @Test
    void test_sortArrayList_withComparator_success() {
        final TransportSorter sorter = new TransportSorterImpl();

        final List<Transport> transportList = new ArrayList<>();
        final Transport bikeAudiTwenty = new Transport("bike", "Audi", 20);
        final Transport autoHondaTen = new Transport("auto", "Honda", 10);

        transportList.add(bikeAudiTwenty);
        transportList.add(autoHondaTen);

        sorter.sort(transportList, Transport::getType);

        assertEquals(transportList.get(0), autoHondaTen);
        assertEquals(transportList.get(1), bikeAudiTwenty);
    }
}