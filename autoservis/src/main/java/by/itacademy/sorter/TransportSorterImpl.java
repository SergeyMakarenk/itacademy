package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TransportSorterImpl implements TransportSorter {
    @Override
    public void sort(final List<Transport> list, final Comparator<Transport> comparator) {
        list.sort(comparator);
    }

    @Override
    public void sortReader(final List<Transport> listTransport) {
        final List<Integer> listChoiseUser = new ArrayList<>();

        while (true) {
            System.out.println("""
                    Выберете метод сортировки валидных ТС:
                    1 - по типу ТС
                    2 - по модели
                    3 - по цене
                    4 - выйти из режима сортировки с записью обработанных данных в файлы
                    """);

            final int choiceUser = checkChoiceUser(1, 4, listChoiseUser);

            switch (choiceUser) {
                case 1 -> sort(listTransport, new ComparatorType());
                case 2 -> sort(listTransport, new ComparatorModel());
                case 3 -> sort(listTransport, new ComparatorPrice());
                case 4 -> {
                    return;
                }
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
                    System.out.println("Вы вышли за границы выбора");
                } else if (listChoiseUser.contains(number)) {
                    System.out.println("Такой метод сортировки уже применен ранее");
                } else {
                    System.out.println("Метод сортировки принят");
                    listChoiseUser.add(number);
                    return number;
                }
            } catch (final NumberFormatException e) {
                System.err.println("Вы вели некорректное значение! повторите ввод");
            }
        }
    }
}
