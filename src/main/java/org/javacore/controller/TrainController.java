package org.javacore.controller;

import org.javacore.entity.Station;
import org.javacore.entity.Train;
import org.javacore.service.TrainService;
import org.javacore.validator.impl.ResultValidator;
import org.javacore.view.CalculateView;
import org.javacore.view.InputView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TrainController implements Runnable {
    private Thread trainController;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private CalculateView calculateView;
    private InputView inputView;
    private TrainService trainService;
    private ResultValidator validator;

    public TrainController(CalculateView calculateView, InputView inputView, TrainService trainService, ResultValidator validator) {
        this.calculateView = calculateView;
        this.inputView = inputView;
        this.trainService = trainService;
        this.validator = validator;
    }

    public void run() {
        running.set(true);

        List<Train> result;
        int userChoice;
        Station station;
        LocalDateTime departureAt;

        calculateView.printGreetingMessage();
        calculateView.printTrainsToConsole(trainService.findAllTrains());

        while (running.get()) {
            calculateView.printMainMenu();
            userChoice = inputView.readUserChoiceFromConsole(1, 4);

            switch (userChoice) {
                case 1:
                    result = trainService.findTrainsWithGeneralSeats();
                    processResult(result);
                    break;
                case 2:
                    station = inputView.readStationFromConsole();
                    departureAt = inputView.readDepartureTimeFromConsole();
                    result = trainService.findTrainsByDepartureTimeAndStation(departureAt, station);
                    processResult(result);
                    break;
                case 3:
                    break;
                default:
                    stop();
                    break;
            }
        }
    }

    public void start() {
        trainController = new Thread(this);
        trainController.start();
    }

    public void stop() {
        running.set(false);
    }

    public void processResult(List<Train> result) {
        if (validator.validate(result)) {
            calculateView.printTrainsToConsole(result);
        } else {
            calculateView.printResultNotFound();
        }
    }

}
