package by.itacademy.writer;

import by.itacademy.transport.Transport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportWriterToJsonImplTest {
    private File invalidFile;
    private File successFile;
    private TransportWriter writer;
    private final List<Transport> transportList = new ArrayList<>();


    @BeforeEach
    void prepare() {
        transportList.add(new Transport("auto", "BMW", 20));
        transportList.add(new Transport("bus", "Icarus", 30));
        invalidFile = Path.of("src", "test", "resources","invalid-transport.json").toFile();
        successFile = Path.of("src", "test", "resources","processed-transport.json").toFile();
        writer = new TransportWriterToJsonImpl(invalidFile, successFile);
    }

    @Test
    void test_writeInvalidTransport_success() throws WriterException {
        writer.writeInvalidTransport(transportList);
        assertNotNull(invalidFile, "file invalid-transport.json is not exist");
    }

    @Test
    void test_writeSuccessTransport_success() throws WriterException {
        writer.writeSuccessTransport(transportList);
        assertNotNull(successFile, "processed-transport.json is not exist");
    }

    @Test
    void test_writeInvalidTransport_wrongFileExtension_throwsWriterException() throws IOException {
        invalidFile.createNewFile();
        invalidFile.setReadOnly();
        WriterException writerException = assertThrows(WriterException.class, () -> writer.writeInvalidTransport(transportList));
        assertNotNull(writerException, "writerException is null");
        assertEquals("Cant write file invalid-transport.json", writerException.getMessage());
    }

    @Test
    void test_writeSuccessTransport_wrongFileExtension_throwsWriterException() throws IOException {
        successFile.createNewFile();
        successFile.setReadOnly();
        WriterException writerException = assertThrows(WriterException.class, () -> writer.writeSuccessTransport(transportList));
        assertNotNull(writerException, "writerException is null");
        assertEquals("Cant write file processed-transport.json", writerException.getMessage());
    }
    @AfterEach
    void finishTest(){
        invalidFile.delete();
        successFile.delete();
    }
}