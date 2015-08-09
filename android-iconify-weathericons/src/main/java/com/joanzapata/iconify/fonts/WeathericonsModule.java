package com.joanzapata.iconify.fonts;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class WeathericonsModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-weathericons.ttf";
    }

    @Override
    public Icon[] characters() {
        return WeathericonsIcons.values();
    }
}
