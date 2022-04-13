package app.util;

import app.entities.Passenger;
import app.entities.Passport;
import app.services.PassengerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Class helps to add some passenger to DB in init-method
 */
public class PassengerAndPassportCreator {


    public static void createFivePassengerAndSaveInDB(PassengerService passengerService) {

        List<Passenger> passengerList = new ArrayList<>();
        Passport passport1 = new Passport();
        passport1.setFirstName("Ivan");
        passport1.setLastName("Ivanov");
        passport1.setMiddleName("Ivanovich");
        passport1.setGender("M");
        passport1.setBirthplace("Voronezh");
        passport1.setResidenceRegistration("Moscow, Kremlin");
        passport1.setDateOfBirth("01-01-2001");
        passport1.setSeriesAndNumber("12 34 123456");

        Passport passport2 = new Passport();
        passport2.setFirstName("Stepan");
        passport2.setLastName("Stepanov");
        passport2.setMiddleName("Stepanovich");
        passport2.setGender("M");
        passport2.setBirthplace("Kazan");
        passport2.setResidenceRegistration("Moscow, CAO");
        passport2.setDateOfBirth("02-02-2002");
        passport2.setSeriesAndNumber("12 34 123457");

        Passport passport3 = new Passport();
        passport3.setFirstName("Irina");
        passport3.setLastName("Irinova");
        passport3.setMiddleName("Olegovna");
        passport3.setGender("F");
        passport3.setBirthplace("Novosibirsk");
        passport3.setResidenceRegistration("Moscow, ZAO");
        passport3.setDateOfBirth("03-03-2003");
        passport3.setSeriesAndNumber("12 34 123458");

        Passport passport4 = new Passport();
        passport4.setFirstName("Olga");
        passport4.setLastName("Kozlova");
        passport4.setMiddleName("Romanovna");
        passport4.setGender("F");
        passport4.setBirthplace("Yakutsk");
        passport4.setResidenceRegistration("Moscow, VAO");
        passport4.setDateOfBirth("04-04-2004");
        passport4.setSeriesAndNumber("12 34 123459");

        Passport passport5 = new Passport();
        passport5.setFirstName("Igor");
        passport5.setLastName("Igorev");
        passport5.setMiddleName("Stepanovich");
        passport5.setGender("M");
        passport5.setBirthplace("Yaroslavl");
        passport5.setResidenceRegistration("Moscow, UAO");
        passport5.setDateOfBirth("05-05-2005");
        passport5.setSeriesAndNumber("12 34 123460");


        Passenger passenger1 = new Passenger();
        passenger1.setEmail("Ivanov@mail.ru");
        passenger1.setPassport(passport1);

        Passenger passenger2 = new Passenger();
        passenger2.setEmail("Stepanov@mail.ru");
        passenger2.setPassport(passport2);

        Passenger passenger3 = new Passenger();
        passenger3.setEmail("Irinova@mail.ru");
        passenger3.setPassport(passport3);

        Passenger passenger4 = new Passenger();
        passenger4.setEmail("Kozlova@mail.ru");
        passenger4.setPassport(passport4);

        Passenger passenger5 = new Passenger();
        passenger5.setEmail("Igorev@mail.ru");
        passenger5.setPassport(passport5);


        passengerList.add(passenger1);
        passengerList.add(passenger2);
        passengerList.add(passenger3);
        passengerList.add(passenger4);
        passengerList.add(passenger5);


        for (Passenger passenger : passengerList) {
            passengerService.save(passenger);
        }

    }


}
