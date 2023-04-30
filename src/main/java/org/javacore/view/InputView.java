package org.javacore.view;

import org.javacore.entity.Station;
import org.javacore.exception.WrongDateTimeProvidedException;
import org.javacore.exception.WrongNumberProvidedException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.javacore.constant.Constants.*;
import static org.javacore.service.Initializer.STATIONS;

public class InputView {
    private static Scanner sc;
    private CalculateView view;

    public InputView(CalculateView view, Scanner sc) {
        this.view = view;
        this.sc = sc;
    }

    public int readUserChoiceFromConsole(int minValue, int maxValue) {
        int number;

        while (true) {
            try {

                number = sc.nextInt();
                if (number >= minValue && number <= maxValue) {
                    return number;
                } else {
                    throw new WrongNumberProvidedException(minValue, maxValue);
                }
            } catch (WrongNumberProvidedException wrongNumberProvidedException) {
                view.printMessage(wrongNumberProvidedException.getMessage());
            } catch (Exception e) {
                view.printMessage(INPUT_NOT_A_NUMBER_ERROR_MESSAGE);
            }
        }
    }

    public LocalDateTime readDepartureTimeFromConsole() {
        view.printMessage(INPUT_DATE_AND_TIME_AS_EXAMPLE_MESSAGE);

        String date;
        LocalDateTime input;

        while (true) {
            try {

                date = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DAY_TIME_FORMAT);
                input = LocalDateTime.parse(date, formatter);

                if (input.getYear() < 2023) {
                    throw new WrongDateTimeProvidedException("Please, select any year after 2022");
                } else if (input.getMonth().getValue() < LocalDateTime.now().getMonth().getValue()) {
                    throw new WrongDateTimeProvidedException("You can't select any past months");
                }

                return input;

            } catch (WrongDateTimeProvidedException wrongDateTimeProvidedException) {
                view.printMessage(wrongDateTimeProvidedException.getMessage());
            } catch (Exception ex) {
                view.printMessage(INPUT_NOT_A_CORRECT_DATE_TIME_ERROR_MESSAGE);
            }
        }
    }

    public Station readStationFromConsole() {
        view.printStationChoiceMenu();
        int userChoice = readUserChoiceFromConsole(1, STATIONS.size());

        return STATIONS.get((long) userChoice);
    }
}
