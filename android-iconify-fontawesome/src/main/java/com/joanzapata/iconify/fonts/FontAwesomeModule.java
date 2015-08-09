package com.joanzapata.iconify.fonts;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class FontAwesomeModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-fontawesome.ttf";
    }

    @Override
    public Icon[] characters() {
        return FontAwesomeIcons.values();
    }
}
