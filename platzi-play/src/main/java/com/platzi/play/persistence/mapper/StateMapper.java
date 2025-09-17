package com.platzi.play.persistence.mapper;

import org.mapstruct.Named;

public interface StateMapper {

    @Named("charToBoolean")
    public static Boolean charToBoolean(char estado) {
        if (estado == 'D') return true;
        return false;
    }

    @Named("booleanToChar")
    public static char booleanToChar(Boolean isAvailable) {
        if (isAvailable) return 'D';
        return 'I';
    }
}