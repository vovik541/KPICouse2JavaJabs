package org.javacore.service;

import org.javacore.entity.PlaceType;
import org.javacore.entity.Station;
import org.javacore.entity.Train;
import org.javacore.repository.TrainFileRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.javacore.util.FilePathManager.getFilePath;

public class TrainService {

    private TrainFileRepository repository;

    public TrainService(TrainFileRepository repository) {
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

    public String saveTrainsToFile(List<Train> trains, String fileName) {
        if (!fileName.isEmpty()) {
            repository.saveTrains(trains, fileName + ".json");
            return "saved to file: " + getFilePath(fileName + ".json");
        }
        return "the result was not saved";
    }

    public TrainFileRepository getRepository() {
        return repository;
    }

    public void setRepository(TrainFileRepository repository) {
        this.repository = repository;
    }
}
