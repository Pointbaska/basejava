package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class MainStreamAPI {
    public static void main(String[] args) {
        int[] array1 = new int[]{1, 2, 3, 3, 2, 3};
        int[] array2 = new int[]{9, 8};
        System.out.println(minValue(array1));
        System.out.println(minValue(array2));

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(1);
        System.out.println(oddOrEven(list));
    }

    private static int minValue(int[] values) {
        OptionalInt optional = Arrays.stream(values)
                .filter(s -> 0 < s && s < 10)
                .distinct()
                .sorted()
                .reduce((x, y) -> x * 10 + y);

        return optional.getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream()
                .mapToInt(i -> i)
                .sum();

        boolean isEven = (sum % 2 == 0);
        return integers.stream()
                .filter(e -> (e % 2 == 0) != isEven)
                .collect(Collectors.toList());
    }
}
