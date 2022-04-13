package app.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class helps to convert string to LocalDate object
 * Takes string in format "25-09-2021"
 * Returns LocalDate object
 */

public class LocalDateUtil {

    /**
     * Converts string to LocalDate object.
     * @param localDateString String in format dd-MM-yyyy (i.e. "31-05-2020")
     * @return LocalDate object
     */
    public static LocalDate createLocalDateFromString(String localDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(localDateString, formatter);
        return localDate;
    }
}
