import java.util.Arrays;

/**
 * A function that adds two strings, where each string represents an
 * arbitrarily large non-negative base 10 number
 * 
 */
class AddArbitLongStrings {

    /**
     * Algorithm to add 2 very large positive integers where both inputs and outputs
     * may not fit wihtin an "int" (32 bit)
     * 
     * @param str1 String representation of number-1
     * @param str2 String representation of number-2
     * @return the result (as String) of adding the 2 positive long numbers
     */
    public String addNumbers(String str1, String str2) {

        // Obtain the length of each Number (in String format)
        int l1 = str1.length();
        int l2 = str2.length();

        // Find the max length so that we can accomodate the result
        int longer = (l1 > l2) ? l1 : l2;

        // Since the String representation of the inputs numbers cannot be captured in
        // primitive data types like int,
        // we have to store them in int[] to perform the basic addition using place
        // value decimal system
        int[] num1 = new int[longer];
        int[] num2 = new int[longer];

        // Storing the number such that least significant digit is to the left of the
        // int array. This will enable simpler arithmatic during summation
        for (int i = 0; i < l1; i++) {
            num1[i] = str1.charAt(l1 - 1 - i) - '0';
        }
        // System.out.println("Num1 integer array: " +
        // Arrays.toString(num1).replaceAll("\\[|\\]|,|\\s", ""));

        // Storing the number such that least significant digit is to the left of the
        // int array. This will enable simpler arithmatic during summation
        for (int i = 0; i < l2; i++) {
            num2[i] = str2.charAt(l2 - 1 - i) - '0';
        }
        // System.out.println("Num2 integer array: " +
        // Arrays.toString(num2).replaceAll("\\[|\\]|,|\\s", ""));

        int carryOver = 0;
        int partialSum = 0;
        int[] sum = new int[longer + 1]; // +1 to accomodate spilling over to the next 10s place

        for (int i = 0; i < longer; i++) {
            partialSum = num1[i] + num2[i] + carryOver;
            sum[i] = partialSum % 10; // for decimal system
            carryOver = (partialSum >= 10) ? 1 : 0; // 10 for decimal system
        }
        sum[longer] = carryOver;

        // reverse resultant String so that least significant digit goes to right end
        return new StringBuilder(Arrays.toString(sum).replaceAll("\\[|\\]|,|\\s", "")).reverse().toString();
    }

    public static void main(String[] args) {
        AddArbitLongStrings c = new AddArbitLongStrings();

        String str1 = "1234";
        String str2 = "15678";
        System.out.println(
                "Test Case#1: Sum of numbers: (" + str1 + ") and (" + str2 + ") = " + c.addNumbers(str1, str2));

        str1 = "123456789012345678901";
        str2 = "12345678";
        System.out.println(
                "Test Case#2: Sum of numbers: (" + str1 + ") and (" + str2 + ") = " + c.addNumbers(str1, str2));

    }
}
