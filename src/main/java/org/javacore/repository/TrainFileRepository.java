package org.javacore.repository;

import org.javacore.entity.Train;

import java.util.List;

public interface TrainFileRepository {

    List<Train> getTrains();

    List<Train> saveTrains(List<Train> trains, String fileName);

}
