package app.controllers;

import app.entities.Destination;
import app.services.DestinationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Это контроллер направлений, т.е. конкретный пункт вылета и/или прилёта,
 * который создаёт изменяет и позволяет получить
 * определенный пункт
 */

@Api(tags = "Destination Controller")
@RestController
@RequestMapping("api/destinations")
public class DestinationController {
    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    /**
     *Это метод для получения пункта прилета/вылета по id
     */

    @ApiOperation("Get destination by id") 
    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable("id") Long id) {
        Destination destination = destinationService.findById(id);
        return destination != null
                ? new ResponseEntity<>(destination, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * Это метод для получения пункта прилёта/вылета по названию города
     */

    @ApiOperation("Get destination by Sity name")
    @GetMapping()
    public ResponseEntity<Destination> getDestinationBySity(@RequestParam(value = "sity") String sity) {
        final Destination destination = destinationService.findBySity(sity);
        return destination != null
                ? new ResponseEntity<>(destination, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * Это метод для создания нового пункта вылета/прилёта
     */

    @ApiOperation("Create new destination")
    @PostMapping
    public ResponseEntity<?> addDestination(@RequestBody Destination destination) {
        destinationService.save(destination);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    /**
     * 
     * этот метод для обновления данных пункта вылета/прилёта
     */

    @ApiOperation("Update destination")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Destination destination) {
        destination.setId(id);
        destinationService.save(destination);
        Destination destination1 = destinationService.findById(id);
        return new ResponseEntity<>(destination1, HttpStatus.OK);
    }
}