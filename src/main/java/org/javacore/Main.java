package org.javacore;

import org.javacore.controller.TrainController;
import org.javacore.repository.impl.JSONTrainRepository;

import static org.javacore.util.Initializer.*;

public class Main {
    public static void main(String[] args) {
        TrainController controller = new TrainController(getCalculateView(), getInputView(), getTrainService(), getResultValidator());
        controller.start();
    }
}