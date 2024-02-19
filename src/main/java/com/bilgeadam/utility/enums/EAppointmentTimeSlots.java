package com.bilgeadam.utility.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Getter
public enum EAppointmentTimeSlots {
    TIME_09_00("09:00-09:30"),
    TIME_09_30("09:30-10:00"),
    TIME_10_00("10:00-10:30"),
    TIME_10_30("10:30-11:00"),
    TIME_11_00("11:00-11:30"),
    TIME_11_30("11:30-12:00"),
    TIME_12_00("12:00-12:30"),
    TIME_13_30("13:30-14:00"),
    TIME_14_00("14:00-14:30"),
    TIME_14_30("14:30-15:00"),
    TIME_15_00("15:00-15:30"),
    TIME_15_30("15:30-16:00"),
    TIME_16_00("16:00-16:30"),
    TIME_16_30("16:30-17:00");

    private final String time;


}
