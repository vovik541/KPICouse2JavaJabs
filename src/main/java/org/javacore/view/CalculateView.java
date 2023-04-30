package org.javacore.view;

import org.javacore.entity.Station;
import org.javacore.entity.Train;

import java.util.List;

import static org.javacore.service.Initializer.STATIONS;

public class CalculateView {
    public static final String TRAINS_WITH_GENERAL_SEATS_MESSAGE = "1. Get trains with general seats";
    public static final String TRAINS_BY_STATION_AND_DEPARTURE_MESSAGE = "2. Get trains by station and departure time";
    public static final String CHANGE_SAVED_DATA_WAY_MESSAGE = "3. Change result save method";

    public static final String EXIT_MESSAGE = "4. Exit";
    public static final String INPUT_NOT_VALID_MESSAGE = "Please, chose number between %d and %d";

    public void printGreetingMessage() {
        System.out.println("All trains:");
    }

    public void printMainMenu() {
        System.out.println("\n Input number to male a choice:");
        System.out.println(TRAINS_WITH_GENERAL_SEATS_MESSAGE);
        System.out.println(TRAINS_BY_STATION_AND_DEPARTURE_MESSAGE);
        System.out.println(CHANGE_SAVED_DATA_WAY_MESSAGE);
        System.out.println(EXIT_MESSAGE);
    }

    public void printMessageAndResult(String message, int value) {
        System.out.println(message + value);
    }

    public void printTrainsToConsole(List<Train> trains) {

        System.out.println("\n" + String.format("|%13s |%17s |%20s |%14s |%12s |%15s |%9s |", "train number", "departure at", "station",
                "general seats",
                "coupe seats",
                "reserved seats",
                "sv seats"));

        System.out.println("-".repeat(115));

        trains.forEach(System.out::println);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printStationChoiceMenu() {
        System.out.println("\nChoose station number:\n");
        System.out.println(String.format("|%7s |%12s |%12s |", "number", "city", "name"));
        System.out.println("-".repeat(38));

        for (Station station : STATIONS.values()) {
            System.out.println(String.format("|%7s |%12s |%12s |", station.getStationId(), station.getStationCity(), station.getStationName()));
        }
    }

    public void printResultNotFound() {
        System.out.println("Sorry, but there is no suitable trains by this search. Try again with other request");
    }
}
