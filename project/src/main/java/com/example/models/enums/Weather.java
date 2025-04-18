package com.example.models.enums;

public enum Weather {
    SUNNY(new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN, Season.WINTER}, "Sunny"),
    RAIN(new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN}, "Rain"),
    STORM(new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN}, "Storm"),
    SNOW(new Season[]{Season.WINTER}, "Snow"),
    ;

    final public Season[] possibleSeasons;
    final private String name;

    Weather(Season[] possibleSeasons, String name) {
        this.name = name;
        this.possibleSeasons = possibleSeasons;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isWeatherPossibleInThisSeason(Season season) {
        for (Season possibleSeason : possibleSeasons) {
            if (possibleSeason == season) {
                return true;
            }
        }
        return false;
    }

    public static Weather getWeatherByName(String input) {
        for (Weather weather : Weather.values()) {
            if (weather.name.compareToIgnoreCase(input) == 0) {
                return weather;
            }
        }
        return null;
    }
}
