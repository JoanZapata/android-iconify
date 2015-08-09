package com.joanzapata.iconify.fonts;

import com.joanzapata.iconify.Icon;

public enum MeteoconsIcons implements Icon {
    mc_sunrise_o('A'),
    mc_sun_o('B'),
    mc_moon_o('C'),
    mc_eclipse('D'),
    mc_cloudy_o('E'),
    mc_wind_o('F'),
    mc_snow('G'),
    mc_sun_cloud_o('H'),
    mc_moon_cloud_o('I'),
    mc_sunrise_sea_o('J'),
    mc_moonrise_sea_o('K'),
    mc_cloud_sea_o('L'),
    mc_sea_o('M'),
    mc_cloud_o('N'),
    mc_cloud_thunder_o('O'),
    mc_cloud_thunder2_o('P'),
    mc_cloud_drop_o('Q'),
    mc_cloud_rain_o('R'),
    mc_cloud_wind_o('S'),
    mc_cloud_wind_rain_o('T'),
    mc_cloud_snow_o('U'),
    mc_cloud_snow2_o('V'),
    mc_cloud_snow3_o('W'),
    mc_cloud_rain2_o('X'),
    mc_cloud_double_o('Y'),
    mc_cloud_double_thunder_o('Z'),
    mc_cloud_double_thunder2_o('0'),
    mc_sun('1'),
    mc_moon('2'),
    mc_sun_cloud('3'),
    mc_moon_cloud('4'),
    mc_cloud('5'),
    mc_cloud_thunder('6'),
    mc_cloud_drop('7'),
    mc_cloud_rain('8'),
    mc_cloud_wind('9'),
    mc_cloud_wind_rain('!'),
    mc_cloud_snow('"'),
    mc_cloud_snow2('#'),
    mc_cloud_rain2('$'),
    mc_cloud_double('%'),
    mc_cloud_double_thunder('&'),
    mc_thermometer('\''),
    mc_compass('('),
    mc_not_applicable(')'),
    mc_celsius('*'),
    mc_fahrenheit('+');

    char character;

    MeteoconsIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
