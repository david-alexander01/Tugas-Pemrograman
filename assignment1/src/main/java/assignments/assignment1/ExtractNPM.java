// This program receives an input of NPM and outputs the data processed
// from the given NPM else outputs "NPM tidak valid!" if the NPM given is
// not valid

package assignments.assignment1;

import java.util.Scanner;

public class ExtractNPM {

    public static int getEntryYear(long npm) {
        // Returns year of entry in the 21st century
        return 2000 + Integer.parseInt(Long.toString(npm).substring(0, 2));
    }

    public static String getJurusan(long npm) {
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

    public static String getBirthDate(long npm) {
        // Returns birthday in the format of "dd-mm-yyyy"
        String birthDate = (npm + "").substring(4, 12);
        String date = birthDate.substring(0, 2);
        String month = birthDate.substring(2, 4);
        String year = birthDate.substring(4);
        return String.format("%2s-%2s-%4s", date, month, year);
    }

    public static boolean validate(long npm) {
        // Returns true if NPM is valid else false
        String npmString = npm + "";
        // Check length
        if (npmString.length() != 14)
            return false;

        int entryYear = getEntryYear(npm);
        int validationCode = Integer.parseInt(String.valueOf(npmString.charAt(13)));


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
        sum += Character.getNumericValue(npmString.charAt(6));
        // Reduce sum of products to one digit
        while (sum >= 10) {
            int tempSum = sum;
            int digitSum = 0;
            int lastDigit;
            while (tempSum > 0) {
                lastDigit = tempSum % 10;
                digitSum += lastDigit;
                tempSum /= 10;
            }
            sum = digitSum;
        }
        return validationCode == sum;
    }

    public static String extract(long npm) {
        // Returns formatted string of extracted information
        return String.format("""
                        Tahun masuk: %d
                        Jurusan: %s
                        Tanggal Lahir: %s""",
                getEntryYear(npm), getJurusan(npm), getBirthDate(npm));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            long npm = input.nextLong();

            // Breaks loop if entered NPM is negative
            if (npm < 0)
                break;

            if (validate(npm))
                System.out.println(extract(npm));
            else
                System.out.println("NPM tidak valid!");
        }
        input.close();
    }
}