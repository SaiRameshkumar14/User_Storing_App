package training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,45,32,76,90,89,77,67,66));
        System.out.println(list); //[1, 45, 32, 76, 90, 89, 77, 67, 66]

        int[] a = {2,4,4,3,2,3,4};
        System.out.println(a); // [I@73c6c3b2

        // Stream is used to check and store the even numbers from the list using .FILTER() method
        List<Integer> evenCheck = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(evenCheck); // [32, 76, 90, 66]

        //Stream is used to add 5 extra to each number in the list using .MAP() method
        List<Integer> addNumberToEach = list.stream().map(i -> i + 5).collect(Collectors.toList());
        System.out.println(addNumberToEach);

        List<Integer> roundOff = list.stream().map(i -> i - (i % 10)).collect(Collectors.toList());
        System.out.println(roundOff);

    }
}
