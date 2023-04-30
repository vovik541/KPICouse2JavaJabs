package org.javacore.service;

import org.javacore.entity.PlaceType;
import org.javacore.entity.Station;
import org.javacore.entity.Train;
import org.javacore.repository.TrainRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TrainService {

    private TrainRepository repository;

    public TrainService(TrainRepository repository) {
        this.repository = repository;
    }

    public List<Train> findTrainsWithGeneralSeats() {
        return repository.getTrains().stream().filter(x -> x.getPlaces().containsKey(PlaceType.GENERAL)).toList();
    }

    public List<Train> findTrainsByDepartureTimeAndStation(LocalDateTime departureAt, Station station) {
        return repository.getTrains().stream().filter(x -> x.getStation().equals(station) && x.getDepartureTime().isAfter(departureAt)).toList();
    }

    public List<Train> findAllTrains() {
        return repository.getTrains();
    }

    public TrainRepository getRepository() {
        return repository;
    }

    public void setRepository(TrainRepository repository) {
        this.repository = repository;
    }
}
