package app.services;

import app.entities.Destination;
import app.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * реализация бизнес логики по поиску, обновлению и созданию
 * пунктов прилёта/вылета
 */

@Service
@Transactional
public class DestinationServiceImp implements DestinationService{

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationServiceImp(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    /**
     * реализация поиска по id
     */
    @Override
    public Destination findById(Long id) {
        Optional<Destination> destinationFromDb = destinationRepository.findById(id);
        return destinationFromDb.orElse(new Destination());
    }

    /**
     * реализация поиска по названию города
     */
    @Override
    public Destination findBySity(String sity) {
        return destinationRepository.findBySity(sity);
    }

    /**
     * реализация сохранения и обновления пункта прилёта/вылета
     */
    @Override
    public void save(Destination destination) {
        destinationRepository.save(destination);
    }
}
