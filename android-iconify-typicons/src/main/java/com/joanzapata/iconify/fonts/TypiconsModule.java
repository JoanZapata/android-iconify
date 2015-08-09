package com.joanzapata.iconify.fonts;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class TypiconsModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-typicons.ttf";
    }

    @Override
    public Icon[] characters() {
        return TypiconsIcons.values();
    }
}
