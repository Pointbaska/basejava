package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStreamAPI {
    public static void main(String[] args) {
        int[] array1 = new int[]{1, 2, 3, 3, 2, 3};
        int[] array2 = new int[]{9, 8};
        System.out.println(minValue(array1));
        System.out.println(minValue(array2));

        List<Integer> list = new ArrayList<>(Arrays.asList(10, 1, 1, 2, 1));
        System.out.println(oddOrEven(list));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (x, y) -> x * 10 + y);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream()
                .mapToInt(i -> i)
                .sum();

        int remainder = sum % 2;
        return integers.stream()
                .filter(e -> (remainder + e) % 2 != 0)
                .collect(Collectors.toList());
    }
}
