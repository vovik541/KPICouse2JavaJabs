package org.javacore.repository.impl;

import org.javacore.entity.Train;
import org.javacore.repository.TrainRepository;
import org.javacore.service.Initializer;

import java.util.List;

public class JSONTrainRepository implements TrainRepository {

    @Override
    public List<Train> getTrains() {
        //todo
        return Initializer.createTrains();
    }

    @Override
    public List<Train> saveTrains(List<Train> trains) {
        //todo
        return null;
    }
}
