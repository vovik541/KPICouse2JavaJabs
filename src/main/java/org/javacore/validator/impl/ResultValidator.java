package org.javacore.validator.impl;

import org.javacore.entity.Train;
import org.javacore.validator.TrainsValidator;

import java.util.List;

public class ResultValidator implements TrainsValidator {

    public boolean validate(List<Train> trains) {
        return trains != null && !trains.isEmpty();
    }

}
