package org.javacore.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.javacore.entity.PlaceType;
import org.javacore.entity.Station;
import org.javacore.entity.Train;
import org.javacore.repository.TrainFileRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.javacore.util.FilePathManager.getFilePath;

public class TrainService {
    private static Logger logger = LogManager.getLogger(TrainService.class);
    private TrainFileRepository repository;

    public TrainService(TrainFileRepository repository) {
        this.repository = repository;
        logger.debug("TrainService created");
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
            logger.info("result was saved");
            return "saved to file: " + getFilePath(fileName + ".json");
        }
        logger.info("result was not saved because of user option");
        return "the result was not saved";
    }

    public TrainFileRepository getRepository() {
        return repository;
    }

    public void setRepository(TrainFileRepository repository) {
        this.repository = repository;
    }
}
