package by.itacademy.writer;

import by.itacademy.transport.Transport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportWriterToJsonImplTest {
    private final File invalidFile = Path.of("src", "test", "resources", "invalid-transport.json").toFile();
    private final File invalidFileActual = Path.of("src", "test", "resources", "invalid-transport-actual.json").toFile();
    private final File successFile = Path.of("src", "test", "resources", "processed-transport.json").toFile();
    private final File successFileActual = Path.of("src", "test", "resources", "processed-transport-actual.json").toFile();
    private final TransportWriter writer = new TransportWriterToJsonImpl();
    private final List<Transport> transportList = new ArrayList<>();


    @BeforeEach
    void prepare() {
        transportList.add(new Transport("auto", "BMW", 20));
        transportList.add(new Transport("bus", "Icarus", 30));
    }

    @Test
    void test_writeTransport_success() throws WriterException, IOException {
        writer.writeTransport(transportList, invalidFile, "invalid");

        assertEquals(Files.readAllLines(invalidFile.toPath()), Files.readAllLines(invalidFileActual.toPath()));
    }

    @Test
    void test_writeSuccessTransport_success() throws WriterException, IOException {
        writer.writeTransport(transportList, successFile, "success");

        assertEquals(Files.readAllLines(successFile.toPath()), Files.readAllLines(successFileActual.toPath()));
    }

    @Test
    void test_writeInvalidTransport_wrongFileExtension_throwsWriterException() throws IOException {
        final boolean newFileInvalid = invalidFile.createNewFile();
        final boolean readOnly = invalidFile.setReadOnly();

        final WriterException writerException = assertThrows(WriterException.class, () -> writer.writeTransport(transportList,
                invalidFile, "invalid"));
        assertNotNull(writerException, "writerException is null");
        assertEquals("Cant write file invalid-transport.json", writerException.getMessage());
    }

    @Test
    void test_writeSuccessTransport_wrongFileExtension_throwsWriterException() throws IOException {
        final boolean newFileSuccess = successFile.createNewFile();
        final boolean readOnly = successFile.setReadOnly();

        final WriterException writerException = assertThrows(WriterException.class, () -> writer.writeTransport(transportList,
                successFile, "success"));
        assertNotNull(writerException, "writerException is null");
        assertEquals("Cant write file processed-transport.json", writerException.getMessage());
    }

    @AfterEach
    void finishTest() {
        transportList.clear();
        final boolean deleteFileInvalid = invalidFile.delete();
        final boolean deleteFileSuccess = successFile.delete();
    }
}