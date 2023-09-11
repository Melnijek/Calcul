import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    private final static Map<String, Integer> romanMap = new HashMap<>();

    static {
        romanMap.put("I", 1);
        romanMap.put("IV", 4);
        romanMap.put("V", 5);
        romanMap.put("IX", 9);
        romanMap.put("X", 10);
        romanMap.put("XL", 40);
        romanMap.put("L", 50);
        romanMap.put("XC", 90);
        romanMap.put("C", 100);

        for (int i = 1; i <= 100; i++) {
            String roman = convertToRoman(i);
            romanMap.put(roman, i);
        }
    }

    public static void main(String[] args) {
        System.out.println("ВВедите значения в формате A + B");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String result = calc(input);
        System.out.println(result);
    }

    public static String calc(String input) {

        String[] parts = input.trim().split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат ввода");
        }
        String romanRegex = "^(I|II|III|IV|V|VI|VII|VIII|IX|X)$";
        String arabicRegex = "^[1-9]|10$";
        String operationsRegex = "[+-/*]";

        String a = parts[0];
        String b = parts[2];

        String operator = parts[1];
        if (!operator.matches(operationsRegex)) {
            throw new IllegalArgumentException("Недопустимая операция");
        }

        if (a.matches(romanRegex) && b.matches(romanRegex)) {
            int result = calculate(romanMap.get(a), romanMap.get(b), operator); //
            return convertToRoman(result);
        } else if (a.matches(arabicRegex) && b.matches(arabicRegex)) {
            int result = calculate(Integer.parseInt(a), Integer.parseInt(b), operator);
            return Integer.toString(result);
        } else {
            throw new IllegalArgumentException("Недопустимые числа");
        }
    }

    private static int calculate(int a, int b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Недопустимая операция");
        };
    }

    private static String convertToRoman(int number) {
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("Число должно быть от 1 до 100");
        }

        StringBuilder result = new StringBuilder();

        int[] arabicNumbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int i = 0;

        while (number > 0) {
            if (number >= arabicNumbers[i]) {
                result.append(romanNumerals[i]);
                number -= arabicNumbers[i];
            } else {
                i++;
            }
        }

        return result.toString();
    }
}


