package by.itacademy.servlet;

import by.itacademy.Transport;
import by.itacademy.checker.TransportCheckerException;
import by.itacademy.reader.ReaderException;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public interface ProcessRequest {
    Map<String, List<Transport>> convertStreamToMapTransports(
            InputStreamReader inputStreamReader, String nameSorting
    ) throws ReaderException, TransportCheckerException;
}
