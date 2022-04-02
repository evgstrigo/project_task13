package app.services;

import app.entities.Passenger;
import app.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A service for passenger entity.<p>
 * Has 4 CRUD methods.<p>
 * Methods descriptions are below.
 */

@Service
@Transactional
@EnableTransactionManagement
public class PassengerServiceImpl implements PassengerService {

    /**
     * Reference to passenger repository
     */
    private final PassengerRepository passengerRepository;


    /**
     * Autowiring passenger repository field by constructor
     */
    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }


    /**
     * Returns List of all passengers from DB.<p>
     * Has no args.
     */
    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }


    /**
     * Returns passenger from DB by passenger's id.<p>
     * Passenger's id is specified in method argument.<p>
     * throws NoSuchElementException if passenger with specified id is not found
     */
    @Override
    public Passenger findById(Long id) {
        return passengerRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no passenger with id = " + id));
    }


    /**
     * Saves or updates passenger to DB.<p>
     * Passenger entity is specified in method argument.
     */
    @Override
    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }


    /**
     * Deletes passenger from DB by passenger's id.<p>
     * Passenger's id is specified in method argument.
     */
    @Override
    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }
}
