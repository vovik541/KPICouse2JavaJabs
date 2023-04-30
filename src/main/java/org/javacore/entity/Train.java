package org.javacore.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

import static org.javacore.constant.Constants.DAY_TIME_FORMAT;
import static org.javacore.entity.PlaceType.*;

public class Train {
    private Station station;
    private Long trainNumber;
    private LocalDateTime departureTime;

    private HashMap<PlaceType, Long> places;

    public Train(Station station, Long trainNumber, LocalDateTime departureTime, HashMap<PlaceType, Long> places) {
        this.station = station;
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
        this.places = places;
    }

    public void addPlaces(PlaceType type, Long count) {
        this.places.put(type, count);
    }

    ;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Long getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Long trainNumber) {
        this.trainNumber = trainNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public HashMap<PlaceType, Long> getPlaces() {
        return places;
    }

    public void setPlaces(HashMap<PlaceType, Long> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return station.equals(train.station) && trainNumber.equals(train.trainNumber) && departureTime.equals(train.departureTime) && places.equals(train.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(station, trainNumber, departureTime, places);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DAY_TIME_FORMAT);

        String formattedDateTime = departureTime.format(formatter);
        return String.format("|%13d |%17s |%20s |%14d |%12d |%15d |%9d |", trainNumber, formattedDateTime, station,
                places.get(GENERAL) == null ? 0 : places.get(GENERAL),
                places.get(COUPE) == null ? 0 : places.get(COUPE),
                places.get(RESERVED_SEATS) == null ? 0 : places.get(RESERVED_SEATS),
                places.get(SV) == null ? 0 : places.get(SV));
    }
}
