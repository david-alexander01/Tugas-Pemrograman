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
    public static int getEntryYear(long npm){
        // Returns year of entry in the 21st century
        return 2000 + Integer.parseInt(Long.toString(npm).substring(0, 2));
    }

    public static String getJurusan(long npm){
        // Returns string of jurusan else "invalid" if code is invalid.
        String jurusanCode = (npm + "").substring(2, 4);
        return switch (jurusanCode) {
            case "01" -> "Ilmu Komputer";
            case "02" -> "Sistem Informasi";
            case "03" -> "Teknologi Informasi";
            case "11" -> "Teknik Telekomunikasi";
            case "12" -> "Teknik Elektro";
            default -> "invalid";
        };
    }

    public static String getBirthDate(long npm){
        // Returns birthday in the format of "dd-mm-yyyy"
        String birthDate = (npm + "").substring(4, 12);
        String date = birthDate.substring(0, 2);
        String month = birthDate.substring(2, 4);
        String year = birthDate.substring(4);
        return String.format("%2s-%2s-%4s", date, month, year);
    }

    public static boolean validate(long npm) {
        String npmString = npm + "";
        int entryYear = getEntryYear(npm);
        int validationCode = Integer.parseInt(String.valueOf(npmString.charAt(13)));

        // Check length
        if (npmString.length() != 14)
            return false;

        // Check jurusan code
        if (getJurusan(npm).equals("invalid"))
            return false;

        // Check age
        int birthYear = Integer.parseInt(getBirthDate(npm).substring(6));
        if (entryYear - birthYear < 15)
            return false;

        // Check NPM code
        int sum = 0;
        // Get sum of products
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