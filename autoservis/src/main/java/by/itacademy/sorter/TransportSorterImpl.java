package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class TransportSorterImpl implements TransportSorter {

    @Override
    public <T extends Comparable<T>> void sort(final List<Transport> listTransport, final Function<Transport, T> keyExtractor) {
         listTransport.sort(Comparator.comparing(keyExtractor));
    }

    @Override
    public void sortReader(final List<Transport> listTransport) {
        final List<Integer> listChoiseUser = new ArrayList<>();

        while (true) {
            System.out.println("""
                    �������� ����� ���������� �������� ��:
                    1 - �� ���� ��
                    2 - �� ������
                    3 - �� ����
                    4 - ����� �� ������ ���������� � ������� ������������ ������ � �����
                    """);

            final int choiceUser = checkChoiceUser(1, 4, listChoiseUser);

            switch (choiceUser) {
                case 1 -> sort(listTransport, Transport::getType);
                case 2 -> sort(listTransport, Transport::getModel);
                case 3 -> sort(listTransport, Transport::getPrice);
                case 4 -> {return;}
                default -> System.out.println("�� ����� �� ������� ������. ��������� ����");
            }
        }
    }

    private static int checkChoiceUser(final int start, final int end, final List<Integer> listChoiseUser) {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            final String stringNumber = scanner.nextLine();

            try {
                final int number = Integer.parseInt(stringNumber);

                if (number < start || number > end) {
                    System.out.println("�� ����� �� ������� ������");
                } else if (listChoiseUser.contains(number)) {
                    System.out.println("����� ����� ���������� ��� �������� �����");
                } else {
                    System.out.println("����� ���������� ������");
                    listChoiseUser.add(number);
                    return number;
                }
            } catch (final NumberFormatException e) {
                System.err.println("�� ���� ������������ ��������! ��������� ����");
            }
        }
    }
}
