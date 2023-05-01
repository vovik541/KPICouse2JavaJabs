package org.javacore.validator.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.javacore.entity.Train;
import org.javacore.validator.TrainsValidator;

import java.util.List;

public class ResultValidator implements TrainsValidator {
    private static Logger logger = LogManager.getLogger(ResultValidator.class);

    public ResultValidator() {
        logger.debug("ResultValidator created");
    }

    public boolean validate(List<Train> trains) {
        return trains != null && !trains.isEmpty();
    }

}
