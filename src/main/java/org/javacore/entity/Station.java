package org.javacore.entity;

import java.util.Objects;

public class Station {

    private Long stationId;
    private String stationName;

    private String StationCity;

    public Station(Long stationId, String stationName, String stationCity) {
        this.stationId = stationId;
        this.stationName = stationName;
        StationCity = stationCity;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCity() {
        return StationCity;
    }

    public void setStationCity(String stationCity) {
        StationCity = stationCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return stationId.equals(station.stationId) && stationName.equals(station.stationName) && StationCity.equals(station.StationCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationId, stationName, StationCity);
    }

    @Override
    public String toString() {
        return stationName + ", " + StationCity;
    }
}
