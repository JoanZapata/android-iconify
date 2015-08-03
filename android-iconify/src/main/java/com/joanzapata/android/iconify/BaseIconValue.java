package com.joanzapata.android.iconify;

public interface BaseIconValue {

    char character();

    String getTtfFilename();

    String getPrefix();

    BaseIconValue iconFrom(String value);

}
