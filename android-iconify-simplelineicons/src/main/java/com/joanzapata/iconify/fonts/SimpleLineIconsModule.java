package com.joanzapata.iconify.fonts;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class SimpleLineIconsModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-simplelineicons.ttf";
    }

    @Override
    public Icon[] characters() {
        return SimpleLineIconsIcons.values();
    }
}
