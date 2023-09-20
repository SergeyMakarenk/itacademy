package by.itacademy.web.service;

import by.itacademy.checker.TransportCheckerException;
import by.itacademy.reader.ReaderException;
import by.itacademy.transport.TransportContainer;

import java.io.InputStreamReader;

public interface ProcessBodyRequest {
    TransportContainer convertStreamToMapTransports(
            InputStreamReader inputStreamReader, String nameSorting
    ) throws ReaderException, TransportCheckerException;
}
