package app.services;

import app.entities.Destination;

/**
 * интерфейс описывающий методы поиска и сохранения/обновления
 * пунктов вылета/прилёта
 */

public interface DestinationService {
    Destination findById(Long id);
    Destination findBySity(String sity);
    void save(Destination destination);
}
