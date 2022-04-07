package app.services;

import app.entities.Passenger;

import java.util.List;

/**
 * Interface for Passenger Service<p>
 * Has 4 CRUD methods
 */
public interface PassengerService {


    /**
     * Returns List of all passengers from DB.<p>
     * Has no args.
     */
    List<Passenger> findAll();


    /**
     * Returns passenger from DB by passenger's id.<p>
     * Passenger's id is specified in method argument.<p>
     * throws NoSuchElementException if passenger with specified id is not found
     */
    Passenger findById(Long id);


    /**
     * Saves or updates passenger to DB.<p>
     * Passenger entity is specified in method argument.
     */
    Passenger save(Passenger passenger);


    /**
     * Deletes passenger from DB by passenger's id.<p>
     * Passenger's id is specified in method argument.
     */
    void delete(Long id);

}
