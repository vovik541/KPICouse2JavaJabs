package org.javacore.repository;

import org.javacore.entity.Train;

import java.util.List;

public interface TrainRepository {

    List<Train> getTrains();

    List<Train> saveTrains(List<Train> trains);

}
