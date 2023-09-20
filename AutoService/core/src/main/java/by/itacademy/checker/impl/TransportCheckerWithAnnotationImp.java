package by.itacademy.checker.impl;

import by.itacademy.Transport;
import by.itacademy.annotations.Validation;
import by.itacademy.checker.TransportChecker;
import by.itacademy.checker.TransportCheckerException;
import by.itacademy.transport.TransportContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class TransportCheckerWithAnnotationImp implements TransportChecker {

    @Override
    public TransportContainer checkTransport(
            final List<Transport> listTransport) throws TransportCheckerException {

        final List<Transport> invalidTransportList = new ArrayList<>();
        final List<Transport> successTransportList = new ArrayList<>();

        for (final Transport transport : listTransport) {
            boolean isValidTransport = false;

            for (final Field field : transport.getClass().getDeclaredFields()) {
                isValidTransport = workWithAnnotation(field, transport);
                if (isValidTransport) {
                    successTransportList.add(transport);
                    break;
                }
            }
            if (!(isValidTransport)) {
                invalidTransportList.add(transport);
            }
        }
        return new TransportContainer(successTransportList, invalidTransportList);
    }

    private boolean workWithAnnotation(final Field field, final Transport transport) throws TransportCheckerException {
        for (final Annotation annotation : field.getDeclaredAnnotations()) {

            if (!(annotation instanceof Validation)) {
                continue;
            }

            if (!field.canAccess(transport) && !field.trySetAccessible()) {
                continue;
            }

            final Validation validation = (Validation) annotation;

            try {
                final String modelValueTransport = (String) field.get(transport);
                final Predicate<String> predicate = Pattern.compile(validation.pattern()).asMatchPredicate();

                return predicate.test(modelValueTransport);

            } catch (final IllegalAccessException e) {
                throw new TransportCheckerException("Can't read field", e);
            }
        }
        return false;
    }
}
