package com.enums;

public enum Days {
    Mon ("Monday"),
    Tue ("Tuesday"),
    Wed ("Wednesday"),
    Thu ("Thursday"),
    Fri ("Friday"),
    Sat ("Saturday"),
    Sun ("Sunday");

    private final String dayOfTheWeek;


    Days(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
