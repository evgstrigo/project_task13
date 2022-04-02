package app.util;

import app.entities.Passenger;
import app.entities.Passport;

import java.util.ArrayList;
import java.util.List;

/**
 * Class helps to add some passenger to DB for testing
 */
public class PassengerAndPassportCreator {


    /**
     * @return List of 3 passengers for testing
     */
    public static List<Passenger> createThreePassengersAndPassports() {
        Passport passport1 = new Passport();
        passport1.setFirstName("Vadim");
        passport1.setLastName("Polev");
        passport1.setMiddleName("Vladimirovich");
        passport1.setGender("M");
        passport1.setBirthplace("Uzhgorod");
        passport1.setResidenceRegistration("Moscow, Kremlin");
        passport1.setDateOfBirth(LocalDateUtil.createLocalDateFromString("05-12-1962"));
        passport1.setSeriesAndNumber(123456);

        Passport passport2 = new Passport();
        passport2.setFirstName("Andrey");
        passport2.setLastName("Hrebtovskiy");
        passport2.setMiddleName("Eduardovich");
        passport2.setGender("M");
        passport2.setBirthplace("Moscow");
        passport2.setResidenceRegistration("Moscow, Kremlin");
        passport2.setDateOfBirth(LocalDateUtil.createLocalDateFromString("15-11-1969"));
        passport2.setSeriesAndNumber(234567);

        Passport passport3 = new Passport();
        passport3.setFirstName("Jacov");
        passport3.setLastName("Pampura");
        passport3.setMiddleName("Aleksandrovich");
        passport3.setGender("F");
        passport3.setBirthplace("Krasnodar");
        passport3.setResidenceRegistration("Moscow, Kremlin");
        passport3.setDateOfBirth(LocalDateUtil.createLocalDateFromString("21-04-1967"));
        passport3.setSeriesAndNumber(345678);

        Passenger passenger1 = new Passenger();
        passenger1.setEmail("vp@italteplo.su");
        passenger1.setFirstName("Vadim");
        passenger1.setLastName("Polev");
        passenger1.setMiddleName("Vladimirovich");
        passenger1.setDateOfBirth(LocalDateUtil.createLocalDateFromString("05-12-1962"));
        passenger1.setPassport(passport1);

        Passenger passenger2 = new Passenger();
        passenger2.setEmail("ae@italteplo.su");
        passenger2.setFirstName("Andrey");
        passenger2.setLastName("Hrebtovskiy");
        passenger2.setMiddleName("Eduardovich");
        passenger2.setDateOfBirth(LocalDateUtil.createLocalDateFromString("15-11-1969"));
        passenger2.setPassport(passport2);

        Passenger passenger3 = new Passenger();
        passenger3.setEmail("jp@italteplo.su");
        passenger3.setFirstName("Jacov");
        passenger3.setLastName("Pampura");
        passenger3.setMiddleName("Aleksandrovich");
        passenger3.setDateOfBirth(LocalDateUtil.createLocalDateFromString("21-04-1967"));
        passenger3.setPassport(passport3);

        List<Passenger> passengerList = new ArrayList();
        passengerList.add(passenger1);
        passengerList.add(passenger2);
        passengerList.add(passenger3);

        return passengerList;
    }

}
