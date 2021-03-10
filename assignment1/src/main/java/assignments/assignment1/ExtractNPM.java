package assignments.assignment1;

import java.util.Scanner;

public class ExtractNPM {
    /*
    You can add other method do help you solve
    this problem
    
    Some method you probably need like
    - Method to get tahun masuk or else
    - Method to help you do the validation
    - and so on
    */

    public static boolean validate(long npm) {
        // TODO: validate NPM, return it with boolean
        String npmString = npm + "";
        String jurusanCode = npmString.substring(2, 4);
        int entryYear = 2000 + Integer.parseInt(npmString.substring(0, 2));
        String birthDate = npmString.substring(4, 12);
        int validationCode = Integer.parseInt(String.valueOf(npmString.charAt(13)));

        // Check length
        if (npmString.length() != 14)
            return false;

        // Check jurusan code
        switch (jurusanCode) {
            case "01":
            case "02":
            case "03":
            case "11":
            case "12":
                break;
            default:
                return false;
        }

        // Check age
        int birthYear = Integer.parseInt(birthDate.substring(4));
        if (entryYear - birthYear < 15)
            return false;

        // Check NPM code
        int sum = 0;
        //Get sum of products
        for (int i = 0; i < 6; i++) {
            int negativeIndex = npmString.length() - 2 - i;
            sum += Character.getNumericValue(npmString.charAt(i))
                    * Character.getNumericValue(npmString.charAt(negativeIndex));
        }

        // Reduce sum of products to one digit
        while (sum >= 10){
            int tempSum = sum;
            int digitSum = 0;
            int lastDigit;
            while (tempSum > 0){
                lastDigit = tempSum % 10;
                digitSum += lastDigit;
                tempSum /= 10;
            }
            sum = digitSum;
        }

        return validationCode == sum;
    }

    public static String extract(long npm) {
        // TODO: Extract information from NPM, return string with given format
        return "";
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exitFlag = false;
        while (!exitFlag) {
            long npm = input.nextLong();
            if (npm < 0) {
                exitFlag = true;
                break;
            }

            // TODO: Check validate and extract NPM

        }
        input.close();
    }
}