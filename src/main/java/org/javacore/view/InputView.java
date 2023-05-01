package org.javacore.view;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.javacore.entity.Station;
import org.javacore.exception.WrongDateTimeProvidedException;
import org.javacore.exception.WrongNumberProvidedException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.javacore.constant.Constants.*;
import static org.javacore.util.Initializer.STATIONS;

public class InputView {

    private static Logger logger = LogManager.getLogger(InputView.class);
    private static Scanner sc;
    private CalculateView view;

    public InputView(CalculateView view, Scanner sc) {
        this.view = view;
        this.sc = sc;
    }

    public int readUserChoiceFromConsole(int minValue, int maxValue) {
        int number = 0;

        while (true) {
            try {
                number = sc.nextInt();
                sc.nextLine();
                if (number >= minValue && number <= maxValue) {
                    logger.info("User chose option %d".formatted(number));
                    return number;
                } else {
                    throw new WrongNumberProvidedException(minValue, maxValue);
                }
            } catch (WrongNumberProvidedException wrongNumberProvidedException) {
                view.printMessage(wrongNumberProvidedException.getMessage());
                logger.info("User chose not appropriate option. Message: " + wrongNumberProvidedException.getMessage());
            } catch (Exception e) {
                view.printMessage(INPUT_NOT_A_NUMBER_ERROR_MESSAGE);
                logger.info("User input exception. Retrying to receive result");
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
                logger.info("User successfully chose date and time");
                return input;

            } catch (WrongDateTimeProvidedException wrongDateTimeProvidedException) {
                view.printMessage(wrongDateTimeProvidedException.getMessage());
                logger.info("User did not chose correct date and time. Message: " + wrongDateTimeProvidedException.getMessage());
            } catch (Exception ex) {
                view.printMessage(INPUT_NOT_A_CORRECT_DATE_TIME_ERROR_MESSAGE);
                logger.info("Not correct user input format");
            }
        }
    }

    public Station readStationFromConsole() {
        view.printStationChoiceMenu();
        int userChoice = readUserChoiceFromConsole(1, STATIONS.size());
        logger.info("User successfully chose station");

        return STATIONS.get((long) userChoice);
    }

    public String readFileNameFromConsole() {
        view.askForSaveFileOutput();
        String result = sc.nextLine();
        return result;
    }
}
