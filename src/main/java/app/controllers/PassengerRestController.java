package app.controllers;

import app.entities.Passenger;
import app.services.PassengerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Passenger entity REST controller<br>
 * Has 4 methods:<br>
 * - Get list of all passengers<br>
 * - Get passenger by id<br>
 * - Add new passenger<br>
 * - Update passenger
 */

@Api(tags = "Passengers", description = "Passenger control")
@RestController
@RequestMapping("/api/passengers")
@Log4j2
public class PassengerRestController {

    /**
     * PassengerService object
     */
    private final PassengerService passengerService;

    /**
     * Autowiring PassengerService object with constructor
     */
    @Autowired
    public PassengerRestController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    /**
     * Maps GET-request
     *
     * @return List of all passengers
     */
    @ApiOperation(value = "Getting all passengers list from DB")
    @GetMapping()
    public List<Passenger> getAllPassengers() {
        log.info("getAllPassengers started");
        return passengerService.findAll();
    }

    /**
     * Maps GET-request with path variable
     *
     * @param id id of concrete passenger
     * @return {@link Passenger} with specified id
     */
    @ApiOperation(value = "Getting passenger by Id from DB")
    @GetMapping("/{id}")
    public Passenger getPassengerById(
            @PathVariable @ApiParam(value = "Passenger's id", example = "1", required = true) long id) {
        log.info("getPassengerById started");
        return passengerService.findById(id);
    }

    /**
     * Maps POST-request
     *
     * @param passenger new passenger to be added to DB
     */
    @ApiOperation(value = "Adding new passenger to DB")
    @PostMapping()
    public void addNewPassenger(@RequestBody Passenger passenger) {
        log.info("addNewPassenger started");
        passengerService.save(passenger);
    }

    /**
     * Maps PUT-request
     *
     * @param passenger passenger (present in DB) to be updated to DB
     */
    @ApiOperation(value = "Updating passenger from DB")
    @PutMapping()
    public void updatePassenger(@RequestBody Passenger passenger) {
        log.info("updatePassenger started");
        passengerService.save(passenger);
    }

}
