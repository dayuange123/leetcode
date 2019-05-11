

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<int[]> list = new ArrayList<>();
        List<int[]> sortList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int count = in.nextInt();
            list.add(new int[count]);
            sortList.add(new int[count]);
            for (int j = 0; j < count; j++) {
                list.get(i)[j] = in.nextInt();
                sortList.get(i)[j] = list.get(i)[j];
            }
            Arrays.sort(sortList.get(i));
        }
        for (int i = 0; i < n; i++) {
            int[] current = list.get(i);
            int[] counts = new int[current.length];
            int min = sortList.get(i)[0], index = 0;
            while (index < current.length) {
                while (index < current.length && min == sortList.get(i)[index]) {
                    min = sortList.get(i)[index++];
                }
                for (int j = 0; j < current.length; j++) {
                    if (current[j] == min) {
                        int right = (j + 1) % current.length;
                        int left = (current.length + j - 1) % current.length;
                        if (current[j] > current[left] && current[right] < current[j]) {
                            counts[j] = Math.max(counts[left], counts[right]) + 1;
                            continue;
                        }
                        if (current[left] > current[right]) {
                            if (current[right] < current[j])
                                counts[j] = counts[right] + 1;
                            else
                                counts[j] = 1;
                        } else {
                            if (current[left] < current[j])
                                counts[j] = counts[left] + 1;
                            else
                                counts[j] = 1;
                        }
                    }
                }
                if (index == current.length) break;
                min = sortList.get(i)[index];
            }
            int sum = 0;
            for (int j = 0; j < current.length; j++) {
                sum += counts[j];
            }
            System.out.println(sum);
        }
    }
}