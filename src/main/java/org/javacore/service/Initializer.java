package org.javacore.service;

import org.javacore.entity.PlaceType;
import org.javacore.entity.Station;
import org.javacore.entity.Train;
import org.javacore.repository.impl.JSONTrainRepository;
import org.javacore.validator.impl.ResultValidator;
import org.javacore.view.CalculateView;
import org.javacore.view.InputView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.javacore.entity.PlaceType.*;

public class Initializer {

    public static HashMap<Long, Station> STATIONS = new HashMap<>() {{
        put(1L, new Station(1L, "Central", "Kyiv"));
        put(2L, new Station(2L, "West", "Kyiv"));
        put(3L, new Station(3L, "East", "Kyiv"));
        put(4L, new Station(4L, "Central", "Vinnytsia"));
    }};

    public static List<Train> createTrains() {
        List<Train> trains = new LinkedList<>();
        trains.add(
                new Train(STATIONS.get(1L),
                        1L,
                        LocalDateTime.of(2023, 7, 20, 10, 30),
                        buildPlaces(0L, 400L, 200L, 50L)));
        trains.add(
                new Train(STATIONS.get(1L),
                        2L,
                        LocalDateTime.of(2023, 6, 25, 16, 45),
                        buildPlaces(100L, 600L, 250L, 26L)));
        trains.add(
                new Train(STATIONS.get(1L),
                        3L,
                        LocalDateTime.of(2023, 9, 21, 9, 33),
                        buildPlaces(0L, 200L, 210L, 430L)));
        trains.add(
                new Train(STATIONS.get(1L),
                        4L,
                        LocalDateTime.of(2023, 9, 11, 6, 11),
                        buildPlaces(600L, 400L, 200L, 60L)));
        trains.add(
                new Train(STATIONS.get(2L),
                        5L,
                        LocalDateTime.of(2023, 10, 1, 8, 15),
                        buildPlaces(250L, 400L, 250L, 0L)));
        trains.add(
                new Train(STATIONS.get(2L),
                        6L,
                        LocalDateTime.of(2023, 11, 26, 20, 56),
                        buildPlaces(456L, 400L, 300L, 20L)));
        trains.add(
                new Train(STATIONS.get(2L),
                        7L,
                        LocalDateTime.of(2023, 6, 18, 11, 12),
                        buildPlaces(356L, 0L, 112L, 110L)));
        trains.add(
                new Train(STATIONS.get(3L),
                        8L,
                        LocalDateTime.of(2023, 7, 12, 5, 10),
                        buildPlaces(156L, 400L, 312L, 321L)));
        trains.add(
                new Train(STATIONS.get(3L),
                        9L,
                        LocalDateTime.of(2023, 8, 5, 17, 15),
                        buildPlaces(0L, 400L, 200L, 0L)));
        trains.add(
                new Train(STATIONS.get(3L),
                        10L,
                        LocalDateTime.of(2023, 9, 1, 15, 43),
                        buildPlaces(140L, 400L, 0L, 90L)));
        trains.add(
                new Train(STATIONS.get(3L),
                        11L,
                        LocalDateTime.of(2023, 10, 13, 14, 24),
                        buildPlaces(0L, 400L, 200L, 110L)));
        trains.add(
                new Train(STATIONS.get(3L),
                        12L,
                        LocalDateTime.of(2023, 12, 15, 11, 54),
                        buildPlaces(0L, 400L, 244L, 150L)));
        trains.add(
                new Train(STATIONS.get(3L),
                        13L,
                        LocalDateTime.of(2023, 11, 27, 15, 43),
                        buildPlaces(100L, 400L, 190L, 140L)));
        trains.add(
                new Train(STATIONS.get(4L),
                        14L,
                        LocalDateTime.of(2023, 8, 24, 14, 32),
                        buildPlaces(0L, 800L, 200L, 320L)));
        trains.add(
                new Train(STATIONS.get(4L),
                        15L,
                        LocalDateTime.of(2023, 7, 20, 21, 34),
                        buildPlaces(110L, 200L, 200L, 220L)));
        trains.add(
                new Train(STATIONS.get(4L),
                        16L,
                        LocalDateTime.of(2023, 7, 14, 22, 50),
                        buildPlaces(500L, 400L, 215L, 120L)));

        return trains;

    }

    private static HashMap<PlaceType, Long> buildPlaces(Long general, Long coupe, Long reserved, Long sv) {
        HashMap<PlaceType, Long> places = new HashMap<>();

        if (general != 0) {
            places.put(GENERAL, general);
        }
        if (coupe != 0) {
            places.put(COUPE, coupe);
        }
        if (reserved != 0) {
            places.put(RESERVED_SEATS, reserved);
        }
        if (sv != 0) {
            places.put(SV, sv);
        }

        return places;
    }

    private static CalculateView calculateView;

    public static CalculateView getCalculateView() {
        if (calculateView == null) {
            calculateView = new CalculateView();
        }
        return calculateView;
    }

    private static InputView inputView;

    public static InputView getInputView() {
        if (inputView == null) {
            inputView = new InputView(getCalculateView(), new Scanner(System.in));
        }
        return inputView;
    }

    private static JSONTrainRepository jsonTrainRepository;

    public static JSONTrainRepository getJSONTrainRepository() {
        if (jsonTrainRepository == null) {
            jsonTrainRepository = new JSONTrainRepository();
        }
        return jsonTrainRepository;
    }

    private static TrainService trainService;

    public static TrainService getTrainService() {
        if (trainService == null) {
            trainService = new TrainService(getJSONTrainRepository());
        }
        return trainService;
    }

    private static ResultValidator resultValidator;

    public static ResultValidator getResultValidator() {
        if (resultValidator == null) {
            resultValidator = new ResultValidator();
        }
        return resultValidator;
    }

}
