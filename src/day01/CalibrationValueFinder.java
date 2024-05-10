package day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CalibrationValueFinder {
    private final String[] lines;
    private static final char[] nums;

    static {
        nums = new char[]{'0','1','2','3','4','5','6','7','8','9'};
    }

    public CalibrationValueFinder() {
        lines = new String[]{"1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"};
    }
    public CalibrationValueFinder(File input) {
        String[] lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            lines = reader.lines().toArray(String[]::new);
        } catch (IOException exception) {
            lines = new String[0];
            System.out.println(exception.getMessage());
        }
        this.lines = lines;
    }

    public long findValuesSummed() {
        long sum = 0L;
        for (String line : lines) {
            int len = line.length();
            char first = '-', last = '-';
            boolean firstFound = false, lastFound = false;
            for (int i = 0; i < len; i++) {
                if (firstFound && lastFound) {
                    break;
                }

                char lookUp = line.charAt(i);
                char lookDown = line.charAt(len - 1 - i);

                if (!firstFound && isNum(lookUp)) {
                    first = lookUp;
                    firstFound = true;
                }
                if (!lastFound && isNum(lookDown)) {
                    last = lookDown;
                    lastFound = true;
                }
            }
            String val = new StringBuilder().append(first).append(last).toString().replace("-", "");
            sum += Integer.parseInt(val);
        }
        return sum;
    }

    private boolean isNum(char value) {
        for (char num : nums) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new CalibrationValueFinder(new File("src/day01/input.txt")).findValuesSummed());
    }
}
