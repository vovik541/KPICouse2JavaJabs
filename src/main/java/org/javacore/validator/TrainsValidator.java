package org.javacore.validator;

import org.javacore.entity.Train;

import java.util.List;

public interface TrainsValidator {

    boolean validate(List<Train> trains);
}
