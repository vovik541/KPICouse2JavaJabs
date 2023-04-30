package org.javacore.entity;

public enum PlaceType {

    GENERAL("general"),
    COUPE("coupe"),
    RESERVED_SEATS("reserved seats"),
    SV("SV");

    private String value;

    PlaceType(String value) {
        this.value = value;
    }

}
