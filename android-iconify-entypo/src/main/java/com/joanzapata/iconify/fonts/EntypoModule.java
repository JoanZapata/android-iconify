package com.joanzapata.iconify.fonts;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class EntypoModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-entypo.ttf";
    }

    @Override
    public Icon[] characters() {
        return EntypoIcons.values();
    }
}
